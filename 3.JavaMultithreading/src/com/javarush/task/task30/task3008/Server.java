package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Admin on 03.03.2017.
 */
public class Server {

    public static void main(String[] args) {

        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            ConsoleHelper.writeMessage("Сервер запущен на порту:" + port);
            while (true)
            {
                Socket newSocket = serverSocket.accept();
                new Handler(newSocket).start();
            }
        }
        catch (IOException ioe)
        {
            ConsoleHelper.writeMessage(ioe.getMessage());
        }
    }



    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }

}
