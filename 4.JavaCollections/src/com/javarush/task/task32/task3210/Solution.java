package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        String number = args[1];
        String text = args[2];

        try(RandomAccessFile file = new RandomAccessFile(fileName,"rw"))
        {
            byte[] buf = new byte[text.length()];
            file.seek(Long.parseLong(number));
            file.read(buf,0, buf.length);
            String str = convertByteToString(buf);
            file.seek(file.length());
            if (str.equals(text))
                file.write("true".getBytes());
            else
                file.write("false".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertByteToString (byte readBytes[]) {
        return new String(readBytes);
    }
}
