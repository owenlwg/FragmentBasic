package com.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class PersonTypeAdapter extends TypeAdapter<Person> {
    @Override
    public void write(JsonWriter out, Person person) throws IOException {
        out.beginObject()
           .name("name_typeAdapter").value(person.getName())
           .name("age_typeAdapter").value(person.getAge())
           .name("email_typeAdapter").value(person.getEmail())
           .endObject();
    }

    @Override
    public Person read(JsonReader in) throws IOException {
        Person person = new Person();
        in.beginObject();
        while (in.hasNext()) {
            String key = in.nextName();
            if ("name".equals(key)) {
                person.setName(in.nextString());
            } else if ("age".equals(key)) {
                person.setAge(in.nextInt());
            } else if ("email".equals(key)) {
                person.setEmail(in.nextString());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return person;
    }
}
