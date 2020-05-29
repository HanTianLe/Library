package com.htl.repository;

import com.htl.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int limit);
    public int count();
}
