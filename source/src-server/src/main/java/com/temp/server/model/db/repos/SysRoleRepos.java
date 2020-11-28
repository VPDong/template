package com.temp.server.model.db.repos;

import com.temp.server.data.db.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepos extends JpaRepository<SysRole, String> {
}
