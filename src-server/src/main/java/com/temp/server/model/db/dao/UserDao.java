package com.temp.server.model.db.dao;

import com.temp.server.data.db.SysRole;
import com.temp.server.data.db.SysUser;
import com.temp.server.model.db.repos.SysRoleRepos;
import com.temp.server.model.db.repos.SysUserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Autowired
    private SysRoleRepos mSysRoleRepos;
    @Autowired
    private SysUserRepos mSysUserRepos;

    @PostConstruct
    private void initialize() {
        Map<String, String> data = new HashMap<>();
        data.put("admin", "ROLE_ADMIN");
        data.put("user", "ROLE_USER");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (mSysUserRepos.findByUsername(entry.getKey()) == null) {
                SysRole role = new SysRole();
                role.setName(entry.getValue());

                List<SysRole> roles = new ArrayList<>();
                roles.add(role);

                SysUser user = new SysUser();
                user.setUsername(entry.getKey());
                user.setPassword("test");
                user.setRoles(roles);

                mSysRoleRepos.save(role);
                mSysUserRepos.save(user);
            }
        }
    }

    public SysUser findByUsername(String username) {
        return mSysUserRepos.findByUsername(username);
    }
}
