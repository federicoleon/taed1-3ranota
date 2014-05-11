package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class InformeResultados extends JDialog {

	private static final long serialVersionUID = -2640429030115806196L;
	private final JPanel contentPanel = new JPanel();
	private Principal principal;
	private JTable tablaResultadosAnteriores;
	private Resultados resultados[];
	private JTable tblDocumentosValidos;
	private JTable tblOtrosArchivos;
	private JTable tblFotosValidas;

	public InformeResultados(JFrame principal) {
		setTitle("An\u00E1lisis de directorios - Estad\u00EDsticas completas");
		setResizable(false);
		setModal(true);
		this.principal = (Principal)principal;
		setBounds(100, 100, 521, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDesdeEstaVentana = new JLabel("Seleccione el resultado que desea visualizar:");
			lblDesdeEstaVentana.setBounds(6, 19, 544, 16);
			contentPanel.add(lblDesdeEstaVentana);
		}
		{
			JButton btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalir.setBounds(431, 563, 83, 29);
			contentPanel.add(btnSalir);
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
		tblDocumentosValidos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nombre de archivo", "Formato"
			}
		));
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
		tblOtrosArchivos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nombre de archivo", "Formato"
			}
		));
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
		tblFotosValidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre de archivo", "Formato"
			}
		));
		scrollPane_3.setViewportView(tblFotosValidas);
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
	}
	
	private void cargarInformacionParaResultadoEnFila(int numeroFila) {
		Resultados resultado = this.getResultadoEnFila(numeroFila);
		DefaultTableModel modeloDocumentosValidos = (DefaultTableModel)this.tblDocumentosValidos.getModel();
		this.vaciarModeloTabla(modeloDocumentosValidos);
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
		
		DefaultTableModel modeloOtrosArchivos = (DefaultTableModel)this.tblOtrosArchivos.getModel();
		this.vaciarModeloTabla(modeloOtrosArchivos);
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
		
		DefaultTableModel modeloFotos = (DefaultTableModel)this.tblFotosValidas.getModel();
		this.vaciarModeloTabla(modeloFotos);
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
	
	private void vaciarModeloTabla(DefaultTableModel modelo) {
		for(int i=0; i<modelo.getRowCount(); i++) {
			modelo.removeRow(i);
		}
	}
}
