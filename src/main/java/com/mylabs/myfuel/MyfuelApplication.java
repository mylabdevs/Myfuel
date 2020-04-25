package com.mylabs.myfuel;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyfuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfuelApplication.class, args);
	}

	@Bean(name = "modelMapper")
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
