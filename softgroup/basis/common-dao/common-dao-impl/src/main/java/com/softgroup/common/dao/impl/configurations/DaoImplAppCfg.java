package com.softgroup.common.dao.impl.configurations;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by anton on 11.03.17.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("com.softgroup.common.dao.impl")
@EnableJpaRepositories("com.softgroup.common.dao.impl.repositories")
@PropertySource("classpath:application.properties")
public class DaoImplAppCfg {

    private static final String[] ENTITY_PACKAGES = {
            "com.softgroup.common.dao.api.entities"
    };


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(ENTITY_PACKAGES);
        em.setJpaVendorAdapter(createJpaVendorAdapter());

        em.setJpaProperties(additionalProperties(env));

        return em;
    }

    private AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

   /* @Bean
    public DataSource dataSource(Environment env){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/Messenger");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }*/

    @Bean
    @Profile("default")
    public DataSource dataSource(Environment env) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    @Profile("test")
    public DataSource dataSourceTest(Environment env) {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory em){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(em);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties(Environment env) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.ejb.naming_strategy", env.getProperty("hibernate.ejb.naming_strategy"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        return properties;
    }


    @Bean
    public SpringLiquibase liquibase(DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibaseLog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}