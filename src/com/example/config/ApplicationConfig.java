package com.example.config;

import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.entity.Customer;

@Configuration
@ComponentScan(basePackages = "com.example")
@EnableWebMvc
public class ApplicationConfig {
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver ivr = new InternalResourceViewResolver();
		ivr.setSuffix(".jsp");
		return ivr;
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springbatch24javabasespringmvc");
		ds.setUsername("root");
		ds.setPassword("root");
		
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean getseSessionFactoryBean() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource());
		
		Properties properties = new Properties();
		properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		properties.setProperty(Environment.HBM2DDL_AUTO, "update");
		properties.setProperty(Environment.SHOW_SQL, "true");
		
		sf.setHibernateProperties(properties);
		sf.setAnnotatedClasses(Customer.class);
		
		return sf;
	}
}
