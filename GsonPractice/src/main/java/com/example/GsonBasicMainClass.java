package com.example;


import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class GsonBasicMainClass {
    public static void main(String[] args) {

        //1.json反序列化，使用JsonReader解析，数组的解析看doc
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

        System.out.println(person);

        //2.json序列化，使用JsonWriter处理，数组的解析看doc
        Person person1 = new Person("Moon", 25);
        person1.setEmail("moon@gmail.com");
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);

        try {
            jsonWriter.beginObject();
            jsonWriter.name("name").value(person1.getName())
                    .name("age").value(person1.getAge())
                    .name("email").value(person1.getEmail());
            jsonWriter.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jsonWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(stringWriter.toString());

    }
}
