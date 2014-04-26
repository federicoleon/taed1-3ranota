package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Resultados;
import service.DirectoryService;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = -8429170421831610540L;
	private DirectoryService directoryService;

	public Principal() {
		this.directoryService = new DirectoryService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.nuevaEjecucion();
	}
	
	private void nuevaEjecucion() {
		Resultados aux = this.directoryService.createNewDirectoryStructure("/Users/fleon/Documents/testing/2014_3rNotaAED1");
		System.out.println(aux);
	}
}