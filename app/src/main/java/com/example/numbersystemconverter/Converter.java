package com.example.numbersystemconverter;


class Converter {

    private static String returnResult;
    private Utils util = new Utils();

    String wholeNumberPartConverter(int userInputNumber, int radix) {
        int quotient = userInputNumber / radix;
        int remainder = userInputNumber % radix;
        userInputNumber = quotient;

        if (quotient >= radix) {
            wholeNumberPartConverter(userInputNumber, radix);
        }

        int[] rem = {remainder};

        for (int i : rem) {
            String finalResult = "";
            if (radix < 16) {
                if (quotient < radix) {
                    returnResult= finalResult.concat(String.valueOf(quotient));
                    returnResult+= finalResult.concat(String.valueOf(i));
                } else {

                    returnResult+= finalResult.concat(String.valueOf(i));
                }
            }else{
                if(quotient<16){
                    returnResult= finalResult.concat(numToAlpha(quotient));
                    returnResult+= finalResult.concat(numToAlpha(i));
                }else{
                    returnResult+= finalResult.concat(numToAlpha(i));
                }

            }
        }
        return returnResult;
    }

    String decimalPartConverter(double value,int radix) {
        String result = "";
        for (int i = 0; i < 10; i++) {
            if (value == 0) {
                break;
            } else {
                double q = value * radix;
                value = Double.parseDouble(util.splitFraction(q));

                if (radix == 16) {
                    result += numToAlpha(Integer.parseInt(util.spiltInt(q)));
                } else {
                    result += util.spiltInt(q);
                }

            }
        }
        return result;
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
