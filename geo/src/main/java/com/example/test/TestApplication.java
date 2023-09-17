package com.example.test;

import org.hibernate.Version;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        //HIBERNATE VERSION : 6.2.7.Final
        String hibernateVersion = Version.getVersionString();
        System.out.println("HIBERNATE VERSION : " + hibernateVersion);
        SpringApplication.run(TestApplication.class, args);
    }
}
