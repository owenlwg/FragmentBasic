package com.example;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Category {

    /**
     * id : 1
     * name : 电脑
     * children : [{"id":100,"name":"笔记本"},{"id":101,"name":"台式机"}]
     */
    @Expose
    private int id;
    private String name;
    @Expose
    private List<Category> children;

    //不希望被序列化和反序列化，不加@Expose注解
    private Category mCategory;


    public Category(int id, String name, List<Category> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", children=" + children +
                       '}';
    }
}
