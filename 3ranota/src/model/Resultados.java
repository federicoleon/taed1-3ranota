package model;

import utils.Constantes;
import utils.Lista;
import utils.Nodo;
import utils.Validaciones;

public class Resultados {
	
	private Ejecucion ejecucion;
	private Lista fotosValidas;
	private Lista documentosValidos;
	private Lista otrosArchivos;
	private int cantidadDirectorios;
	private int cantidadArchivos;
	private int cantidadFotosJPG;
	private int cantidadFotosBMP;
	private int cantidadFotosPNG;
	
	public Resultados(Ejecucion ejecucion) {
		this.ejecucion = ejecucion;
		this.fotosValidas = new Lista(Constantes.LISTA_NO_ORDENADA);
		this.documentosValidos = new Lista(Constantes.LISTA_ORDENADA);
		this.otrosArchivos = new Lista(Constantes.LISTA_NO_ORDENADA);
		this.cantidadDirectorios = 0;
		this.cantidadFotosJPG = 0;
		this.cantidadFotosBMP = 0;
		this.cantidadFotosPNG = 0;
		this.procesarResultados();
	}
	
	public Lista getFotosValidas() {
		return fotosValidas;
	}

	public void setFotosValidas(Lista fotosValidas) {
		this.fotosValidas = fotosValidas;
	}

	public Lista getDocumentosValidos() {
		return documentosValidos;
	}

	public void setDocumentosValidos(Lista documentosValidos) {
		this.documentosValidos = documentosValidos;
	}

	public Lista getOtrosArchivos() {
		return otrosArchivos;
	}

	public void setOtrosArchivos(Lista otrosArchivos) {
		this.otrosArchivos = otrosArchivos;
	}

	public int getCantidadDirectorios() {
		return cantidadDirectorios;
	}

	public int getCantidadArchivos() {
		return cantidadArchivos;
	}

	public int getCantidadFotosJPG() {
		return cantidadFotosJPG;
	}

	public int getCantidadFotosBMP() {
		return cantidadFotosBMP;
	}

	public int getCantidadFotosPNG() {
		return cantidadFotosPNG;
	}
	
	private void procesarResultados() {
		Nodo aux = this.ejecucion.getArchivos().getComienzo();
		if(aux != null) {
			do {
				Archivo archivo = (Archivo)aux.getObjeto();
				if(archivo.esDirectorio()) {
					this.cantidadDirectorios ++;
				} else {
					this.cantidadArchivos ++;
					if(Validaciones.esFormatoImagenValido(archivo.getNombre())) {
						archivo.setEsFotoValida(true);
						if(Constantes.PNG.equalsIgnoreCase(archivo.getExtension())) {
							this.cantidadFotosPNG ++;
						}
						if(Constantes.JPG.equalsIgnoreCase(archivo.getExtension()) || Constantes.JPEG.equalsIgnoreCase(archivo.getExtension())) {
							this.cantidadFotosJPG ++;
						}
						if(Constantes.BMP.equalsIgnoreCase(archivo.getExtension())) {
							this.cantidadFotosBMP ++;
						}
						this.fotosValidas.insertar(archivo);
					} else if(Validaciones.esFormatoDocumentoValido(archivo.getNombre())) {
						archivo.setEsDocumentoValido(true);
						this.documentosValidos.insertar(archivo); 
					} else {
						this.otrosArchivos.insertar(archivo);
					}
				}
				aux = aux.getSiguiente();
			} while(aux != null);
		}
	}
	
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("Cantidad de directorios: ");
		resultado.append(this.cantidadDirectorios);
		resultado.append("\n");
		resultado.append("Cantidad de archivos: ");
		resultado.append(this.cantidadArchivos);
		resultado.append("\n");
		resultado.append("Cantidad fotos v‡lidas: ");
		resultado.append(this.fotosValidas.size());
		resultado.append("\n");
		resultado.append("	Fotos PNG: ");
		resultado.append(this.cantidadFotosPNG);
		resultado.append("\n");
		resultado.append("	Fotos JPG: ");
		resultado.append(this.cantidadFotosJPG);
		resultado.append("\n");
		resultado.append("	Fotos BMP: ");
		resultado.append(this.cantidadFotosBMP);
		resultado.append("\n");
		resultado.append("Cantidad de documentos v‡lidos: ");
		resultado.append(this.documentosValidos.size());
		resultado.append("\n");
		resultado.append("Cantidad de otros archivos: ");
		resultado.append(this.otrosArchivos.size());
		return resultado.toString();
	}
}