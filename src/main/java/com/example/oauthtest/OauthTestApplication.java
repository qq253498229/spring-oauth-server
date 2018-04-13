package com.example.oauthtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author wangbin
 */
@SpringBootApplication
@EnableResourceServer
public class OauthTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthTestApplication.class, args);
  }
}
