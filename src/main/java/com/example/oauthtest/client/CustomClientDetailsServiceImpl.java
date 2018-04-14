package com.example.oauthtest.client;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.example.oauthtest.consts.RedisKey.CLIENT_KEY;


/**
 * @author wangbin
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomClientDetailsServiceImpl implements ClientDetailsService {

  @Resource
  private CustomClientDetailsDao customClientDetailsDao;

  @Override
  @Cacheable(CLIENT_KEY)
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    return customClientDetailsDao.findByClientId(clientId);
  }

  @CachePut(CLIENT_KEY)
  public ClientDetails saveClientDetails(CustomClientDetails clientDetails) {
    return customClientDetailsDao.save(clientDetails);
  }
}
