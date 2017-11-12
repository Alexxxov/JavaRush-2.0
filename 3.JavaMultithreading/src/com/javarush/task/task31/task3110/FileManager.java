package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.11.2017.
 */
public class FileManager {

    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) throws IOException {

        if (Files.isRegularFile(path)) {
            fileList.add(rootPath.relativize(path));
        } else if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);) {
                for (Path item : directoryStream) {
                    collectFileList(item);
                }
            }
        }

//        if(Files.isRegularFile(path))
//        {
//            fileList.add(rootPath.relativize(path));
//        }
//        if (Files.isDirectory(path)) {
//            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
//
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    fileList.add(file);
//                    return FileVisitResult.CONTINUE;
//                }
//
//            });
//        }

    }
}
