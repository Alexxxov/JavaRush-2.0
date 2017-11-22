package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Admin on 28.10.2017.
 */
public abstract class ZipCommand implements Command{

    public ZipFileManager getZipFileManager() throws Exception {
        ConsoleHelper.writeMessage("Введи полный пути файла архива");
        Path archivePath = Paths.get(ConsoleHelper.readString());
        return new ZipFileManager(archivePath);
    }
}
