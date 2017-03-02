package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        boolean entered = false;
        for (int i = 2; i < 37; i++)
        {
            try
            {
                if (entered)
                    break;
                new BigInteger(args[0],i);
                entered = true;
                System.out.println(i);
            }
            catch(NumberFormatException e)
            {

            }
        }
        if (!entered)
            System.out.println("incorrect");
    }
}