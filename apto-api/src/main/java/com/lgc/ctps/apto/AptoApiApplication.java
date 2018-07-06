package com.lgc.ctps.apto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackageClasses = {AptoApiApplication.class, Jsr310JpaConverters.class})
@EnableJpaRepositories(basePackageClasses = AptoApiApplication.class, repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class AptoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AptoApiApplication.class, args);
	}
}
