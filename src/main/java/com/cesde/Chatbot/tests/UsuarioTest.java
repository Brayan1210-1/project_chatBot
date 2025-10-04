package com.cesde.Chatbot.tests;

import com.cesde.Chatbot.model.Usuario;

public class UsuarioTest {

	public  void testCreacion() {
		Usuario usuario1 = new Usuario(100L,"julio","pepitorojas@gmail.com","23143424");
		System.out.println(usuario1.getNombre() + usuario1.getEmail());
		
	}
}
