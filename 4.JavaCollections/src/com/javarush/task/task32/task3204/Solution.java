package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        int len = 8;
        String abcs = "abcdefghijklmnopqrstuvwxyz";
        String digs = "1234567890";
        String[] myArr = {abcs, digs, abcs.toUpperCase()};
        StringBuilder res = new StringBuilder();

        Random rnd = new Random();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int randomNum;
        char[] arr;
        while (0 < len--)
        {
            randomNum = ThreadLocalRandom.current().nextInt(0, myArr.length);

            if (len > 2)
                arr = myArr[randomNum].toCharArray();
            else
                arr = myArr[len].toCharArray();

            res.append(arr[rnd.nextInt(arr.length-1)]);
        }
        baos.write(res.toString().getBytes());
        baos.close();
        return baos;
    }
}