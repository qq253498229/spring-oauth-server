package com.example.oauthtest.user;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wangbin
 */
@Entity
@Table(name = "t_role")
@Data
public class Role implements Serializable {

  @Id
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @GeneratedValue(generator = "uuid")
  @Column(length = 32)
  private String id;

  @Column(nullable = false, unique = true)
  private String name;
}
