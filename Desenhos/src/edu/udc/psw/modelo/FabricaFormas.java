package edu.udc.psw.modelo;

import java.nio.ByteBuffer;

public class FabricaFormas {
	
	public static FormaGeometrica fabricarFormaGeometrica(String forma) {
		int i = forma.indexOf(' ');
		String nome = forma.substring(0, i);
		FormaGeometrica formaGeometrica = null;
		
		if(nome.equals(Linha.class.getSimpleName()))
			formaGeometrica = fabricarLinha(forma.substring(i+1)); //new Linha();
		else if(nome.equals(Retangulo.class.getSimpleName()))
			formaGeometrica = fabricarRetangulo(forma.substring(i+1)); //new Retangulo();
		else if(nome.equals(Ponto2D.class.getSimpleName()))
			formaGeometrica = fabricarPonto2D(forma.substring(i+1)); //new Ponto2D();
		
		return formaGeometrica;
	}
	
	public static Linha fabricarLinha(String linha) {
		int i = linha.indexOf(')');
		Ponto2D p1 = fabricarPonto2D(linha.substring(0, i));
		Ponto2D p2 = fabricarPonto2D(linha.substring(i+1));
		Linha l = new Linha(p1, p2);
		//System.out.println(l.getClass().getSimpleName() + " " + l.toString());
		return l;
	}

	public static Retangulo fabricarRetangulo(String retangulo) {
		int i = retangulo.indexOf(')');
		Ponto2D p1 = fabricarPonto2D(retangulo.substring(0, i));
		Ponto2D p2 = fabricarPonto2D(retangulo.substring(i+1));
		Retangulo r = new Retangulo(p1, p2);
		//System.out.println(r.getClass().getSimpleName() + " " + r.toString());
		return r;
	}

	public static Ponto2D fabricarPonto2D(String ponto) {
		int i = ponto.indexOf(';');
		double x = Double.parseDouble(ponto.substring(1, i).replace(',', '.'));
		double y = Double.parseDouble(ponto.substring(i+1, ponto.length()-1).replace(',', '.'));
		Ponto2D p = new Ponto2D(x, y);
		//System.out.println(p.getClass().getSimpleName() + " " + p.toString());
		return p;
	}

	public static FormaGeometrica fabricarFormaGeometrica(byte bytes[]) {
		long forma = ByteBuffer.wrap(bytes, 0, 8).getLong();
		FormaGeometrica formaGeometrica = null;
		
		if(forma == Linha.serialVersionUID)
			formaGeometrica = new Linha(bytes);
		else if(forma == Retangulo.serialVersionUID)
			formaGeometrica = new Retangulo(bytes);
		else if(forma == Ponto2D.serialVersionUID)
			formaGeometrica = new Ponto2D(bytes);
		
		return formaGeometrica;
	}
	
	public static Ponto2D fabricarPonto2D(byte bytes[]) {
		double x = ByteBuffer.wrap(bytes, 0, 8).getDouble();
		double y = ByteBuffer.wrap(bytes, 8, 8).getDouble();
		return new Ponto2D(x, y);
	}

	public static Retangulo fabricarRetangulo(byte bytes[]) {
		double ax = ByteBuffer.wrap(bytes, 0, 8).getDouble();
		double ay = ByteBuffer.wrap(bytes, 8, 8).getDouble();
		double bx = ByteBuffer.wrap(bytes, 16, 8).getDouble();
		double by = ByteBuffer.wrap(bytes, 24, 8).getDouble();
		return new Retangulo(new Ponto2D(ax, ay), new Ponto2D(bx, by));
	}

	public static Linha fabricarLinha(byte bytes[]) {
		double ax = ByteBuffer.wrap(bytes, 0, 8).getDouble();
		double ay = ByteBuffer.wrap(bytes, 8, 8).getDouble();
		double bx = ByteBuffer.wrap(bytes, 16, 8).getDouble();
		double by = ByteBuffer.wrap(bytes, 24, 8).getDouble();
		return new Linha(new Ponto2D(ax, ay), new Ponto2D(bx, by));
	}

}
