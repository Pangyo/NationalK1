package org.inspection.nationalk1.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Contains database configurations.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
  @Autowired
  private Environment env;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean entityManagerFactory;


  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));
    return dataSource;
  }

//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//    LocalContainerEntityManagerFactoryBean entityManagerFactory =
//        new LocalContainerEntityManagerFactoryBean();
//
//    entityManagerFactory.setDataSource(dataSource);
//
//    // Classpath scanning of @Component, @Service, etc annotated class
//    entityManagerFactory.setPackagesToScan(env.getProperty("entityManager.packagesToScan"));
//
//    // Vendor adapter
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//
//    // Hibernate properties
//    Properties additionalProperties = new Properties();
//    additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//    additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//    additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//
//
//    entityManagerFactory.setJpaProperties(additionalProperties);
//
//    return entityManagerFactory;
//  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }


}