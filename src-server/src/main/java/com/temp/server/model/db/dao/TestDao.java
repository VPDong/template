package com.temp.server.model.db.dao;

import com.temp.server.model.db.repos.TestRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class TestDao {
    @Autowired
    private TestRepos mTestRepos;

    public void test() {
        // todo
    }
}
