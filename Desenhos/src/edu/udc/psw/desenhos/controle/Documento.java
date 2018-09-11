package edu.udc.psw.desenhos.controle;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import edu.udc.psw.colecao.Iterador;
import edu.udc.psw.colecao.ListaEncadeada;
import edu.udc.psw.modelo.FabricaFormas;
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

	public void novaForma(FormaGeometrica forma)
	{
		listaFormas.inserirFim(forma);
	}
	
	public void novoPonto(int x, int y)
	{
		Ponto2D p = new Ponto2D(x, y);
		listaFormas.inserirFim(p);
		
	}
	
	public void novaLinha(int xi, int yi, int xf, int yf)
	{
		Linha l = new Linha(new Ponto2D(xi, yi), new Ponto2D(xf, yf));
		listaFormas.inserirFim(l);
	}

	public void novoRetangulo(int xi, int yi, int xf, int yf)
	{

		Retangulo r = new Retangulo(new Ponto2D(xi, yi), new Ponto2D(xf, yf));
		listaFormas.inserirFim(r);
	}

	public int getQtdFiguras()
	{
		return listaFormas.getTamanho();
	}
	
	public FormaGeometrica getFigura(int pos)
	{
		return (FormaGeometrica) listaFormas.pesquisar(pos);
	}
	
	public Iterador<FormaGeometrica> getIteradorFormas() {
		return listaFormas.getInicio();
	}

	public void salvarSerial(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			FormaGeometrica formaAux;
			Iterador<FormaGeometrica> it = listaFormas.getInicio();

			formaAux = it.getObjeto();
			while (formaAux != null) {
				oos.writeObject(formaAux);
				formaAux = it.proximo();
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lerSerial(File file) {
		// Criar um método na lista para realizar esta operação
		while (!listaFormas.isVazia()) {
			listaFormas.removerInicio();
		}

		FormaGeometrica formaAux = null;
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				formaAux = (FormaGeometrica) ois.readObject();
				
				listaFormas.inserirFim(formaAux);
			}
		} catch (EOFException endOfFileException) {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // fim do arquivo foi alcançado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvarTexto(File f) {
		FileWriter output; // objeto utilizado para gerar saída de texto no arquivo
		
		try {
			output = new FileWriter(f);
			
			FormaGeometrica formaAux;
			Iterador<FormaGeometrica> it = listaFormas.getInicio();

			formaAux = it.getObjeto();
			while (formaAux != null) {
				output.append(formaAux.getClass().getSimpleName() + " " + formaAux.toString() + "\n");
				formaAux = it.proximo();
			}
			
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void lerTexto(File f) {
		Scanner input = null;

		// Criar um método na lista para realizar esta operação
		while (!listaFormas.isVazia()) {
			listaFormas.removerInicio();
		}

		try {
			input = new Scanner( f );
			while (input.hasNextLine()) {
				String str = input.nextLine();
				FormaGeometrica formaAux = FabricaFormas.fabricarFormaGeometrica(str);
				
				listaFormas.inserirFim(formaAux);
			}
			
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}


}
