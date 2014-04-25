package model;

import utils.Lista;
import utils.Nodo;

public class Resultados {
	
	private Ejecucion ejecucion;
	private Lista fotosValidas;
	private Lista documentosValidos;
	private Lista otrosArchivos;
	private int cantidadDirectorios;
	private int cantidadFotosJPG;
	private int cantidadFotosBMP;
	private int cantidadFotosPNG;
	
	public Resultados(Ejecucion ejecucion) {
		this.ejecucion = ejecucion;
		this.fotosValidas = new Lista();
		this.documentosValidos = new Lista();
		this.otrosArchivos = new Lista();
		this.cantidadDirectorios = 0;
		this.cantidadFotosJPG = 0;
		this.cantidadFotosBMP = 0;
		this.cantidadFotosPNG = 0;
		this.procesarResultados();
	}
	
	private void procesarResultados() {
		Nodo aux = this.ejecucion.getArchivos().getComienzo();
		if(aux != null) {
			do {
				Archivo archivo = (Archivo)aux.getObjeto();
				if(archivo.esDirectorio()) {
					this.cantidadDirectorios ++;
				} else if(archivo.esFotoValida()) {
					this.fotosValidas.agregar(archivo);
				} else if(archivo.esDocumentoValido()) {
					this.documentosValidos.agregar(archivo); 
				} else {
					this.otrosArchivos.agregar(archivo);
				}
				aux = aux.getSiguiente();
			} while(aux != null);
		}
		System.out.println("Cantidad de directorios: " + this.cantidadDirectorios);
		System.out.println("Cantidad fotos v‡lidas: " + this.fotosValidas.size());
		System.out.println("Cantidad de documentos v‡lidos: " + this.documentosValidos.size());
		System.out.println("Cantidad de otros archivos: " + this.otrosArchivos.size());
	}
}