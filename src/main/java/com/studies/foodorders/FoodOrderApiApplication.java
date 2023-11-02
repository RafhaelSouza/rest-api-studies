package com.studies.foodorders;

import com.studies.foodorders.core.io.Base64ProtocolResolver;
import com.studies.foodorders.infrastructure.repositories.springcustom.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class FoodOrderApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		var app = new SpringApplication(FoodOrderApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
