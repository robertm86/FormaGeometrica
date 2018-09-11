package edu.udc.psw.modelo;

import java.io.Serializable;

import edu.udc.psw.modelo.manipulador.ManipuladorFormaGeometrica;

public interface FormaGeometrica extends Serializable {
	
	public Ponto2D centro();
	public double area();
	public double perimetro();
	public double base();
	public double altura();
	
	public byte[] toArray();

	public ManipuladorFormaGeometrica getManipulador();
	
	public FormaGeometrica clone();

}
