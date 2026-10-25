package me.quclient.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.quclient.Packet.Client.C00PingPacket;
import me.quclient.Packet.ClientPacket;
import me.quclient.Packet.Packet;
import me.quclient.Packet.Server.S00PacketPong;
import me.quclient.Utils.Parsee;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    protected Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void log(String mess){
        System.out.println(mess);
    }

    public void run(){

        log("Thread UP");

        InputStream in = null;
        BufferedReader br = null;
        OutputStream out = null;
        BufferedWriter writer;

        try {
            in = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            out = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(out));

            String line;

            while(true) {
                line = br.readLine();

                if(line == null) continue;

                if(line.equalsIgnoreCase("QUIT")){
                    in.close();
                    out.close();
                    socket.close();
                    log("Client disconnected");
                    return;
                } else {
                    //log("Recv Message");
                    //log(line);

                    Parsee p = new Parsee();

                    ClientPacket o = (ClientPacket) p.Parsees(line);

                    if(o instanceof C00PingPacket){
                        C00PingPacket c = (C00PingPacket)o;
                        c.log();
                        S00PacketPong pong = new S00PacketPong(c.getKey());
                        pong.log();
                        System.out.println(pong.toString());
                        writer.write(pong.toString() + "\n");
                        writer.flush();
                    }

                }

            }

        } catch (IOException e) {
            System.out.println("Client Disconnected due to crash?");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
