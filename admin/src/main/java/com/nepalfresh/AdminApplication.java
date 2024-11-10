package com.nepalfresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.nepalfresh",exclude = FreeMarkerAutoConfiguration.class)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.nepalfresh"})
@EnableJpaRepositories(basePackages = {"com.nepalfresh"})
public class AdminApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}