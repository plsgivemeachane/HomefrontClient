package me.quclient.Client;

public class MainGameThread extends Thread {

    public static boolean isOnline;

    public void run(){
        new SnakeGame().run();
    }

}
