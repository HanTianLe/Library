package com.htl.repository;

import com.htl.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
