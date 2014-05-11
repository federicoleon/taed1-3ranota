package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utils.Constantes;
import utils.Lista;
import utils.Nodo;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import model.Archivo;
import model.Resultados;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class InformeResultados extends JDialog {

	private static final long serialVersionUID = -2640429030115806196L;
	private final JPanel contentPanel = new JPanel();
	private Principal principal;
	private JTable tablaResultadosAnteriores;
	private Resultados resultados[];
	private JTable tblDocumentosValidos;
	private JTable tblOtrosArchivos;
	private JTable tblFotosValidas;
	private JTextArea txtArbolGenerado;
	private JLabel lblPeso;
	private JLabel lblAltura;
	private Resultados resultadoActual;

	public InformeResultados(JFrame principal) {
		setTitle("An\u00E1lisis de directorios - Estad\u00EDsticas completas");
		setResizable(false);
		setModal(true);
		this.principal = (Principal)principal;
		setBounds(100, 100, 850, 581);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDesdeEstaVentana = new JLabel("Seleccione el resultado que desea visualizar:");
			lblDesdeEstaVentana.setBounds(6, 19, 508, 16);
			contentPanel.add(lblDesdeEstaVentana);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 47, 508, 103);
		contentPanel.add(scrollPane);
		
		tablaResultadosAnteriores = new JTable();
		tablaResultadosAnteriores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				cargarInformacionParaResultadoEnFila(tablaResultadosAnteriores.getSelectedRow());
			}
		});
		tablaResultadosAnteriores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ruta analizada"
			}
		));
		tablaResultadosAnteriores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tablaResultadosAnteriores.getColumnModel().getColumn(0).setMaxWidth(30);
		scrollPane.setViewportView(tablaResultadosAnteriores);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 162, 508, 389);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Documentos v\u00E1lidos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 30, 246, 166);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 21, 234, 139);
		panel_1.add(scrollPane_1);
		
		tblDocumentosValidos = new JTable();
		tblDocumentosValidos.setModel(this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_DOCUMENTOS_VALIDOS));
		scrollPane_1.setViewportView(tblDocumentosValidos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Otros archivos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(264, 30, 238, 166);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 21, 226, 139);
		panel_2.add(scrollPane_2);
		
		tblOtrosArchivos = new JTable();
		tblOtrosArchivos.setModel(this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_OTROS_ARCHIVOS));
		scrollPane_2.setViewportView(tblOtrosArchivos);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Fotos v\u00E1lidas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(6, 208, 496, 166);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(6, 23, 484, 137);
		panel_3.add(scrollPane_3);
		
		tblFotosValidas = new JTable();
		tblFotosValidas.setModel(this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_FOTOS_VALIDAS));
		scrollPane_3.setViewportView(tblFotosValidas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(727, 14, 117, 29);
		contentPanel.add(btnSalir);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u00C1rbol generado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(526, 47, 318, 504);
		contentPanel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 86, 306, 412);
		panel_4.add(scrollPane_4);
		
		txtArbolGenerado = new JTextArea();
		txtArbolGenerado.setEditable(false);
		scrollPane_4.setViewportView(txtArbolGenerado);
		
		JLabel lblPesotxt = new JLabel("Peso:");
		lblPesotxt.setBounds(18, 29, 50, 16);
		panel_4.add(lblPesotxt);
		
		JLabel lblNewLabel = new JLabel("Altura:");
		lblNewLabel.setBounds(18, 58, 50, 16);
		panel_4.add(lblNewLabel);
		
		lblPeso = new JLabel("0");
		lblPeso.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPeso.setBounds(68, 29, 61, 16);
		panel_4.add(lblPeso);
		
		lblAltura = new JLabel("0");
		lblAltura.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAltura.setBounds(68, 58, 61, 16);
		panel_4.add(lblAltura);
		this.cargarResultados();
	}
	
	private void cargarResultados() {
		DefaultTableModel modeloResultadosAnteriores = (DefaultTableModel)this.tablaResultadosAnteriores.getModel();
		Lista aux = this.principal.getDirectoryService().getResultados();
		Nodo nodo = aux.getComienzo();
		this.resultados = new Resultados[aux.size()];
		if(nodo != null) {
			int i=0;
			do {
				Resultados resultado = (Resultados)nodo.getObjeto();
				this.resultados[i] = resultado;
				Object[] filaTabla = new Object[2];
				filaTabla[0] = resultado.getIdResultado();
				filaTabla[1] = resultado.getPathInicial();
				modeloResultadosAnteriores.addRow(filaTabla);
				nodo = nodo.getSiguiente();
				i++;
			} while(nodo != null);
		}
		this.tablaResultadosAnteriores.setModel(modeloResultadosAnteriores);
		this.tablaResultadosAnteriores.repaint();
	}
	
	private void cargarInformacionParaResultadoEnFila(int numeroFila) {
		Resultados resultado = this.getResultadoEnFila(numeroFila);
		if(resultado != this.resultadoActual) {
			this.resultadoActual = resultado;
			DefaultTableModel modeloDocumentosValidos = this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_DOCUMENTOS_VALIDOS);
			Nodo nodoDocumentosValidos = resultado.getDocumentosValidos().getComienzo();
			if(nodoDocumentosValidos != null) {
				Archivo archivo;
				do {
					archivo = (Archivo)nodoDocumentosValidos.getObjeto();
					Object fila[] = new Object[] { archivo.getNombre(), archivo.getExtension().toUpperCase() };
					modeloDocumentosValidos.addRow(fila);
					nodoDocumentosValidos = nodoDocumentosValidos.getSiguiente();
				} while(nodoDocumentosValidos != null);
			}
			this.tblDocumentosValidos.setModel(modeloDocumentosValidos);
			this.tblDocumentosValidos.repaint();
			
			DefaultTableModel modeloOtrosArchivos = this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_OTROS_ARCHIVOS);
			Nodo otrosArchivos = resultado.getOtrosArchivos().getComienzo();
			if(otrosArchivos != null) {
				Archivo archivo;
				do {
					archivo = (Archivo)otrosArchivos.getObjeto();
					Object fila[] = new Object[] { archivo.getNombre(), archivo.getExtension().toUpperCase() };
					modeloOtrosArchivos.addRow(fila);
					otrosArchivos = otrosArchivos.getSiguiente();
				} while(otrosArchivos != null);
			}
			this.tblOtrosArchivos.setModel(modeloOtrosArchivos);
			this.tblOtrosArchivos.repaint();
			
			DefaultTableModel modeloFotos = this.getNuevoModelo(Constantes.RESULTADOS_COLUMNAS_TBL_FOTOS_VALIDAS);
			Nodo fotos = resultado.getFotosBMP().getComienzo();
			if(fotos != null) {
				Archivo archivo;
				do {
					archivo = (Archivo)fotos.getObjeto();
					Object fila[] = new Object[] { archivo.getNombre(), archivo.getExtension().toUpperCase() };
					modeloFotos.addRow(fila);
					fotos = fotos.getSiguiente();
				} while(fotos != null);
			}
			fotos = resultado.getFotosJPG().getComienzo();
			if(fotos != null) {
				Archivo archivo;
				do {
					archivo = (Archivo)fotos.getObjeto();
					Object fila[] = new Object[] { archivo.getNombre(), archivo.getExtension().toUpperCase() };
					modeloFotos.addRow(fila);
					fotos = fotos.getSiguiente();
				} while(fotos != null);
			}
			fotos = resultado.getFotosPNG().getComienzo();
			if(fotos != null) {
				Archivo archivo;
				do {
					archivo = (Archivo)fotos.getObjeto();
					Object fila[] = new Object[] { archivo.getNombre(), archivo.getExtension().toUpperCase() };
					modeloFotos.addRow(fila);
					fotos = fotos.getSiguiente();
				} while(fotos != null);
			}
			this.tblFotosValidas.setModel(modeloFotos);
			this.tblFotosValidas.repaint();
			
			this.lblPeso.setText(String.valueOf(resultado.getArbolGenerado().getPeso()));
			this.lblAltura.setText(String.valueOf(resultado.getArbolGenerado().getAltura()));
			this.txtArbolGenerado.setText(resultado.getArbolGenerado().getArbolEnTexto());
		}
	}
	
	private Resultados getResultadoEnFila(int numeroFila) {
		int idResultado = (Integer)this.tablaResultadosAnteriores.getValueAt(numeroFila, 0);
		for(int i=0; i<this.resultados.length; i++) {
			if(this.resultados[i].getIdResultado() == idResultado) {
				return this.resultados[i];
			}
		}
		return null;
	}
	
	private DefaultTableModel getNuevoModelo(Object[] columnas) {
		DefaultTableModel resultado = new DefaultTableModel();
		for(int i=0; i<columnas.length; i++) {
			resultado.addColumn(columnas[i]);
		}
		return resultado;
	}
}
