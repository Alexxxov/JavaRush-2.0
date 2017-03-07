package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Admin on 03.03.2017.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {

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

    public static void sendBroadcastMessage(Message message)
    {
        for (Connection con :connectionMap.values())
            try
            {
                con.send(message);
            }
            catch (IOException ioe)
            {
                ConsoleHelper.writeMessage("Exception occured, message cant be sent.");
            }
    }

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String clientName;
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message msg = connection.receive();
                if (msg.getType() == MessageType.USER_NAME)
                {
                    clientName = msg.getData();
                    if (clientName != null && !clientName.isEmpty())
                        if (!(connectionMap.containsKey(clientName)))
                        {
                            connectionMap.put(clientName, connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            break;
                        }
                }
            }
            return clientName;
        }
    }

}
