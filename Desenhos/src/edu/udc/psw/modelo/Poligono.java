package edu.udc.psw.modelo;

public class Poligono {
	// tarefa -> n�o foi feito
	
	private Ponto2D pontos[] = new Ponto2D[10];
	private int lados;
	
	public Poligono(int lados)
	{
		this.lados = lados;
		for(int i = 0; i < lados; i++){
			pontos[i] = new Ponto2D(i, i);
		}
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
	
}
