package edu.udc.psw.modelo.manipulador;

import java.awt.Graphics;

import edu.udc.psw.gui.PainelDesenho;
import edu.udc.psw.modelo.Ponto2D;
import edu.udc.psw.modelo.Triangulo;

public class ManipuladorTriangulo implements ManipuladorFormaGeometrica {

	private Triangulo triangulo;
	private int clique = 0 ;

	public ManipuladorTriangulo(Triangulo t) {
		triangulo = t;
	}

	@Override
	public void click(int x, int y) {

		clique = PainelDesenho.getClique();
		
		if (clique == 0) {
			Ponto2D p1 = new Ponto2D(x, y);
			triangulo.setP1(p1);
			clique++;
			PainelDesenho.setClique(clique);
		}
		else if(clique == 1) {
			Ponto2D p2 = new Ponto2D(x, y);
			triangulo.setP2(p2);
			clique++;
			PainelDesenho.setClique(clique);
		}
		else if(clique == 2) {
			Ponto2D p3 = new Ponto2D(x, y);
			triangulo.setP3(p3);
			clique++;
			PainelDesenho.setClique(clique);
		};

	}

	@Override
	public void press(int x, int y) {
		
	}

	@Override
	public void release(int x, int y) {

	}

	@Override
	public void drag(int x, int y) {

	}

	@Override
	public void paint(Graphics g) {
		 
		if (clique == 1 ) {
			 
			g.drawOval ((int) triangulo.getP1().getX(), (int) triangulo.getP1().getY(),2,2);
			
		}
		
		if (clique == 2 ) {
			
			g.drawOval ((int) triangulo.getP2().getX(), (int) triangulo.getP1().getY(),2,2);
			
			g.drawLine((int) triangulo.getP1().getX(), (int) triangulo.getP1().getY(), 
					(int) triangulo.getP2().getX(), (int) triangulo.getP2().getY());
			
		}
		
		if (clique == 3 ) {
			
			g.drawOval ((int) triangulo.getP3().getX(), (int) triangulo.getP3().getY(),2,2);
			
			g.drawLine((int) triangulo.getP1().getX(), (int) triangulo.getP1().getY(), 
					(int) triangulo.getP2().getX(), (int) triangulo.getP2().getY());
			
			g.drawLine((int) triangulo.getP2().getX(), (int) triangulo.getP2().getY(), 
					(int) triangulo.getP3().getX(), (int) triangulo.getP3().getY());
			
			g.drawLine((int) triangulo.getP3().getX(), (int) triangulo.getP3().getY(), 
					(int) triangulo.getP1().getX(), (int) triangulo.getP1().getY());
			
			clique = 0;
			PainelDesenho.setClique(clique);
			
		}

	}
}
