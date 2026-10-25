package me.quclient.Packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;


public class Packet {

    public boolean isCanncel = false;
    public long timer = System.currentTimeMillis();

    @Override
    public String toString(){

        ObjectMapper op = new ObjectMapper();


        try {
            String json = op.writeValueAsString(this);
            return this.getClass().getName() + ";" + json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // This cant be happend!

    }


}
