package com.example.numbersystemconverter;


public class Converter {

    static String finalResult ="";
    static String returnResult;

    public String convert(int userInputNumber, int radix) {


        int quotient = userInputNumber / radix;
        int remainder = userInputNumber % radix;
        userInputNumber = quotient;

        if (quotient >= radix) {
            convert(userInputNumber, radix);
        }

        int[] rem = {remainder};

        for (int i : rem) {
            if (radix < 16) {
                if (quotient < radix) {
                    returnResult=finalResult.concat(String.valueOf(quotient));
                    returnResult+=finalResult.concat(String.valueOf(i));
                } else {

                    returnResult+=finalResult.concat(String.valueOf(i));
                }
            }else{
                if(quotient<16){
                    returnResult=finalResult.concat(numToAlpha(quotient));
                    returnResult+=finalResult.concat(numToAlpha(i));
                }else{
                    returnResult+=finalResult.concat(numToAlpha(i));
                }

            }
        }
        return returnResult;
    }

    private String numToAlpha(int i) {
        if(i == 10){
            return "A";
        }else if(i == 11) {
            return "B";
        }else if(i == 12) {
            return "C";
        }else if(i == 13) {
            return "D";
        }else if(i == 14) {
            return "E";
        }else if (i == 15) {
            return "F";
        }else{
            return String.valueOf(i);
        }
    }

}
