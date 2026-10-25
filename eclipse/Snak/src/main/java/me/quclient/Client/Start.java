package me.quclient.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.activation.Server;
import me.quclient.Packet.Client.C00PingPacket;
import me.quclient.Packet.ServerPacket;
import me.quclient.Utils.Parsee;

import java.io.*;
import java.net.*;


public class Start {

    public static void logging(String s){
        System.out.println(s);
    }



    public static void main(String[] args) {
        new GameThread().start();
    }

}
