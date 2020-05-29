package com.htl.repository;

import com.htl.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
