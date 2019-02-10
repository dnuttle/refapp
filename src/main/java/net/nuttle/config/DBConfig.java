package net.nuttle.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DBConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);
  static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/db.sqlite"; 

  @Autowired Environment env;
  
  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("dbDriverClassName"));
    dataSource.setUrl(DB_URL);
    //dataSource.setUrl(DB_URL);
    dataSource.setUsername(env.getProperty("dbUser"));
    dataSource.setPassword(env.getProperty("dbPassword"));
    return dataSource;
  }
  
  //Not entirely clear what this is about and why it's needed. But errors without it.
  //The Baeldung tutorial really fell down on not mentioning this; had to examine the github project.
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "net.nuttle.model" });
      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      em.setJpaProperties(additionalProperties());
      return em;
  }

  final Properties additionalProperties() {
      final Properties hibernateProperties = new Properties();
      if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
          hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      }
      if (env.getProperty("hibernate.dialect") != null) {
          hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
      }
      if (env.getProperty("hibernate.show_sql") != null) {
          hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      }
      return hibernateProperties;
  }  
  
}
