package edu.udc.psw.desenhos.controle;

import java.io.File;
import edu.udc.psw.colecao.Iterador;
import edu.udc.psw.colecao.ListaEncadeada;
import edu.udc.psw.desenhos.controle.persistencia.ArquivoFormaGeometrica;
import edu.udc.psw.desenhos.controle.persistencia.ArquivoFormasBinario;
import edu.udc.psw.desenhos.controle.persistencia.ArquivoFormasSerial;
import edu.udc.psw.desenhos.controle.persistencia.ArquivoFormasTexto;
import edu.udc.psw.modelo.FormaGeometrica;
import edu.udc.psw.modelo.Linha;
import edu.udc.psw.modelo.Ponto2D;
import edu.udc.psw.modelo.Retangulo;

public class Documento {
	private ListaEncadeada<FormaGeometrica> listaFormas;

	public Documento() {
		super();
		listaFormas = new ListaEncadeada<FormaGeometrica>();
	}

	public void novaForma(FormaGeometrica forma) {
		listaFormas.inserirFim(forma);
	}

	public void novoPonto(int x, int y) {
		Ponto2D p = new Ponto2D(x, y);
		listaFormas.inserirFim(p);

	}

	public void novaLinha(int xi, int yi, int xf, int yf) {
		Linha l = new Linha(new Ponto2D(xi, yi), new Ponto2D(xf, yf));
		listaFormas.inserirFim(l);
	}

	public void novoRetangulo(int xi, int yi, int xf, int yf) {

		Retangulo r = new Retangulo(new Ponto2D(xi, yi), new Ponto2D(xf, yf));
		listaFormas.inserirFim(r);
	}

	public int getQtdFiguras() {
		return listaFormas.getTamanho();
	}

	public FormaGeometrica getFigura(int pos) {
		return (FormaGeometrica) listaFormas.pesquisar(pos);
	}

	public Iterador<FormaGeometrica> getIteradorFormas() {
		return listaFormas.getInicio();
	}

	public void salvarFormas(File file) {
		ArquivoFormaGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.')+1);

			if (ext.compareTo("ser") == 0)
				arq = new ArquivoFormasSerial(file);
			if (ext.compareTo("txt") == 0)
				arq = new ArquivoFormasTexto(file);
			if (ext.compareTo("bin") == 0)
				arq = new ArquivoFormasBinario(file);
			arq.salvarFormas(listaFormas);

	}

	public void lerFormas(File file) {
		ArquivoFormaGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.')+1);

			if (ext.compareTo("ser") == 0)
				arq = new ArquivoFormasSerial(file);
			if (ext.compareTo("txt") == 0)
				arq = new ArquivoFormasTexto(file);
			if (ext.compareTo("bin") == 0)
				arq = new ArquivoFormasBinario(file);
			listaFormas = arq.lerFormas();


	}

}
