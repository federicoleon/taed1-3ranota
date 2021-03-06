package model;

import java.io.File;
import utils.Lista;

public class Ejecucion {
	private File path;
	private Lista archivos;
	private Resultados resultados;
	
	public Ejecucion(String path) {
		this.path = new File(path);
		this.archivos = new Lista();
	}
	
	public String getPathInicial() {
		return this.path.getAbsolutePath();
	}
	
	public File getFilePath() {
		return this.path;
	}
	
	public void agregar(Archivo archivo) {
		this.archivos.insertarAlFrente(archivo);
	}
	
	public Lista getArchivos() {
		return this.archivos;
	}
	
	public void procesarResultados() {
		this.resultados = new Resultados(this);
	}
	
	public Resultados getResultadosEjecucion() {
		return this.resultados;
	}
}