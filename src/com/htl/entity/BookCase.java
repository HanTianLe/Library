package com.htl.entity;

public class BookCase {
    /** 用 Integer 的好处是不仅能接收数字，而且能接收null。*/
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookCase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
