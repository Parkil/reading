package com.daekyo.boot_config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db1")
  public DB1Prop db1Prop() {
    return new DB1Prop();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.db2")
  public DB2Prop db2Prop() {
    return new DB2Prop();
  }
}
