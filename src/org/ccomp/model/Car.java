package org.ccomp.model;

import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

public class Car {

    private Engine carEngine;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private String seteTrekk;

    public Car(Engine engine) {
        this.carEngine = engine;
    }

    public Car(SteeringWheel steeringWheel){
        this.steeringWheel = steeringWheel;
    }

    public Car(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public void builCarEngine() {

        if (carEngine instanceof GasolineEngine) ((GasolineEngine) carEngine).printEngine();
        else if (carEngine instanceof ElectricMotor) ((ElectricMotor) carEngine).printEngine();
        else if (carEngine instanceof HybridEngine) ((HybridEngine) carEngine).printEngine();

        /*
        if (carEngine.isCombustion()) {
            System.out.println("BENSIN BIL");
        } else if (carEngine.isElectric()) {
            System.out.println("EL BIL");
            //electricMotor = new ElectricMotor();
            electricMotor.print();
        } else if (carEngine.isCombustion() && carEngine.isElectric()) {
            System.out.println("HYBRID BIL");
        }
         */
    }

}
