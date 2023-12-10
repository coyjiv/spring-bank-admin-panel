package com.coyjiv.springbankadminpanel;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SpringBankAdminPanelApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBankAdminPanelApplication.class, args);
	}


	@Override public void run(ApplicationArguments args) throws Exception {
		System.out.println("http://localhost:9000/swagger-ui/index.html \n");
		System.out.println("http://localhost:9000/h2-console \n");


	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("BAP API")
						.description("Bank admin platform")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
						.description("SpringShop Wiki Documentation")
						.contact(new Contact().email("djdanil46@gmail.com").url("https://daniilshcherbakov.vercel.app/")))
				;
	}
}
