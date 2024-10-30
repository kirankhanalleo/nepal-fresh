package com.nepalfresh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.nepalfresh"})
@EntityScan(basePackages = {"com.nepalfresh"})
@EnableJpaRepositories(basePackages = {"com.nepalfresh"})
public class AppApplication extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
