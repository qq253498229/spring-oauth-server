package com.example.oauthtest.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangbin
 */
@Repository
public interface CustomClientDetailsDao extends JpaRepository<CustomClientDetails, String> {
  /**
   * 通过clientId查找client信息
   *
   * @param clientId clientId
   * @return client信息
   */
  @Query("select c from CustomClientDetails c where c.clientId=:clientId")
  CustomClientDetails findByClientId(@Param("clientId") String clientId);
}
