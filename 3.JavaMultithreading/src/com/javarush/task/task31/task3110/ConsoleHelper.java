package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Admin on 28.10.2017.
 */
public class ConsoleHelper {

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        }
    }

    public static int readInt() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return new Integer(reader.readLine());
        }
    }
}
