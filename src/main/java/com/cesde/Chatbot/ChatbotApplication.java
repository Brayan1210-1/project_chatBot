package com.cesde.Chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cesde.model.*;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
		
		CategoriaPregunta categoriaInicio = new CategoriaPregunta("Juan", "Prueba");
		 
		Pregunta preguntaInicio = new Pregunta(1L, "Amo los perritos", categoriaInicio);
		
		System.out.println(preguntaInicio);
	}

}
