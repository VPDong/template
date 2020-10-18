package com.temp.server.model.db;

import com.temp.server.model.db.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBModel {
    @Autowired
    private TestDao mTestDao;

    public void test() {
        // todo
    }
}
