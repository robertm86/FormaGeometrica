package edu.udc.psw.gui;

import javax.swing.JPanel;

import edu.udc.psw.modelo.FabricaFormas;
import edu.udc.psw.modelo.FormaGeometrica;
import edu.udc.psw.modelo.manipulador.ManipuladorFormaGeometrica;
import edu.udc.psw.colecao.Iterador;
import edu.udc.psw.colecao.ListaEncadeada;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private boolean desenhando = false;
	private ManipuladorFormaGeometrica manipulador;

	// private Ponto2D ponto;
	// private Linha linha;
	private FormaGeometrica forma;

	private ListaEncadeada<FormaGeometrica> listaFormaGeometrica;

	/**
	 * Create the panel.
	 */
	public PainelDesenho() {

		listaFormaGeometrica = new ListaEncadeada<FormaGeometrica>();

		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void salvarSerial(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			FormaGeometrica formaAux;
			Iterador<FormaGeometrica> it = listaFormaGeometrica.getInicio();

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
		while (!listaFormaGeometrica.isVazia()) {
			listaFormaGeometrica.removerInicio();
		}

		FormaGeometrica formaAux = null;
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				formaAux = (FormaGeometrica) ois.readObject();
				
				listaFormaGeometrica.inserirFim(formaAux);
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
			Iterador<FormaGeometrica> it = listaFormaGeometrica.getInicio();

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
		while (!listaFormaGeometrica.isVazia()) {
			listaFormaGeometrica.removerInicio();
		}

		try {
			input = new Scanner( f );
			while (input.hasNextLine()) {
				String str = input.nextLine();
				FormaGeometrica formaAux = FabricaFormas.fabricarFormaGeometrica(str);
				
				listaFormaGeometrica.inserirFim(formaAux);
			}
			
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void setFormaGeometrica(FormaGeometrica forma) {
		this.forma = forma;
		manipulador = forma.getManipulador();
	}

	public boolean isDesenhando() {
		return desenhando;
	}

	public void setDesenhando(boolean desenhando) {
		this.desenhando = desenhando;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (forma != null)
			manipulador.paint(g);

		FormaGeometrica formaAux;
		Iterador<FormaGeometrica> it = listaFormaGeometrica.getInicio();

		formaAux = it.getObjeto();
		while (formaAux != null) {
			formaAux.getManipulador().paint(g);
			formaAux = it.proximo();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (manipulador != null) {
			manipulador.click(e.getX(), e.getY());

			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (manipulador != null) {
			manipulador.press(e.getX(), e.getY());

			desenhando = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (desenhando) {
			manipulador.release(e.getX(), e.getY());

			listaFormaGeometrica.inserirFim(forma);

			desenhando = false;

			forma = forma.clone();
			manipulador = forma.getManipulador();

			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (desenhando) {
			manipulador.drag(e.getX(), e.getY());

			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
