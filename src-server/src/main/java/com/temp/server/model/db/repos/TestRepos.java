package com.temp.server.model.db.repos;

import com.temp.server.data.db.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepos extends JpaRepository<Test, Long> {
    boolean existsByName(String name);
}
