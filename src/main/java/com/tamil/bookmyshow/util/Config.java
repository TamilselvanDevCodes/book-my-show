package com.tamil.bookmyshow.util;



import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class Config implements WebMvcConfigurer{

	@Bean
	public OpenAPI swaggerDocOpenApi() {
		Server devServer=new Server();
		devServer.setUrl("localhost:8080");
		devServer.setDescription("Development Server");
		Contact contact=new Contact();
		contact.setEmail("lt.tamilselvan2002@gmail.com");
		contact.setName("TamilSelvan");
		contact.setUrl("https://github.com/Tamilselvan025");
		
		License license=new License();
		license.setName("Licenses");
		license.setUrl("https://chat.openai.com");
		
		Info info=new Info();
		info.setContact(contact);
		info.setLicense(license);
		info.setDescription("Book My Show");
		info.setTermsOfService("Terms & Service");
		info.setTitle("Book My Show");
		info.setVersion("2.0");
		
		OpenAPI openAPI=new OpenAPI();
		openAPI.info(info);
		openAPI.servers(List.of(devServer));
		return openAPI;
	}
}
