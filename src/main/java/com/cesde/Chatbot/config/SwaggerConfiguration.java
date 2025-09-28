package com.cesde.Chatbot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
class SwaggerConfiguracion {
	
	/**
	 * Swagger/OpenAPI configuration for documenting our API
	 * 
	 * This configuration creates the automatic documentation that can be viewed at:
	 * http://localhost:80/swagger-ui.html
	 */
	  @Bean
	  public OpenAPI userApiOpenAPI() {
	    return new OpenAPI()
	      .info(new Info()
	        .title("🚀 Chatbot - Documentación de uso")
	        .description("Simple REST API for learning Spring Boot 3.x - CESDE Student project")
	        .version("1.0.0")
	        .contact(new Contact()
	          .name("Creadores del chatbot")
	          .email("smazo1234@cesde.net")
	          .url("https://cesde.edu.co")
	        )
	        .license(new License()
	          .name("MIT License")
	          .url("https://opensource.org/licenses/MIT")
	        )
	      )
	      .servers(List.of(
	        new Server()
	          .url("http://localhost:80")
	          .description("🛠️ Servidor local")
	        )
	      )
	      .tags(List.of(
	        new Tag()
	          .name("Gestión de usuarios")
	          .description("CRUD operations for users: create, read, update, delete")
	      )
	    );
	  }
	}

