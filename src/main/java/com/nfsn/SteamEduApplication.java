package com.nfsn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.nfsn.**.mapper"})
public class SteamEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteamEduApplication.class, args);
    }

}
