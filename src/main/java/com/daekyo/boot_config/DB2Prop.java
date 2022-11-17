package com.daekyo.boot_config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "password")
@Setter
@Getter
public class DB2Prop {
  private String driverClassName;
  private String url;
  private String username;
  private String password;
}
