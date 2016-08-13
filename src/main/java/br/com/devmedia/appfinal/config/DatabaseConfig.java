package br.com.devmedia.appfinal.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("br.com.devmedia.appfinal")
@PropertySource("classpath:sql/datasource.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${datasource.url}")
    private String url;
    
    @Value("${datasource.username}")
    private String username;
    
    @Value("${datasource.password}")
    private String password;
    
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);
        
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(this.dataSource());
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}