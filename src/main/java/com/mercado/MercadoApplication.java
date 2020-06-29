package com.mercado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class MercadoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MercadoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/mercado/**").allowedMethods("*").allowedOrigins("*");

		registry.addMapping("/pessoa/**").allowedMethods("*").allowedOrigins("*");
		/* Liberando o mapeamento de usuario para todos os origins */
	}

}
