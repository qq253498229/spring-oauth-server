package com.example.oauthtest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Package com.example.oauthtest.user
 * Module
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/13 下午11:14
 *
 * @author wangbin
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
  User findByUsername(String username);
}
