package com.example;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;


public class SinceUntilModifierModel {
    @Since(1.1) //大于等于
    private String newId;
    @Until(1.1)  //小于
    private String id;

    public int publicField = 100;
    public static String publicStaticField = "publicStatic";
    public final String publicFinalField = "publicFinal";

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
