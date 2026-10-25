package me.quclient.Server;

import java.io.*;
import java.net.*;

public class Start {
    public static void main(String[] args){
        System.out.println("Starting...");

        try {
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server Started");
            while(true){
                try {
                    Socket client = ss.accept();
                    System.out.println("Client Connect... Start thread!");
                    new ServerThread(client).start();
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        } catch(Exception e){System.out.println(e);}
    }


}
