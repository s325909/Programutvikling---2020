package org.ccomp.model;

import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;
import org.ccomp.model.component.WheelRim;
import org.ccomp.model.component.engine.Engine;

import java.io.Serializable;

public class MapKey implements Serializable {

    private Engine keyEngine;
    private Seat keySeat;
    private Spoiler keySpoiler;
    private SteeringWheel keySteeringWheel;
    private WheelRim keyWheelRim;

    public Engine getKeyEngine() {
        return keyEngine;
    }

    public void setKeyEngine(Engine keyEngine) {
        this.keyEngine = keyEngine;
    }

    public Seat getKeySeat() {
        return keySeat;
    }

    public void setKeySeat(Seat keySeat) {
        this.keySeat = keySeat;
    }

    public Spoiler getKeySpoiler() {
        return keySpoiler;
    }

    public void setKeySpoiler(Spoiler keySpoiler) {
        this.keySpoiler = keySpoiler;
    }

    public SteeringWheel getKeySteeringWheel() {
        return keySteeringWheel;
    }

    public void setKeySteeringWheel(SteeringWheel keySteeringWheel) {
        this.keySteeringWheel = keySteeringWheel;
    }

    public WheelRim getKeyWheelRim() {
        return keyWheelRim;
    }

    public void setKeyWheelRim(WheelRim keyWheelRim) {
        this.keyWheelRim = keyWheelRim;
    }


}