package org.ccomp.fileHandling;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjectHelper {

    // write a StringProperty to ObjectOutputStream
    public void writeStringProp(ObjectOutputStream s, StringProperty strProp) throws IOException {
        s.writeUTF(strProp.getValueSafe());
    }

    // automatic write set of properties to ObjectOutputStream
    public static void writeAllProp(ObjectOutputStream s, Property... properties) throws IOException {
        s.defaultWriteObject();
        for(Property prop:properties) {
            if(prop instanceof StringProperty) s.writeUTF(((StringProperty)prop).getValueSafe());
            else if(prop instanceof IntegerProperty) s.writeInt(((IntegerProperty) prop).intValue());
            else if(prop instanceof LongProperty) s.writeLong(((LongProperty) prop).longValue());
            else if(prop instanceof DoubleProperty) s.writeDouble(((DoubleProperty)prop).doubleValue());
            else if(prop instanceof BooleanProperty) s.writeBoolean(((BooleanProperty)prop).get());
            else throw new RuntimeException("Type d'objet incompatible : " + prop.toString());
        }
    }
}

