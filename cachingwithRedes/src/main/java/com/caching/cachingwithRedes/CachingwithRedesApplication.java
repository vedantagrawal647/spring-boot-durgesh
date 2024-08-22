package com.caching.cachingwithRedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingwithRedesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingwithRedesApplication.class, args);
	}

}
