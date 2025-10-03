package com.cesde.Chatbot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//No vuelvo a hacer otra cosa sin comentarios
//recuerda usar las importaciones correctas :)
//se me volvió a olvidar el @Entity y @Table

@Entity
@Table(name = "respuesta")
public class Respuesta extends Contenido {

	
	/**indicamos la relación con pregunta
	 * el decorador ManyToOne se coloca en la parte muchos de la relación
	 * en el joinColumn se coloca el atributo que hace a la relación (preguntaId)
	 */
	@ManyToOne
	@JoinColumn(name = "pregunta_id", nullable = false)
	private Pregunta pregunta;
	
	//constructores
	
	//constructor sin parámetros (se llama al constructor de la clase padre )
	public Respuesta () { super();}
	
	/**
	 * constructor con parámetros
	 * Lo bueno es que se reduce mucho el código
	 * primero se escriben los parámetros del constructor del padre y después los de la clase hija
	 */
	
	public Respuesta(String enunciado, Pregunta preguntaId) {
		super(enunciado);
		this.pregunta = preguntaId;
	}

	//importante nunca olvidar los getters y setters porque después te falla  y no sabes por qué
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta preguntaId) {
		this.pregunta = preguntaId;
	}
	
}
