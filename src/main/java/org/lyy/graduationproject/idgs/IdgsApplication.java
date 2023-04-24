package org.lyy.graduationproject.idgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lyy.graduationproject.idgs.mapper")
public class IdgsApplication {


    public static void main(String[] args) {
        SpringApplication.run(IdgsApplication.class, args);
    }

}
