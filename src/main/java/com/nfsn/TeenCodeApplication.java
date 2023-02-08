package com.nfsn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.nfsn.**.mapper"})
public class TeenCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeenCodeApplication.class,args);
    }

}
