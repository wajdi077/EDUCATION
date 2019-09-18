package com.dpc.Scolarity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dpc.Scolarity.service.StorageService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.dpc.Scolarity"})
@EntityScan("com.dpc.Scolarity.Domain")
@EnableJpaRepositories("com.dpc.Scolarity.Repository")


public class EdulinkBackendApplication  {
	  @Autowired
	  StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(EdulinkBackendApplication.class, args);
		

		LocalTime time = LocalTime.now();
		 String s = time.toString().substring(0, 5);      
		 System.out.println("date de jour "+s);
        
		
	}
	 public void run(String... arg) throws Exception {
	  
	   storageService.init();
	  }

}
