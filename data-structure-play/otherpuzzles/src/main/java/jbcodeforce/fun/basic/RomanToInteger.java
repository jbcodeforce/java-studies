
package jbcodeforce.fun.basic;

import org.junit.jupiter.api.Assertions;

public class RomanToInteger {

    /**
     * 1 <= s.length <= 15
     * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
     * s is valid roman number
     * I can be placed before V (5) and X (10) to make 4 and 9. 
        X can be placed before L (50) and C (100) to make 40 and 90. 
        C can be placed before D (500) and M (1000) to make 400 and 900.
     */
    public static int parse(String s) {
        int value = 0;
        for (int i = s.length()-1; i >= 0 ; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'M':
                    value += 1000;
                    break;
                case 'D':
                    value += 500;
                    break;
                case 'C':
                    value = processC(s, i, value);
                    break;
                case 'L':
                    value +=50;
                    break;
                case 'X':
                    value = processX(s, i, value);
                    break;
                case 'V':
                    value +=5;
                    break;
                case 'I':
                    value = processI(s,i,value);
                    break;
            }

        }
        return value;
    }

    /**
     *  I can be placed before V (5) and X (10) to make 4 and 9. 
     * @param s roman string
     * @param i index in string with char is I
     * @param value accumulated value so far
     * @return new value with X mapped to 1, 4 or 9
     */
    public static int processI(String s,int i,int value){
        if (i != s.length() - 1) {
            switch(s.charAt(i+1)) {
                case 'V':
                case 'X':
                    value -= 1;
                    break;
                default:
                    value +=1 ;
            }
        } else {
            value +=1;
        }
        return value;
    }

    /**
     *  X can be placed before L (50) and C (100) to make 40 and 90. 
     * @param s roman string
     * @param i index in string with char is X
     * @param value accumulated value so far
     * @return new value with X mapped to 10, 40 or 90
     */
    public static int processX(String s,int i,int value){
        if (i != s.length() - 1) {
            switch(s.charAt(i+1)) {
                case 'L':
                case 'C':
                    value -= 10;
                    break;
                default:
                    value +=10;
            }
        } else {
            value +=10;
        }
        return value;
    }

    /**
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * @param s roman string
     * @param i index in string with char is X
     * @param value accumulated value so far
     * @return new value with C mapped to 100, 400 or 900
     */
    public static int processC(String s,int i,int value){
        if (i != s.length() - 1) {
            switch(s.charAt(i+1)) {
                case 'D':
                case 'M':
                    value -= 100;
                    break;
                default:
                    value +=100;
            }
        } else {
            value +=100;
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(parse("M"));
        Assertions.assertEquals(1000, parse("M"));
        Assertions.assertEquals(1500, parse("MD"));
        Assertions.assertEquals(58, parse("LVIII"));
        Assertions.assertEquals(1, parse("I"));
        Assertions.assertEquals(2, parse("II"));
        Assertions.assertEquals(4, parse("IV"));
        Assertions.assertEquals(9, parse("IX"));
        Assertions.assertEquals(10, parse("X"));
        Assertions.assertEquals(40, parse("XL"));
        Assertions.assertEquals(90, parse("XC"));
        Assertions.assertEquals(59, parse("LIX"));
        Assertions.assertEquals(1994, parse("MCMXCIV"));
    }
}