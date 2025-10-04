package com.cesde.Chatbot.tests;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;
import com.cesde.Chatbot.model.Pregunta;

public class PreguntaTest {

	public void crearPregunta(CategoriaPreguntaRespuesta categoria) {
		Pregunta pregunta = new Pregunta("¿puedo saltar de un avión?", categoria);
		System.out.println(pregunta.getEnunciado() + ""+ pregunta.getCategoria());
	}

	
}
