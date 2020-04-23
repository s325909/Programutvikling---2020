package org.ccomp.model;


public class Validation {
    public static String valAdmin(String name, String price /*String quantity*/, String power, String color, String material,
                                  String side, String dimension, Object carType, Object quantity) {

        return validateName(name) + /*validateQuantity(quantity)*/  validatePrice(price) + validatePower(power) + validateColor(color)
                +validateMaterial(material) + validateSide(side) + validateDimension(dimension) + validateObjectCarType(carType) + validateObjectOnNull(quantity);
    }

    public static String valUser(String name, String email, String number, String Zip, String city) {

        return validateName(name) + validateEmail(email) + validateNumber(number) + validateZip(Zip) + validateCity(city);
    }

    public static String validateName(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]*";

        if (!input.matches(regex)) {
            lable = ("Navnet er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn navn på komponentet! \n");
        }
        return lable;
    }

  /*  public static String validateQuantity (String input) {
        String lable = "";

        String regex = "[0-9]\\d{0,5}";

        if (!input.matches(regex)) {
            lable = ("Feil format i antall komponenter!\n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn riktig antall komponenter! \n");
        }
        return lable;
    }*/

    public static String validatePrice (String input) {
        String lable = "";

        String regex = "[0-9]\\d{3,5}";

        if (!input.matches(regex)) {
            lable = ("Pris er skrevet inn i feil format!\n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn pris for komponentet! \n");
        }
        return lable;
    }

    public static String validatePower (String input) {
        String lable = "";

        String regex = "[0-9]\\d{0,3}";

        if (!input.matches(regex)) {
            lable = ("Antall hestekrefter er skrevet inn i feil format!\n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn antallet hestekrefter for komponent!\n");
        }
        return lable;
    }

    public static String validateColor(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]{3,10}";

        if (!input.matches(regex)) {
            lable = ("Farge er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn en farge på komponentet! \n");
        }
        return lable;
    }

    public static String validateMaterial(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]";

        if (!input.matches(regex)) {
            lable = ("Materiale er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn materiale på komponentet! \n");
        }
        return lable;
    }

    public static String validateSide(String input) {
        String lable = "";

        String regex = "(Høyre | høyre | Venstre | venstre)";

        if (!input.matches(regex)) {
            lable = ("Side på spoiler er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn monteringsside for komponentet! \n");
        }
        return lable;
    }

    public static String validateDimension(String input) {
        String lable = "";

        String regex = "[0-9]{2}| x | [0-9]{2}";

        if (!input.matches(regex)) {
            lable = ("Dimensjonen på felg er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn dimensjonen for komponentet! \n");
        }
        return lable;
    }

    public static String validateObjectOnNull(Object object) {
        String lable = "";
        if (object == null) {

            lable = ("Velg antall av samme komponenter du vil legge til! \n");
        }

        return lable;
    }

    public static String validateNumber(String input) {
        String lable = "";

        String regex = "[0-9]{8}";

        if (!input.matches(regex)) {
            lable = ("Mobilnummer er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn ditt mobilnummer! \n");
        }
        return lable;
    }

    public static String validateEmail(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ.-_]*[@][a-zæøåA-ZÆØÅ]*[.]{1}[a-zA-Z]{2,3}";

        if (!input.matches(regex)) {
            lable = ("E-postadresse er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn din e-postadresse! \n");
        }
        return lable;
    }

    public static String validateZip(String input) {
        String lable = "";

        String regex = "[0-9]{4}";

        if (!input.matches(regex)) {
            lable = ("Postnummer er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn ditt postnummer! \n");
        }
        return lable;
    }

    public static String validateCity(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]{2,25}";


        if (!input.matches(regex)) {
            lable = ("Poststed er skrevet inn i feil format! \n");
        } else if (input.isEmpty()) {
            lable = ("Vennligst fyll inn ditt poststed! \n");
        }
        return lable;
    }

    public static String validateObjectCarType(Object object) {
        String lable = "";

        if (object == null) {

            lable = ("Velg riktig biltype for motor!! \n");
        }

        return lable;
    }
}
