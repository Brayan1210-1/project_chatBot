package com.cesde.Chatbot.tests;

import com.cesde.Chatbot.model.CategoriaPreguntaRespuesta;

public class CategoriaPreguntaRespuestaTest {

	public  CategoriaPreguntaRespuesta crearCategoria() {
CategoriaPreguntaRespuesta categoria = new CategoriaPreguntaRespuesta("Prueba1210", "Pruebas unitariassss");
		System.out.println(categoria.getNombre() + categoria.getDescripcion());
		return categoria;
	}
}
