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
            ConsoleHelper.writeMessage("Сервер запущен на порту: " + port);
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
        for (Connection connection :connectionMap.values())
            try
            {
                connection.send(message);
            }
            catch (IOException ioe)
            {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения клиенту");
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

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (Map.Entry<String, Connection> entry: connectionMap.entrySet())
            {
                if (entry.getKey().equals(userName))
                    continue;
                Message msg = new Message(MessageType.USER_ADDED, entry.getKey());
                connection.send(msg);
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message userMsg = connection.receive();
                if (userMsg.getType() == MessageType.TEXT)
                {
                    Message toAllMsg = new Message(MessageType.TEXT, userName + ": " + userMsg.getData());
                    sendBroadcastMessage(toAllMsg);
                }
                else
                    ConsoleHelper.writeMessage(String.format("Ошибка! Недопустимый тип сообщения (MessageType.%s) от клиента: %s",
                                    userMsg.getType().toString() ,userName));
            }
        }

        public void run()
        {
            ConsoleHelper.writeMessage("Установлено соединение с удаленным клиентом с адресом: " + socket.getRemoteSocketAddress());
            String userName = null;

            try(Connection connection = new Connection(socket))
            {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage(String.format("Произошл ошибка при передачи данных с удаленным адресом %s.", socket.getRemoteSocketAddress()));
            }

            if (userName != null)
            {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage(String.format("Соединение с удаленным адресом (%s) закрыто.", socket.getRemoteSocketAddress()));
        }
    }

}
