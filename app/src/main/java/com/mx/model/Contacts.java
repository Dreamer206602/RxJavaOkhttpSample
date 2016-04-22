package com.mx.model;

/**
 * Created by boobooL on 2016/4/21 0021
 * Created 邮箱 ：boobooMX@163.com
 */
public class Contacts {
    private String name;

    public Contacts(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
