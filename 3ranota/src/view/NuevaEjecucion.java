package view;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.Resultados;
import utils.Textos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class NuevaEjecucion extends JDialog {

	private static final long serialVersionUID = 5681966752266487274L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRuta;
	private JLabel lblMensaje;
	private JPanel panelResultados;
	private JLabel lblCantidadDirectorios;
	private JLabel lblCantidadArchivos;
	private JLabel lblFotosValidas;
	private JLabel lblDocumentosValidos;
	private JLabel lblOtrosArchivos;
	private JLabel lblFotosPNG;
	private JLabel lblFotosJPG;
	private JLabel lblFotosBMP;
	
	private Principal principal;
	
	public NuevaEjecucion(JFrame principal) {
		setTitle("Nuevo an\u00E1lisis de directorio");
		setResizable(false);
		setModal(true);
		this.principal = (Principal)principal;
		setBounds(100, 100, 450, 381);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del directorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 438, 123);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(16, 33, 32, 16);
		panel.add(lblRuta);
		
		txtRuta = new JTextField();
		txtRuta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evento) {
				// Se presiono Enter. Generamos una nueva ejecucion.
				if(evento.getKeyCode() == 10) {
					generarNuevaEjecucion();
				}
				// Se presiono escape. Cerramos la ventana actual.
				if(evento.getKeyCode() == 27) {
					dispose();
				}
			}
		});
		txtRuta.setBounds(60, 27, 372, 28);
		panel.add(txtRuta);
		txtRuta.setColumns(10);
		
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(340, 89, 92, 29);
		panel.add(btnCancelar);
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarNuevaEjecucion();
			}
		});
		btnAnalizar.setBounds(236, 89, 92, 29);
		panel.add(btnAnalizar);
		
		lblMensaje = new JLabel(Textos.NUEVAEJECUCION_LBL_INGRESE_RUTA);
		lblMensaje.setBounds(16, 61, 416, 16);
		panel.add(lblMensaje);
		
		panelResultados = new JPanel();
		panelResultados.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelResultados.setBounds(6, 141, 438, 212);
		contentPanel.add(panelResultados);
		this.panelResultados.setEnabled(false);
		panelResultados.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Directorios:");
		lblNewLabel.setBounds(18, 32, 93, 16);
		panelResultados.add(lblNewLabel);
		
		JLabel lblArchivos = new JLabel("Archivos:");
		lblArchivos.setBounds(18, 60, 93, 16);
		panelResultados.add(lblArchivos);
		
		JLabel lblNewLabel_1 = new JLabel("Fotos v\u00E1lidas:");
		lblNewLabel_1.setBounds(18, 88, 93, 16);
		panelResultados.add(lblNewLabel_1);
		
		JLabel lblDocumentosVlidos = new JLabel("Documentos v\u00E1lidos:");
		lblDocumentosVlidos.setBounds(230, 88, 146, 16);
		panelResultados.add(lblDocumentosVlidos);
		
		JLabel lblOtrosArchivosTexto = new JLabel("Otros archivos:");
		lblOtrosArchivosTexto.setBounds(230, 32, 107, 16);
		panelResultados.add(lblOtrosArchivosTexto);
		
		lblCantidadDirectorios = new JLabel("0");
		lblCantidadDirectorios.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblCantidadDirectorios.setBounds(123, 32, 33, 16);
		panelResultados.add(lblCantidadDirectorios);
		
		lblCantidadArchivos = new JLabel("0");
		lblCantidadArchivos.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblCantidadArchivos.setBounds(123, 60, 61, 16);
		panelResultados.add(lblCantidadArchivos);
		
		lblFotosValidas = new JLabel("0");
		lblFotosValidas.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFotosValidas.setBounds(123, 88, 61, 16);
		panelResultados.add(lblFotosValidas);
		
		lblDocumentosValidos = new JLabel("0");
		lblDocumentosValidos.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDocumentosValidos.setBounds(377, 88, 33, 16);
		panelResultados.add(lblDocumentosValidos);
		
		lblOtrosArchivos = new JLabel("0");
		lblOtrosArchivos.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblOtrosArchivos.setBounds(349, 32, 61, 16);
		panelResultados.add(lblOtrosArchivos);
		
		JLabel lblFotosPng = new JLabel("Fotos PNG:");
		lblFotosPng.setBounds(42, 116, 77, 16);
		panelResultados.add(lblFotosPng);
		
		JLabel lblFotosJpg = new JLabel("Fotos JPG:");
		lblFotosJpg.setBounds(42, 148, 71, 16);
		panelResultados.add(lblFotosJpg);
		
		JLabel lblFotosBmp = new JLabel("Fotos BMP:");
		lblFotosBmp.setBounds(39, 176, 72, 16);
		panelResultados.add(lblFotosBmp);
		
		lblFotosPNG = new JLabel("0");
		lblFotosPNG.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFotosPNG.setBounds(123, 116, 61, 16);
		panelResultados.add(lblFotosPNG);
		
		lblFotosJPG = new JLabel("0");
		lblFotosJPG.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFotosJPG.setBounds(123, 148, 61, 16);
		panelResultados.add(lblFotosJPG);
		
		lblFotosBMP = new JLabel("0");
		lblFotosBMP.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFotosBMP.setBounds(123, 176, 61, 16);
		panelResultados.add(lblFotosBMP);
	}
	
	private void generarNuevaEjecucion() {
		String ruta = this.txtRuta.getText().trim();
		this.txtRuta.setText("");
		File directorio = new File(ruta);
		if (!directorio.isDirectory()) {
			this.lblMensaje.setText(Textos.NUEVAEJECUCION_LBL_RUTA_INVALIDA);
		}else{
			Resultados resultados = this.principal.getDirectoryService().createNewDirectoryStructure(ruta);
			this.mostrarResultados(resultados);
			this.lblMensaje.setText(Textos.NUEVAEJECUCION_LBL_MAS_RESULTADOS);
		}
	}
	
	private void mostrarResultados(Resultados resultados) {
		this.panelResultados.setEnabled(true);
		this.lblCantidadDirectorios.setText(String.valueOf(resultados.getCantidadDirectorios()));
		this.lblCantidadArchivos.setText(String.valueOf(resultados.getCantidadArchivos()));
		this.lblFotosValidas.setText(String.valueOf(resultados.getCantidadFotosValidas()));
		this.lblDocumentosValidos.setText(String.valueOf(resultados.getCantidadDocumentosValidos()));
		this.lblOtrosArchivos.setText(String.valueOf(resultados.getCantidadOtrosArchivos()));
		this.lblFotosPNG.setText(String.valueOf(resultados.getCantidadFotosPNG()));
		this.lblFotosJPG.setText(String.valueOf(resultados.getCantidadFotosJPG()));
		this.lblFotosBMP.setText(String.valueOf(resultados.getCantidadFotosBMP()));
	}
}
