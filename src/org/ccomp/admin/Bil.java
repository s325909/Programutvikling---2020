package org.ccomp.admin;

import javafx.scene.control.ToggleGroup;

public class Bil {

    //Biltype

    private String bilType;
    private String bensin;
    private String elBil;
    private String hybrid;

    //Komponenter

    private  String komponenter;
    private String motor;
    private String setetrekk;
    private String felg;


    //For motor

    private String navn;
    private String hestekrefter;
    private String pris;

    public Bil( String bilType, String komponenter, String navn, String hestekrefter, String pris){
         this.bilType = bilType;
         this.komponenter = komponenter;
         this.navn = navn;
         this.hestekrefter = hestekrefter;
         this.pris = pris;


    }

    public String getBilType() { return bilType; }

    public String getKomponenter() { return komponenter; }

    public String getNavn() {
        return navn;
    }

    public String getHestekrefter() {
        return hestekrefter;
    }


    public String getPris() {
        return pris;
    }

}
