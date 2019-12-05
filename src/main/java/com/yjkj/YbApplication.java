package com.yjkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//@EnableAsync
//@EnableBatchProcessing
@MapperScan(basePackages = "com.yjkj.mapper")
public class YbApplication {
	public static void main(String[] args) {
		SpringApplication.run(YbApplication.class);
	}
}
