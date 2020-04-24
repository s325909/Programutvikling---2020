package org.ccomp.user;

import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.component.CarComponent;
import org.ccomp.model.component.Seat;

import java.util.HashMap;
import java.util.List;

public class Order {



    private String name;
    private String color;
    private String price;
    private String material;
    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;




    public Order(String name , String color, String price, String material){
        this.name = name;
        this.color = color;
        this.price = price;
        this.material=material;
    }

    public void selectedItems(){
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("Seat");


    }






}
