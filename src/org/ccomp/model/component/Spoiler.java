package org.ccomp.model.component;

public class Spoiler {

    private String type; //sport, normal, etc.

    public Spoiler(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
