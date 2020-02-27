package br.com.softplan.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/*@ComponentScan({"br.com.softplan.api.controller"})
@EntityScan("br.com.softplan.api.domain")
@EnableJpaRepositories("br.com.softplan.api.dao")
@ComponentScan({"br.com.softplan.api.error"})
@ComponentScan({"br.com.softplan.api.handler"})
@ComponentScan({"br.com.softplan.api.config"})*/
public class ManagerIndicadoresAPI {

	public static void main(String[] args) {
		SpringApplication.run(ManagerIndicadoresAPI.class, args);

	}

}
