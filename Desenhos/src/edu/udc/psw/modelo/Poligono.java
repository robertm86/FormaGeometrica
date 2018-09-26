package edu.udc.psw.modelo;

import edu.udc.psw.modelo.manipulador.ManipuladorFormaGeometrica;

public class Poligono implements FormaGeometrica {
	// tarefa -> não foi feito
	
	private Ponto2D pontos[] = new Ponto2D[10];
	private int lados;
	private int x,y,i;
	
	public Poligono(int lados)
	{
		this.lados = lados;
		for(int i = 0; i < lados; i++){
			pontos[i] = new Ponto2D(i, i);
		}
	}

	public Poligono() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Poligono clone() {
		return new Poligono(lados);
	}
	
	public Ponto2D centro() {
		double x = 0, y = 0;
		
		for(int i = 0; i < lados; i++){
			x += pontos[i].getX();
			y += pontos[i].getY();
		}
		
		x /= lados;
		y /= lados;
		
		return new Ponto2D(x, y);
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double base() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double altura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManipuladorFormaGeometrica getManipulador() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPonto(Ponto2D p) {
		this.x = (int) p.getX();
		this.y = (int) p.getY();
		
		i = pontos.length;
			
		//pontos[i] = new Ponto2D(x, y);
		pontos[i] = p;
	}
	
	public void setPonto(Ponto2D p,int pos) {
		pontos[pos] = new Ponto2D((int) p.getX(), (int) p.getY());
	}
	
	public Ponto2D[] getPonto() {
		return pontos;
	}
	
	public Ponto2D getPonto(int pos) {
		return pontos[pos];
	}
}
