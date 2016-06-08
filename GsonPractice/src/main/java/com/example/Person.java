package com.example;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

@JsonAdapter(PersonTypeAdapter.class)
public class Person {
    /**
     * name : 小李飞刀
     * age : 28
     * Email : x_spoil@163.com
     */

    private String name;
    private int age;
    @SerializedName(value = "email", alternate ={"email_address"})
    private String Email;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                       "name='" + name + '\'' +
                       ", age='" + age + '\'' +
                       ", Email='" + Email + '\'' +
                       '}';
    }
}
