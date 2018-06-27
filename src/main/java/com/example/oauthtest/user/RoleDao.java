package com.example.oauthtest.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangbin
 */
public interface RoleDao extends JpaRepository<Role, String> {
}
