package com.example;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class GsonBasicMainClass {
    public static void main(String[] args) {
        //1.json解析
        String json = "{\"name\":\"owen\", \"age\":28, \"email_address\":\"xsxxx@163.com\"}";
        Person person = new Person();
        JsonReader reader = new JsonReader(new StringReader(json));

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                switch (key) {
                    case "name":
                        person.setName(reader.nextString());
                        break;
                    case "age":
                        person.setAge(reader.nextInt());
                        break;
                    case "email_address":
                        person.setEmail(reader.nextString());
                        break;
                }
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //2.

    }
}
