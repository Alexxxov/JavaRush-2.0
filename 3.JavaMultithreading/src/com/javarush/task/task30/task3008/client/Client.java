package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

/**
 * Created by Admin on 09.03.2017.
 */
public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public class SocketThread extends Thread
    {
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message msg = connection.receive();
                MessageType type = msg.getType();
                if (type != MessageType.NAME_REQUEST && type != MessageType.NAME_ACCEPTED)
                    throw new IOException("Unexpected MessageType");

                switch (type)
                {
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    case NAME_REQUEST:
                        connection.send(new Message(MessageType.USER_NAME, getUserName()));
                        break;
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message msg = connection.receive();
                MessageType type = msg.getType();
                if (type != MessageType.TEXT && type != MessageType.USER_ADDED && type != MessageType.USER_REMOVED)
                    throw new IOException("Unexpected MessageType");

                switch (type)
                {
                    case TEXT:
                        processIncomingMessage(msg.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(msg.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(msg.getData());
                        break;
                }
            }
        }
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        try
        {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка при отправке сообщения. Соединение будет закрыто.");
            clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                this.wait();
            }
        }
        catch (InterruptedException ie)
        {
            ConsoleHelper.writeMessage("Выход из программы по ошибке.");
            return;
        }

        if (clientConnected)
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
        else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

        String textMsg;
        while (clientConnected)
        {
            textMsg = ConsoleHelper.readString();
            if (textMsg.toLowerCase().equals("exit"))
                break;
            if (shouldSendTextFromConsole())
                sendTextMessage(textMsg);
        }
    }
}
