package edu.udc.psw.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.udc.psw.modelo.Linha;
import edu.udc.psw.modelo.Retangulo;
import edu.udc.psw.modelo.Ponto2D;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class JanelaDesenho extends JFrame {
	private static final long serialVersionUID = 1L;
	private PainelDesenho contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaDesenho frame = new JanelaDesenho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaDesenho() {
		setTitle("Janela de desenho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new PainelDesenho();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSalvarSerial = new JMenuItem("Salvar (serial)");
		mntmSalvarSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = escolherArquivo();
				if(f == null)
					return;
				contentPane.salvarSerial(f);
			}
		});
		mnArquivo.add(mntmSalvarSerial);
		
		JMenuItem mntmLerSerial = new JMenuItem("Ler (serial)");
		mntmLerSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = escolherArquivo();
				if(f == null)
					return;
				contentPane.lerSerial(f);
			}
		});
		mnArquivo.add(mntmLerSerial);
		
		JMenu mnFiguras = new JMenu("Figuras");
		menuBar.add(mnFiguras);
		
		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ponto2D ponto = new Ponto2D();
				
				contentPane.setFormaGeometrica(ponto);
			}
		});
		mnFiguras.add(mntmPonto);
		
		JMenuItem mntmLinha = new JMenuItem("Linha");
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Linha linha = new Linha();
				
				contentPane.setFormaGeometrica(linha);
			}
		});
		mnFiguras.add(mntmLinha);
		
		JMenuItem mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Retangulo retangulo = new Retangulo();
				contentPane.setFormaGeometrica(retangulo);
			}
		});
		mnFiguras.add(mntmRetangulo);
		
		
	}
	
	private File escolherArquivo() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));

		FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Serial file", "ser");
		fc.setFileFilter(textFilter);

		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

}
