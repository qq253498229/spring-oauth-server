package com.example.oauthtest.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Package com.example.oauthtest.user
 * Module
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/13 下午11:15
 *
 * @author wangbin
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserDetailsService {
  @Resource
  private UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDao.findByUsername(username);
  }
}
