package clases;

import java.io.Serializable;

public class jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	public String tiempo;
	public int numero;
	public int respuesta;

	public jugador(String tiempo, int numero, int respuesta) {
		this.tiempo = tiempo;
		this.numero = numero;
		this.respuesta = respuesta;

	}

}