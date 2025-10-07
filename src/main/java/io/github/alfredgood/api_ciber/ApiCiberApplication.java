package io.github.alfredgood.api_ciber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title = "API CIBER",
		description = "Bienvenido a mi api ciber, hecha con spring boot 3.5.4",
		termsOfService = "",
		version = "1.0.1",
		contact = @Contact(
			name = "José Alfredo López De La Cruz",
			url = "https://github.com/AlfreGood20",
			email = "josealfredolopezdelacruz2@gmail.com"
		)
	),

	servers = {
		@Server (url = "http://localhost:8080/", description = "Aqui esta todos los enpoint de la api, UNICO server")
	}
)

@SpringBootApplication
public class ApiCiberApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCiberApplication.class, args);
	}
}