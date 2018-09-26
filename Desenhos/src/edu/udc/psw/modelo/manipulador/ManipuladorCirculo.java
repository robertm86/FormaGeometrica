package edu.udc.psw.modelo.manipulador;

import java.awt.Graphics;

import edu.udc.psw.modelo.Circulo;
import edu.udc.psw.modelo.Ponto2D;

public class ManipuladorCirculo implements ManipuladorFormaGeometrica {

	private Circulo circulo;
	
	public ManipuladorCirculo(Circulo c) {
		circulo = c;
	}
	
	@Override
	public void click(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void press(int x, int y) {
		// TODO Auto-generated method stub
		Ponto2D p = new Ponto2D(x, y);
		circulo.setA(p);
		p = new Ponto2D(x, y);
		circulo.setB(p);
		
	}

	@Override
	public void release(int x, int y) {
		// TODO Auto-generated method stub
		Ponto2D p = new Ponto2D(x, y);
		circulo.setB(p);
		
	}

	@Override
	public void drag(int x, int y) {
		// TODO Auto-generated method stub
		Ponto2D p = new Ponto2D(x, y);
		circulo.setB(p);
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		int radius = 15;
		
		int xa = (int) circulo.getA().getX();
		int xb = (int) circulo.getB().getX();
		int ya = (int) circulo.getA().getY();
		int yb = (int) circulo.getB().getY();
		
		//g.drawOval((int) circulo.getA().getX(),(int) circulo.getA().getY(), 
		//		(int) radius * 2, (int) radius *2 );
		
		g.drawOval(xa < xb ? xa : xb, ya < yb ? ya : yb, 
				(int) circulo.base(), (int) circulo.altura() );
		
	}

}
