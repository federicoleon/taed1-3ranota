package model;

import java.io.File;

import utils.Lista;

public class Ejecucion {
	private File path;
	private Lista directorios;
	private Lista archivos;
	
	public Ejecucion(String path) {
		this.path = new File(path);
		this.directorios = new Lista();
		this.archivos = new Lista();
	}
	
	public File getFilePath() {
		return this.path;
	}
	
	public void agregarDirectorio(Archivo archivo) {
		this.directorios.agregar(archivo);
	}
	
	public void agregarArchivo(Archivo archivo) {
		this.archivos.agregar(archivo);
	}
	
	public Lista getDirectorios() {
		return this.directorios;
	}
	
	public Lista getArchivos() {
		return this.archivos;
	}
}