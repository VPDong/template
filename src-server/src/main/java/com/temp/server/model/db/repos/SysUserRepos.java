package com.temp.server.model.db.repos;

import com.temp.server.data.db.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepos extends JpaRepository<SysUser, String> {
    SysUser findByUsername(String username);
}
