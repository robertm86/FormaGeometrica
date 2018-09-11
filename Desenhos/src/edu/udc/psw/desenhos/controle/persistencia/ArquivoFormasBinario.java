package edu.udc.psw.desenhos.controle.persistencia;

import java.io.File;

import edu.udc.psw.colecao.ListaEncadeada;
import edu.udc.psw.modelo.FormaGeometrica;

public class ArquivoFormasBinario implements ArquivoFormaGeometrica {
	private File file;
	
	public ArquivoFormasBinario(File file)
	{
		this.file = file;
	}


	@Override
	public ListaEncadeada<FormaGeometrica> lerFormas() {
		ListaEncadeada<FormaGeometrica> lista = new ListaEncadeada<FormaGeometrica>();
		return lista;
	}

	@Override
	public void salvarFormas(ListaEncadeada<FormaGeometrica> lista) {
		// TODO Auto-generated method stub

	}

}
