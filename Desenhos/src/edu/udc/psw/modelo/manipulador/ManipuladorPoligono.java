package edu.udc.psw.modelo.manipulador;

import java.awt.Graphics;

import edu.udc.psw.gui.PainelDesenho;
import edu.udc.psw.modelo.Poligono;
import edu.udc.psw.modelo.Ponto2D;

public class ManipuladorPoligono implements ManipuladorFormaGeometrica{

	private Poligono poligono;
	private int clique = 0;
	private int cont=0;
	private Ponto2D pontos[] = new Ponto2D[10];
	
	
	
	public ManipuladorPoligono(Poligono p) {
		poligono = p;
	}
	
	@Override
	public void click(int x, int y) {
		clique = PainelDesenho.getClique();
	
		Ponto2D p = new Ponto2D(x, y);
		poligono.setPonto(p);
		System.out.println(poligono.getPonto());
		clique++;
		PainelDesenho.setClique(clique);
		
	}

	@Override
	public void press(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drag(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		
		clique = PainelDesenho.getClique();
		/*
		pontos = poligono.getPonto();
		int i = 0;
		
        if (clique>1) {
            g.drawLine((int) pontos[0].getX(),(int)pontos[0].getY(),(int) pontos[1].getX(),(int) pontos[1].getY());
            if (clique>2) {
                for(int j=2; j<cont; j++) {
                    g.drawLine((int)pontos[j-1].getX(),(int) pontos[j-1].getY(),(int) pontos[j].getX(),(int) pontos[j].getY());
                }
            }
        }
        */
	}

}
