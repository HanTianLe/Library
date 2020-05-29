package com.htl.service.impl;

import com.htl.repository.AdminRepository;
import com.htl.repository.ReaderRepository;
import com.htl.repository.impl.AdminRepositoryImpl;
import com.htl.repository.impl.ReaderRepositoryImpl;
import com.htl.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();


    @Override
    public Object login(String username, String password,String type) {
        Object object = null;
        /** 这边判断是什么用户类型，然后再用相应的Repository处理。*/
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
                break;
        }
        return object;
    }
}

