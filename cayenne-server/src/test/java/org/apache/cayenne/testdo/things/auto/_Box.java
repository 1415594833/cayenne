package org.apache.cayenne.testdo.things.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.apache.cayenne.testdo.things.Bag;
import org.apache.cayenne.testdo.things.Ball;
import org.apache.cayenne.testdo.things.BoxInfo;
import org.apache.cayenne.testdo.things.Thing;

/**
 * Class _Box was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Box extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<Bag> BAG = Property.create("bag", Bag.class);
    public static final Property<List<Ball>> BALLS = Property.create("balls", List.class);
    public static final Property<BoxInfo> BOX_INFO = Property.create("boxInfo", BoxInfo.class);
    public static final Property<List<Thing>> THINGS = Property.create("things", List.class);

    protected String name;

    protected Object bag;
    protected Object balls;
    protected Object boxInfo;
    protected Object things;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void setBag(Bag bag) {
        setToOneTarget("bag", bag, true);
    }

    public Bag getBag() {
        return (Bag)readProperty("bag");
    }

    public void addToBalls(Ball obj) {
        addToManyTarget("balls", obj, true);
    }

    public void removeFromBalls(Ball obj) {
        removeToManyTarget("balls", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Ball> getBalls() {
        return (List<Ball>)readProperty("balls");
    }

    public void setBoxInfo(BoxInfo boxInfo) {
        setToOneTarget("boxInfo", boxInfo, true);
    }

    public BoxInfo getBoxInfo() {
        return (BoxInfo)readProperty("boxInfo");
    }

    @SuppressWarnings("unchecked")
    public List<Thing> getThings() {
        return (List<Thing>)readProperty("things");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "bag":
                return this.bag;
            case "balls":
                return this.balls;
            case "boxInfo":
                return this.boxInfo;
            case "things":
                return this.things;
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
            case "name":
                this.name = (String)val;
                break;
            case "bag":
                this.bag = val;
                break;
            case "balls":
                this.balls = val;
                break;
            case "boxInfo":
                this.boxInfo = val;
                break;
            case "things":
                this.things = val;
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
        out.writeObject(this.name);
        out.writeObject(this.bag);
        out.writeObject(this.balls);
        out.writeObject(this.boxInfo);
        out.writeObject(this.things);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.bag = in.readObject();
        this.balls = in.readObject();
        this.boxInfo = in.readObject();
        this.things = in.readObject();
    }

}
