package edu.udc.psw.desenhos.controle.persistencia;

import edu.udc.psw.colecao.ListaEncadeada;
import edu.udc.psw.modelo.FormaGeometrica;

public interface ArquivoFormaGeometrica {
	public ListaEncadeada<FormaGeometrica> lerFormas();
	public void salvarFormas(ListaEncadeada<FormaGeometrica> lista);
}
