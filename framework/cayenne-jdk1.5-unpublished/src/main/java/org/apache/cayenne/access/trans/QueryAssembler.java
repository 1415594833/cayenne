/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.access.trans;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.access.QueryLogger;
import org.apache.cayenne.access.QueryTranslator;
import org.apache.cayenne.map.DbAttribute;
import org.apache.cayenne.map.DbEntity;
import org.apache.cayenne.map.DbRelationship;
import org.apache.cayenne.map.JoinType;
import org.apache.cayenne.query.QualifiedQuery;

/**
 * Abstract superclass of Query translators.
 * 
 * @author Andrus Adamchik
 */
public abstract class QueryAssembler extends QueryTranslator {

    /** PreparedStatement values. */
    protected List<Object> values = new ArrayList<Object>();

    /**
     * PreparedStatement attributes matching entries in <code>values</code> list.
     */
    protected List<DbAttribute> attributes = new ArrayList<DbAttribute>();

    /**
     * Processes a join being added.
     * 
     * @deprecated since 3.0 use {@link #dbRelationshipAdded(DbRelationship, JoinType)}.
     */
    public abstract void dbRelationshipAdded(DbRelationship dbRel);

    /**
     * Returns aliases for the joins defined in the query.
     * 
     * @since 3.0
     */
    protected Map<String, String> getJoinAliases() {
        if (query instanceof QualifiedQuery) {
            return ((QualifiedQuery) query).getJoinAliases();
        }
        return Collections.emptyMap();
    }

    /**
     * Appends a join with given semantics to the query.
     * 
     * @since 3.0
     */
    public abstract void dbRelationshipAdded(
            DbRelationship relationship,
            JoinType joinType);

    /**
     * Translates query into sql string. This is a workhorse method of QueryAssembler. It
     * is called internally from <code>createStatement</code>. Usually there is no need
     * to invoke it explicitly.
     */
    public abstract String createSqlString() throws Exception;

    public String aliasForTable(DbEntity ent, DbRelationship rel) {
        return aliasForTable(ent); // Default implementation
    }

    /**
     * Returns a name that can be used as column alias. This can be one of the following:
     * <ul>
     * <li>an alias for this table, if it uses aliases</li>
     * <li>a fully qualified table name, if not.</li>
     * </ul>
     * CayenneRuntimeException is thrown if a table alias can not be created.
     */
    public abstract String aliasForTable(DbEntity dbEnt);

    /**
     * Returns <code>true</code> if table aliases are supported. Default implementation
     * returns false.
     */
    public boolean supportsTableAliases() {
        return false;
    }

    /**
     * Registers <code>anObject</code> as a PreparedStatement parameter.
     * 
     * @param anObject object that represents a value of DbAttribute
     * @param dbAttr DbAttribute being processed.
     */
    public void addToParamList(DbAttribute dbAttr, Object anObject) {
        attributes.add(dbAttr);
        values.add(anObject);
    }

    /**
     * Translates internal query into PreparedStatement.
     */
    @Override
    public PreparedStatement createStatement() throws Exception {
        long t1 = System.currentTimeMillis();
        String sqlStr = createSqlString();
        QueryLogger.logQuery(sqlStr, attributes, values, System.currentTimeMillis() - t1);
        PreparedStatement stmt = connection.prepareStatement(sqlStr);
        initStatement(stmt);
        return stmt;
    }

    /**
     * Initializes prepared statements with collected parameters. Called internally from
     * "createStatement". Cayenne users shouldn't normally call it directly.
     */
    protected void initStatement(PreparedStatement stmt) throws Exception {
        if (values != null && values.size() > 0) {
            int len = values.size();
            for (int i = 0; i < len; i++) {
                Object val = values.get(i);

                DbAttribute attr = attributes.get(i);

                // null DbAttributes are a result of inferior qualifier processing
                // (qualifier can't map parameters to DbAttributes and therefore
                // only supports standard java types now)
                // hence, a special moronic case here:
                if (attr == null) {
                    stmt.setObject(i + 1, val);
                }
                else {
                    adapter.bindParameter(stmt, val, i + 1, attr.getType(), attr
                            .getScale());
                }
            }
        }
    }
}
