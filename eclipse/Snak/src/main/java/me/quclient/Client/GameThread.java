package me.quclient.Client;

import me.quclient.Packet.Client.C00PingPacket;
import me.quclient.Packet.ServerPacket;
import me.quclient.Utils.Parsee;
import sun.applet.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GameThread extends Thread {

    public static void logging(String s){
        System.out.println(s);
    }

    public long mili = 1000;
    public boolean started;

    public void run(){
        try{
            Socket s=new Socket("localhost",6666);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            BufferedWriter dout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            int key = 0;
            while(true){

                //logging("Start Sending Packet");

                Thread.sleep(mili);

//                if(key > 10){
//                    dout.write("QUIT");
//                    break;
//                }

                C00PingPacket ping = new C00PingPacket(key);

                //ping.log();

                //logging("Sending Packet");

                dout.write(ping.toString() + "\n");

                long start = System.currentTimeMillis();

                dout.flush();

                //logging("Sending Packet SUCCESS");

                String line = in.readLine();

                long end = System.currentTimeMillis();

                logging("Recived Packet Ping : " + (end - start) + "ms");

                if(line == null) throw new RuntimeException("WHYYYYYYYYY");

                Parsee parsee = new Parsee();

                ServerPacket packet = (ServerPacket) parsee.Parsees(line);

                //packet.log();

                if(!started) {
                    started = true;
                    System.out.println("Server connect Started game");
                    new MainGameThread().start();
                } else {
                    if(MainGameThread.isOnline){
                        logging("Snake Deed disconnected");
                        break;
                    }
                }

                key++;
            }

            dout.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
    }

}
