package org.ccomp.model;

public class Validation {
    public static String valAdminEngine(String enginename, String engineprice, String enginequantity, String enginepower) {

        return validateName(enginename) + validateQuantity(enginequantity) + validatePrice(engineprice) + validatePower(enginepower);
    }

    public static String valAdminSeat (String seatname, String seatmaterial, String seatcolor, String seatprice, String seatquantity) {

        return validateName(seatname) + validateMaterial(seatmaterial) + validateColor(seatcolor) + validatePrice(seatprice) + validateQuantity(seatquantity);
    }

    public static String valAdminSpiler (String spoilername, String spoilercolor, String spoilerside, String spoilerprice, String spoilerquantity) {

        return validateName(spoilername) + validateColor(spoilercolor) + validateSide(spoilerside) + validatePrice(spoilerprice) + validateQuantity(spoilerquantity);
    }

    public static String valAdminSteering (String steeringname, String steeringmaterial, String steeringcolor, String steeringprice, String steeringquantity) {

        return validateName(steeringname) + validateMaterial(steeringmaterial) + validateColor(steeringcolor) + validatePrice(steeringprice) + validateQuantity(steeringquantity);
    }

    public static String valAdminRim (String rimname, String rimcolor, String rimdimension, String rimprice, String rimquantity) {

        return validateName(rimname) + validateColor(rimcolor) + validateDimension(rimdimension) + validatePrice(rimprice) + validateQuantity(rimquantity);
    }

    public static String valUser(String name, String email, String number, String Zip, String city) {

        return validateName(name) + validateEmail(email) + validateNumber(number) + validateZip(Zip) + validateCity(city);
    }

    public static String validateName(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]*";

        if (!input.matches(regex)) {
            lable = ("Navnet er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateQuantity (String input) {
        String lable = "";

        String regex = "[1-9][0-9]{1,10}";

        if (!input.matches(regex)) {
            lable = ("Feil format i antall komponenter!\n");
        }
        return lable;
    }

    public static String validatePrice (String input) {
        String lable = "";

        String regex = "^([1-9][0-9])\\d{1,5}?([.]\\d{1,2})?";

        if (!input.matches(regex)) {
            lable = ("Pris er skrevet inn i feil format!\n");
        }
        return lable;
    }

    public static String validatePower (String input) {
        String lable = "";

        String regex = "^[1-9][0-9]{3}";

        if (!input.matches(regex)) {
            lable = ("Antall hestekrefter er skrevet inn i feil format!\n");
        }
        return lable;
    }

    public static String validateColor(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]{3,10}";

        if (!input.matches(regex)) {
           lable  = ("Farge er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateMaterial(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]{1,10}";

        if (!input.matches(regex)) {
            lable = ("Materiale er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateSide(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]*";

        if (!input.matches(regex)) {
            lable = ("Side på spoiler er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateDimension(String input) {
        String lable = "";

        String regex = "^[a-zA-Z0-9]+$";

        if (!input.matches(regex)) {
            lable = ("Dimensjonen på felg er skrevet inn i feil format! \n");
        }
        return lable;
    }


    public static String validateNumber(String input) {
        String lable = "";

        String regex = "[0-9]{8}";

        if (!input.matches(regex)) {
            lable = ("Mobilnummer er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateEmail(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ.-_]*[@][a-zæøåA-ZÆØÅ]*[.]{1}[a-zA-Z]{2,3}";

        if (!input.matches(regex)) {
            lable = ("E-postadresse er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateZip(String input) {
        String lable = "";

        String regex = "[0-9]{4}";

        if (!input.matches(regex)) {
            lable = ("Postnummer er skrevet inn i feil format! \n");
        }
        return lable;
    }

    public static String validateCity(String input) {
        String lable = "";

        String regex = "[a-zæøåA-ZÆØÅ]{2,25}";

        if (!input.matches(regex)) {
            lable = ("Poststed er skrevet inn i feil format! \n");
        }
        return lable;

    }
}
