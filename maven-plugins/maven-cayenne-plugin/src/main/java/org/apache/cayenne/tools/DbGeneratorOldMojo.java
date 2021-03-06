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

package org.apache.cayenne.tools;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Maven mojo to perform class generation from data map. This class is a Maven
 * adapter to DefaultClassGenerator class.
 *
 * @deprecated use "cayenne-maven-plugin"
 * @since 4.0
 */
@Mojo(name = "cdbgen", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class DbGeneratorOldMojo extends DbGeneratorMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().warn("Plugin \"maven-cayenne-plugin\" has been deprecated.\n" +
                "Please use \"cayenne-maven-plugin\":\n" +
                "<plugin>\n" +
                "   <groupId>org.apache.cayenne.plugins</groupId>\n" +
                "   <artifactId>cayenne-maven-plugin</artifactId>\n" +
                "   <version>LATEST</version>\n" +
                "</plugin>\n");

        super.execute();
    }
}
