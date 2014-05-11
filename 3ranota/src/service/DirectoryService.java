package service;

import java.io.File;
import utils.Lista;
import model.Archivo;
import model.Ejecucion;
import model.Resultados;

public class DirectoryService {
	
	private Lista resultados;
	private int ultimoIdResultado;
	
	public DirectoryService() {
		this.resultados = new Lista();
		this.ultimoIdResultado = 1;
	}
	
	public Resultados createNewDirectoryStructure(String directoryPath) {
		Ejecucion ejecucion = new Ejecucion(directoryPath);
		this.createNewDirectoryStructure(ejecucion, ejecucion.getFilePath());
		ejecucion.procesarResultados();
		Resultados resultado = ejecucion.getResultadosEjecucion();
		resultado.setIdResultado(this.ultimoIdResultado);
		this.ultimoIdResultado++;
		this.resultados.insertar(resultado);
		return resultado;
	}
	
	private void createNewDirectoryStructure(Ejecucion ejecucion, File pathDirectory) {
		try {
			File[] files = pathDirectory.listFiles();
			for (File file : files) {
				Archivo archivo = new Archivo(file.getName());
				if (file.isDirectory()) {
					archivo.setEsDirectorio(true);
					ejecucion.agregar(archivo);
					createNewDirectoryStructure(ejecucion, file);
				} else {
					ejecucion.agregar(archivo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Lista getResultados() {
		return this.resultados;
	}
}