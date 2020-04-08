package org.ccomp.model;

import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;
import org.ccomp.model.component.WheelRim;
import org.ccomp.model.component.engine.Engine;

public class MapKey {

    private Engine keyEngine;
    private Seat seat;
    private Spoiler keySpoiler;
    private SteeringWheel keySteeringWheel;
    private WheelRim keyWheelRim;

    public Engine getKeyEngine() {
        return keyEngine;
    }

    public void setKeyEngine(Engine keyEngine) {
        this.keyEngine = keyEngine;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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


    //todo:
    // Create first map entry with key <A,B>.
    // MapKey mapKey1 = new MapKey();
    // mapKey1.setKey1("A");
    // mapKey1.setKey2("B");

}