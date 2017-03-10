package com.javarush.task.task30.task3008.client;

/**
 * Created by Admin on 10.03.2017.
 */
public class BotClient extends Client{

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread
    {

    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random() * 100);
    }
}
