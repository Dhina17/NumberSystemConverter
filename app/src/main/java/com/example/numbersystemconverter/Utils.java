package com.example.numbersystemconverter;

class Utils {

    String spiltInt(double number) {
        String value = Double.toString(number);
        int index = value.indexOf(".");
        String wholeNumber = value.substring(0, index);
        return wholeNumber;
    }

    String splitFraction(double number) {
        String value = Double.toString(number);
        int index = value.indexOf(".");
        String decimalPart = value.substring(index);
        return decimalPart;
    }

}
