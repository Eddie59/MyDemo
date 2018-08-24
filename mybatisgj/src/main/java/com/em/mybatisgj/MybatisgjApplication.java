package com.em.mybatisgj;

import com.em.mybatisgj.common.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement

public class MybatisgjApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisgjApplication.class, args);
	}
}
