package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        //first with extension then plain name without extension
        String fullName = fileName.split(".*[\\\\/](?=[^\\\\/]+$)")[1];
        String extension = "";
        if (fullName.contains("."))
            extension = fullName.split("\\.")[1];
        try
        {
            switch (extension)
            {
                case "xml":
                    FileInputStream in1 = new FileInputStream(fileName);
                    properties.loadFromXML(in1);
                    in1.close();
                    break;
                case "txt":
                    FileReader reader = new FileReader(fileName);
                    properties.load(reader);
                    reader.close();
                    break;
                default:
                    FileInputStream in2 = new FileInputStream(fileName);
                    properties.load(in2);
                    in2.close();
                    break;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            return properties;
        }
        return properties;
    }
}
