package com.mylabs.myfuel;

import com.mylabs.myfuel.domain.dto.mapper.UserMapper;
import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyfuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfuelApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public VeiculoMapper veiculoMapper() {
		return new VeiculoMapper(modelMapper());
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapper(modelMapper());
	}

}
