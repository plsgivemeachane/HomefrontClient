package me.quclient.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parsee {

    public Object Parsees(String json) throws ClassNotFoundException, JsonProcessingException {
        ObjectMapper op = new ObjectMapper();
        String[] arr = json.split(";");
        if(arr.length != 2) throw new IllegalStateException("Args not enough default=2");

        return op.readerFor(Class.forName(arr[0])).readValue(arr[1]);
    }

}
