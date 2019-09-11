package com.ats;


import com.ats.service.impl.SuggestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class, SecurityAutoConfiguration.class},
        scanBasePackages = "com.ats")
@PropertySource(value = {"classpath:application.properties"})
@Configuration
@EnableScheduling
public class ATSApplication {

    @Autowired
     SuggestServiceImpl suggestService;

    public static void main(String[] args) {
        SpringApplication.run(ATSApplication.class, args);
    }

    @Scheduled(fixedRate = 300000)
    public  void excuteSuggestCvForJob() {
        System.out.println("The suggest executed at " + new Date());
        try {
			suggestService.test();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
