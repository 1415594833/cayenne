package org.apache.cayenne.testdo.primitive.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _PrimitivesTestEntity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _PrimitivesTestEntity extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<Boolean> BOOLEAN_COLUMN = Property.create("booleanColumn", Boolean.class);
    public static final Property<Integer> INT_COLUMN = Property.create("intColumn", Integer.class);

    protected boolean booleanColumn;
    protected int intColumn;


    public void setBooleanColumn(boolean booleanColumn) {
        beforePropertyWrite("booleanColumn", this.booleanColumn, booleanColumn);
        this.booleanColumn = booleanColumn;
    }

	public boolean isBooleanColumn() {
        beforePropertyRead("booleanColumn");
        return this.booleanColumn;
    }

    public void setIntColumn(int intColumn) {
        beforePropertyWrite("intColumn", this.intColumn, intColumn);
        this.intColumn = intColumn;
    }

    public int getIntColumn() {
        beforePropertyRead("intColumn");
        return this.intColumn;
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "booleanColumn":
                return this.booleanColumn;
            case "intColumn":
                return this.intColumn;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "booleanColumn":
                this.booleanColumn = val == null ? false : (Boolean)val;
                break;
            case "intColumn":
                this.intColumn = val == null ? 0 : (Integer)val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeBoolean(this.booleanColumn);
        out.writeInt(this.intColumn);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.booleanColumn = in.readBoolean();
        this.intColumn = in.readInt();
    }

}
