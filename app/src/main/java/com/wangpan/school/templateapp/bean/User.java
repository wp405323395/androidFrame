package com.wangpan.school.templateapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = true)
    private long id;
    private String cupSize;
    private int age;
    @Generated(hash = 2031477216)
    public User(long id, String cupSize, int age) {
        this.id = id;
        this.cupSize = cupSize;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCupSize() {
        return this.cupSize;
    }
    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
