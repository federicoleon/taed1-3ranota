package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import service.DirectoryService;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = -8429170421831610540L;
	private DirectoryService directoryService;
	private Principal principal;

	public Principal() {
		setTitle(":: AED - An\u00E1lisis de directorios ::");
		this.principal = this;
		this.directoryService = new DirectoryService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEjecuciones = new JMenu("Ejecuciones");
		menuBar.add(mnEjecuciones);
		
		JMenuItem mntmNuevaEjecucion = new JMenuItem("Nueva ejecuci\u00F3n");
		mntmNuevaEjecucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaEjecucion nuevaEjecucion = new NuevaEjecucion(principal);
				nuevaEjecucion.setLocationRelativeTo(null);
				nuevaEjecucion.setModal(true);
				nuevaEjecucion.setVisible(true);
			}
		});
		mnEjecuciones.add(mntmNuevaEjecucion);
		
		JMenu mnResultados = new JMenu("Resultados");
		menuBar.add(mnResultados);
		
		JMenuItem mntmEstadsticas = new JMenuItem("Informes");
		mntmEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeResultados informes = new InformeResultados(principal);
				informes.setLocationRelativeTo(null);
				informes.setModal(true);
				informes.setVisible(true);
			}
		});
		mnResultados.add(mntmEstadsticas);
		
		JMenu mnSesinActual = new JMenu("Sesi\u00F3n actual");
		menuBar.add(mnSesinActual);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnSesinActual.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public DirectoryService getDirectoryService() {
		return this.directoryService;
	}
}