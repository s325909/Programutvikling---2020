package org.ccomp.admin;

import javafx.beans.property.SimpleStringProperty;
import sun.dc.pr.PRError;

public class Seatmidl {

        private SimpleStringProperty materiell,navn;

        public Seatmidl(String materiell,String navn){

            this.materiell = new SimpleStringProperty(materiell);
            this.navn = new SimpleStringProperty(navn);

        }


    public String getMateriell() {
        return materiell.get();
    }

    public SimpleStringProperty materiellProperty() {
        return materiell;
    }

    public void setMateriell(String materiell) {
        this.materiell.set(materiell);
    }

    public String getNavn() {
        return navn.get();
    }

    public SimpleStringProperty navnProperty() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }
}
