package me.quclient.Packet;

public interface ServerPacket {

    default void log(){
        System.out.println("SERVER : " + this.getClass().getSimpleName());
    }

}
