package org.ccomp.model;


public class Validation {
    public static String valAdmin(String name, String price, String quantity, String power, String color, String material,
                                  String side, String dimension, Object obj) {

        return validateName(name);

                //validateObjectOnNull(obj);
    }

    public static String validateName(String input) {
        String field = "";

        String adminRegex = "[a-zæøåA-ZÆØÅ]*";
        if (!input.matches(adminRegex)) {
            field = ("Navnet er i feil format! \n");
        } else if (input.isEmpty()) {
            field = ("Vennligst fyll inn navn på komponentet! \n");
        }
        return field;
    }

}
