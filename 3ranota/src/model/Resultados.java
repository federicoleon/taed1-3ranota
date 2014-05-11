package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import utils.Constantes;
import utils.Lista;
import utils.Nodo;
import utils.Validaciones;

public class Resultados {
	private int idResultado;
	private Ejecucion ejecucion;
	private Lista documentosValidos;
	private Lista otrosArchivos;
	private int cantidadArchivos;
	private int cantidadDirectorios;
	private Lista fotosJPG;
	private Lista fotosBMP;
	private Lista fotosPNG;
	
	public Resultados(Ejecucion ejecucion) {
		this.ejecucion = ejecucion;
		this.documentosValidos = new Lista();
		this.otrosArchivos = new Lista();
		this.cantidadDirectorios = 0;
		this.fotosJPG = new Lista();
		this.fotosBMP = new Lista();
		this.fotosPNG = new Lista();
		this.procesarResultados();
	}
	
	private void procesarResultados() {
		Nodo aux = this.ejecucion.getArchivos().getComienzo();
		if(aux != null) {
			do {
				Archivo archivo = (Archivo)aux.getObjeto();
				if(archivo.esDirectorio()) {
					this.cantidadDirectorios ++;
				} else {
					if(Validaciones.esFormatoImagenValido(archivo.getNombre())) {
						archivo.setEsFotoValida(true);
						if(Constantes.PNG.equalsIgnoreCase(archivo.getExtension())) {
							this.fotosPNG.insertar(archivo);
						}
						if(Constantes.JPG.equalsIgnoreCase(archivo.getExtension()) || Constantes.JPEG.equalsIgnoreCase(archivo.getExtension())) {
							this.fotosJPG.insertar(archivo);
						}
						if(Constantes.BMP.equalsIgnoreCase(archivo.getExtension())) {
							this.fotosBMP.insertar(archivo);
						}
					} else if(Validaciones.esFormatoDocumentoValido(archivo.getNombre())) {
						archivo.setEsDocumentoValido(true);
						this.documentosValidos.insertar(archivo); 
					} else {
						this.otrosArchivos.insertar(archivo);
					}
					this.cantidadArchivos++;
				}
				aux = aux.getSiguiente();
			} while(aux != null);
		}
		this.documentosValidos = this.documentosValidos.ordernar(Constantes.LISTA_ORDENADA_ASC);
		this.crearArchivoDocumentosValidos();
	}
	
	private void crearArchivoDocumentosValidos() {
		try {
			StringBuilder path = new StringBuilder(this.ejecucion.getPathInicial());
			path.append("/");
			path.append(Constantes.RESULTADOS_ARCHIVO_DOCUMENTOS);
			BufferedWriter out = new BufferedWriter(new FileWriter(path.toString()));
			Nodo aux = this.documentosValidos.ordernar(Constantes.LISTA_ORDENADA_DESC).getComienzo();
			if(aux != null) {
				do {
					Archivo archivo = (Archivo)aux.getObjeto();
					out.write(archivo.getNombre());
			        out.newLine();
			        aux = aux.getSiguiente();
				} while(aux != null);
			}
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPathInicial() {
		return this.ejecucion.getPathInicial();
	}
	
	public int getCantidadFotosValidas() {
		return (
			this.getCantidadFotosBMP() + 
			this.getCantidadFotosJPG() + 
			this.getCantidadFotosPNG()
		);
	}

	public Lista getDocumentosValidos() {
		return this.documentosValidos;
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
		return this.fotosJPG.size();
	}
	
	public Lista getFotosJPG() {
		return this.fotosJPG;
	}

	public int getCantidadFotosBMP() {
		return this.fotosBMP.size();
	}
	
	public Lista getFotosBMP() {
		return this.fotosBMP;
	}

	public int getCantidadFotosPNG() {
		return fotosPNG.size();
	}
	
	public Lista getFotosPNG() {
		return this.fotosPNG;
	}
	
	public int getCantidadDocumentosValidos() {
		return this.documentosValidos.size();
	}
	
	public int getCantidadOtrosArchivos() {
		return this.otrosArchivos.size();
	}
	
	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}
	
	public int getIdResultado() {
		return this.idResultado;
	}
	
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("ID: ");
		resultado.append(this.idResultado);
		resultado.append("\n");
		resultado.append("Cantidad de directorios: ");
		resultado.append(this.cantidadDirectorios);
		resultado.append("\n");
		resultado.append("Cantidad de archivos: ");
		resultado.append(this.cantidadArchivos);
		resultado.append("\n");
		resultado.append("Cantidad fotos v‡lidas: ");
		resultado.append(this.getCantidadFotosValidas());
		resultado.append("\n");
		resultado.append("	Fotos PNG: ");
		resultado.append(this.fotosPNG.size());
		resultado.append("\n");
		resultado.append("	Fotos JPG: ");
		resultado.append(this.fotosJPG.size());
		resultado.append("\n");
		resultado.append("	Fotos BMP: ");
		resultado.append(this.fotosBMP.size());
		resultado.append("\n");
		resultado.append("Cantidad de documentos v‡lidos: ");
		resultado.append(this.documentosValidos.size());
		resultado.append("\n");
		resultado.append("Cantidad de otros archivos: ");
		resultado.append(this.otrosArchivos.size());
		return resultado.toString();
	}
}