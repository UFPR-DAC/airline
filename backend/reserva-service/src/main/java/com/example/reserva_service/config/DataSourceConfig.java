package com.example.reserva_service.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.reserva_service.comando.repository",
        entityManagerFactoryRef = "commandEntityManagerFactory",
        transactionManagerRef = "commandTransactionManager"
)
class CommandDataSourceConfig {
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean commandEntityManagerFactory(DataSource dataSource, JpaProperties jpaProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.reserva_service.comando.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }
    @Primary
    @Bean
    public PlatformTransactionManager commandTransactionManager(LocalContainerEntityManagerFactoryBean commandEntityManagerFactory) {
        return new JpaTransactionManager(commandEntityManagerFactory.getObject());
    }
}

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.reserva_service.consulta.repository",
        entityManagerFactoryRef = "queryEntityManagerFactory",
        transactionManagerRef = "queryTransactionManager"
)
class QueryDataSourceConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean queryEntityManagerFactory(DataSource dataSource, JpaProperties jpaProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.reserva_service.consulta.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.default_schema", "reserva_qry_db"); // USA O SCHEMA 'query'
        em.setJpaPropertyMap(properties);
        return em;
    }
    @Bean
    public PlatformTransactionManager queryTransactionManager(LocalContainerEntityManagerFactoryBean queryEntityManagerFactory) {
        return new JpaTransactionManager(queryEntityManagerFactory.getObject());
    }
}