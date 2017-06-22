package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("D:/MyDownloads12"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        String fileName = urlString.substring(urlString.lastIndexOf("/")+1);
        Path resultFile = Paths.get(downloadDirectory + "/" + fileName);

        URL url = new URL(urlString);
        InputStream in = url.openStream();
//        String fileName = urlString.replaceAll(".*/(?=[-\\w]+\\.\\w{3,4})","");
//        String[] prefSuf = fileName.split("\\.");

        Path tempFile = Files.createTempFile(null,null);

        //add StandardCopyOption.REPLACE_EXISTING to see result
        Files.copy(in,tempFile);

        in.close();

        //add StandardCopyOption.REPLACE_EXISTING to see result
        Files.move(tempFile,resultFile);

        return resultFile;
    }
}
