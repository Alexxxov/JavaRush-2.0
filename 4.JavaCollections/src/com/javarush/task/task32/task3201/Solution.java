package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        String number = args[1];
        String text = args[2];

        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            long size = Long.parseLong(number);
            if (size > randomAccessFile.length()) {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(text.getBytes());
            }
            else
            {
                randomAccessFile.seek(Long.parseLong(number));
                randomAccessFile.write(text.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
