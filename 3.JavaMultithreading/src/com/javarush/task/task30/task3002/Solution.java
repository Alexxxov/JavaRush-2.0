package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }


    //CЛОМАН ВАЛИДАТОР - НЕ ПРИНИМАЕТ
    public static String convertToDecimalSystem(String s) {
        if (s.matches("^0x.*"))
        {
            s = s.substring(2);
            return String.valueOf(Integer.parseInt(s,16));
        }
        else if (s.matches("^0b.*"))
        {
            s = s.substring(2);
            return String.valueOf(Integer.parseInt(s,2));
        }
        else if (s.matches("^0.*"))
        {
            s = s.substring(1);
            return String.valueOf(Integer.parseInt(s,8));
        }
        else
            return s;
    }
}
