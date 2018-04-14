package com.example.oauthtest.config;

import com.example.oauthtest.client.CustomClientDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * 认证服务器配置
 * Package com.example.oauthtest.config
 * Module oauth-test
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
  private CustomClientDetailsServiceImpl customClientDetailsService;
  @Resource
  private AuthenticationManager authenticationManager;
  @Resource
  private RedisConnectionFactory redisConnectionFactory;


  @Bean
  public TokenStore getTokenStore() {
    return new RedisTokenStore(redisConnectionFactory);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(customClientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    //这里的userDetailsService是为了refresh_token
    endpoints.authenticationManager(authenticationManager).tokenStore(getTokenStore());
  }

}
