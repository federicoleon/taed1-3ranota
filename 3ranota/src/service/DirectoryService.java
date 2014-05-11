package service;

import java.io.File;
import utils.Arbol;
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
		File baseArbol = new File(directoryPath);
		Arbol arbol = this.crearArbolDesdeDirectorio(baseArbol);
		resultado.setArbolGenerado(arbol);
		this.ultimoIdResultado++;
		this.resultados.insertarAlFrente(resultado);
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
	
	private Arbol crearArbolDesdeDirectorio(File raiz) {
		Arbol arbol = new Arbol(raiz.getName());
		try {
			File[] files = raiz.listFiles();
			for (File file : files) {
				if(!file.getName().equalsIgnoreCase(".DS_Store")) {
					if (file.isDirectory()) {
						Arbol aux = crearArbolDesdeDirectorio(file);
						aux.setPadre(arbol);
						arbol.agregarHijo(aux);
					} else {
						Arbol aux = new Arbol(file.getName());
						aux.setPadre(arbol);
						arbol.agregarHijo(aux);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arbol;
	}
	
	public Lista getResultados() {
		return this.resultados;
	}
}