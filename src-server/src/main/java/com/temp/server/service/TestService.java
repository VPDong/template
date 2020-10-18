package com.temp.server.service;

import com.temp.server.model.db.DBModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private DBModel mDBModel;

    public void test() {
        // todo
    }
}
