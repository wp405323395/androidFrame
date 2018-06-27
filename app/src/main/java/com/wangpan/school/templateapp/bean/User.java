package com.wangpan.school.templateapp.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String cupSize;
    private int age;

    public int getId() {
        return id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }
}
