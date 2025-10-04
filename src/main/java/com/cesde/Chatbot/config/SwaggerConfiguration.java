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
	          .url("http://localhost:443")
	          .description("🛠️ Servidor local")
	        )
	      )
	      .tags(List.of(
	        new Tag()
	          .name("Gestión de usuarios")
	          .description("administración básica de usuarios (leer,borrar,actualizar y crear)"),
	        new Tag()
	        .name("Gestión de categorias")
	        .description("leer categorias existentes"),
	        new Tag()
	        .name("Gestión de preguntas")
	        .description("Modificar preguntas"),
	        new Tag()
	        .name("Gestión de respuestas")
	        .description("administrar las respuestas existentes"),
	        new Tag()
	        .name("Gestión de categoria sugerencia")
	        .description("administrar las categorias de sugerencias existentes"),
	        new Tag()
	        .name("Gestión de sugerencias")
	        .description("métodos crud para las sugerencias")
	         
	      )
	    );
	  }
	}

