package com.cesde.Chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cesde.Chatbot.tests.*;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
		 
		UsuarioTest usuario = new UsuarioTest();
		usuario.testCreacion();
		
		CategoriaPreguntaRespuestaTest categoria = new CategoriaPreguntaRespuestaTest();
	    categoria.crearCategoria();
	    
	    System.out.println("");
	    PreguntaTest pregunta = new PreguntaTest();
	    pregunta.crearPregunta(categoria.crearCategoria());
	 
		
		
	}

}
