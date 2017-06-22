package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Admin on 03.03.2017.
 */
public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString()
    {
        String result;
        while (true)
        {
            try
            {
                result = reader.readLine();
                break;
            }
            catch (IOException ioe)
            {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                continue;
            }
        }
        return result;
    }

    public static int readInt()
    {
        int number;
        while (true)
        {
            try
            {
                number = Integer.parseInt(readString());
                break;
            }
            catch (NumberFormatException ioe)
            {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                continue;
            }
        }
        return number;

    }

}
