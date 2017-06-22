package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by Admin on 22.06.2017.
 */
public class Archiver {

    public static void main(String[] args) throws Exception {

        System.out.println("Enter  absolute path to archive");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String archivePath = "";
        archivePath = reader.readLine();

        ZipFileManager manager = new ZipFileManager(Paths.get(archivePath));

        System.out.println("Enter  absolute path to file to be archived");

        String filePath = "";
        filePath = reader.readLine();

        manager.createZip(Paths.get(filePath));
    }

}
