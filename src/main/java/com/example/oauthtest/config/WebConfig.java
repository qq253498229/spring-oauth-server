package com.example.oauthtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC配置
 * Package com.example.oauthtest.config
 * Module oauth-test
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/14 上午3:26
 *
 * @author wangbin
 */
@SessionAttributes("authorizationRequest")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/oauth/confirm_access").setViewName("authorize");
  }
}
