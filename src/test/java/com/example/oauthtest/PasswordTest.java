package com.example.oauthtest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Package com.example.oauthtest
 * Module
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/14 下午6:41
 *
 * @author wangbin
 */
public class PasswordTest {
  private PasswordEncoder encoder;

  @Before
  public void setUp() {
    this.encoder = new BCryptPasswordEncoder();
  }

  @Test
  public void password() {
    System.out.println(encoder.encode("1"));
  }

  @Test
  public void secret() {
    System.out.println(encoder.encode("secret"));
  }
}
