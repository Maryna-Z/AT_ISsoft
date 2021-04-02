package com.marina.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marina.exception.CommonException;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static com.marina.constants.Constants.UTF_8;

public class Utils {

    public static int generateRandomValue(int min, int max){
        return (int)(min + Math.random() * max);
    }

    public static int getIntFromConsole(InputStream inputStream){
        Scanner in = new Scanner(inputStream);
        System.out.println("Input product id: ");
        return in.nextInt();
    }

    public static String  convertToJSON(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new CommonException("Error to convert to JSON", ex);
        }
    }

    public static <T> T convertFromJSON(String string, Class<T> classOf){
        try {
            return new ObjectMapper().readValue(string, classOf);
        } catch (JsonProcessingException ex) {
            throw new CommonException("Error to convert to JSON", ex);
        }
    }

    public static String getStringBody(InputStream inputStream) throws IOException {
        return IOUtils.toString(inputStream, UTF_8);
    }
}
