package me.quclient.Packet;

public interface ClientPacket {

    default void log(){
        System.out.println("CLIENT : " + this.getClass().getSimpleName());
    }

}
