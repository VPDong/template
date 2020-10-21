package com.temp.server.model.db;

import com.temp.server.model.db.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBModel {
    @Autowired
    private UserDao mUserDao;

    public void exec() {
        // todo
    }
}
