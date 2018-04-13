package com.example.oauthtest.config;

import com.example.oauthtest.user.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.annotation.Resource;

/**
 * Package com.example.oauthtest.config
 * Module
 * Project oauth-test
 * Email 253498229@qq.com
 * Created on 2018/4/13 下午8:56
 *
 * @author wangbin
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Resource
  private UserServiceImpl userService;

  @Resource
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
            .withClient("client")
            .secret("secret")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .scopes("app")
    ;
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    //这里的userDetailsService是为了refresh_token
    endpoints.authenticationManager(authenticationManager).userDetailsService(userService);
  }

}
