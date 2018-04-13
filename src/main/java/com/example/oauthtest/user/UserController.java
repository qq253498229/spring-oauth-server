package com.example.oauthtest.user;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Package com.example.oauthtest.user
 * Module
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/13 下午11:15
 *
 * @author wangbin
 */
@RestController
public class UserController {
  @GetMapping("/user")
  public Object getUser(Principal principal) {
    return ((OAuth2Authentication) principal).getUserAuthentication();
  }
}
