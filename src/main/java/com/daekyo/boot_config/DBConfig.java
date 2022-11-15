package com.daekyo.boot_config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.daekyo.repo", // jpa repository 위치 지정
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager"
)
@RequiredArgsConstructor
public class DBConfig implements WebMvcConfigurer {

  private final DB1Prop db1Prop;

  private final DB2Prop db2Prop;

  @Bean("DB1")
  public DataSource writerDataSource() {
    System.out.println("DB1Prop : "+db1Prop);
    return createDataSource("jdbc:mysql://localhost:3306/test");
  }

  @Bean("DB2")
  public DataSource readerDataSource() {
    System.out.println("DB2Prop : "+db2Prop);
    return createDataSource("jdbc:mysql://localhost:3307/test");
  }

  @Bean("routingDataSource")
  public DataSource routingDataSource(
      @Qualifier("DB1") DataSource db1,
      @Qualifier("DB2") DataSource db2) {
    ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();
    Map<Object, Object> dataSourceMap = new HashMap<>();

    //determineCurrentLookupKey 에서 반환되는 값과 동일하게 설정
    dataSourceMap.put("DB1", db1);
    dataSourceMap.put("DB2", db2);

    replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
    replicationRoutingDataSource.setDefaultTargetDataSource(db1);
    replicationRoutingDataSource.afterPropertiesSet(); // 이부분이 지정이 안되면 오류가 발생한다

    return new LazyConnectionDataSourceProxy(replicationRoutingDataSource);
  }

  // datasource 를 따로 설정하면 jpa entity / transaction 을 별도로 설정해 주어야 함
  @Bean("mysqlEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier("routingDataSource") DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactory
        = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setPackagesToScan("com.daekyo.entity");
    entityManagerFactory.setJpaVendorAdapter(this.jpaVendorAdapter());
    entityManagerFactory.setJpaProperties(this.jpaProperties());
    entityManagerFactory.setPersistenceUnitName("mysqlEntityManager");
    return entityManagerFactory;
  }

  private JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setGenerateDdl(true);
    hibernateJpaVendorAdapter.setShowSql(true);
    hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
    return hibernateJpaVendorAdapter;
  }

  private Properties jpaProperties() {
    Properties jpaProperties = new Properties();
    jpaProperties.setProperty("hibernate.show_sql", "true");
    jpaProperties.setProperty("hibernate.format_sql", "true");
    jpaProperties.setProperty("hibernate.highlight_sql", "true");
    jpaProperties.setProperty("hibernate.use_sql_comments", "true");
    return jpaProperties;
  }

  @Bean("mysqlTransactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("mysqlEntityManagerFactory")
      LocalContainerEntityManagerFactoryBean entityManagerFactory
  ) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return jpaTransactionManager;
  }


  private DataSource createDataSource(String url) {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    hikariDataSource.setUsername("root");
    hikariDataSource.setPassword("aaa000");
    hikariDataSource.setJdbcUrl(url);
    hikariDataSource.setMaximumPoolSize(2);

    return hikariDataSource;
  }
}
