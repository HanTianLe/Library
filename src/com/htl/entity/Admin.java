package com.htl.entity;

public class Admin {
    /** 用 Integer 的好处是不仅能接收数字，而且能接收null。*/
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Admin(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
