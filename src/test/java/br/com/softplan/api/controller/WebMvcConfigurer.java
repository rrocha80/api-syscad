package br.com.softplan.api.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class WebMvcConfigurer {
	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@SuppressWarnings("unused")
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("//**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
			}
		};
		
	}
	
	/*@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("http://localhost:3000").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

	}*/

}
