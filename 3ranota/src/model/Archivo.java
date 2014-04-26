package model;

import utils.Validaciones;

public class Archivo {
	private String nombre;
	private boolean esDirectorio;
	private boolean esFotoValida;
	private boolean esDocumentoValido;
	
	public Archivo(String nombre) {
		this.nombre = nombre;
		this.esDirectorio = false;
		this.esFotoValida = false;
		this.esDocumentoValido = false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean esFotoValida() {
		return esFotoValida;
	}
	
	public void setEsFotoValida(boolean esFotoValida) {
		this.esFotoValida = esFotoValida;
	}
	
	public boolean esDocumentoValido() {
		return esDocumentoValido;
	}
	
	public void setEsDocumentoValido(boolean esDocumentoValido) {
		this.esDocumentoValido = esDocumentoValido;
	}
	
	public boolean esDirectorio() {
		return esDirectorio;
	}
	
	public void setEsDirectorio(boolean esDirectorio) {
		this.esDirectorio = esDirectorio;
	}
	
	public String getExtension() {
		return Validaciones.getExtension(this.getNombre());
	}
	
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("Nombre: ");
		resultado.append(this.getNombre());
		resultado.append("\n");
		resultado.append("Es directorio: ");
		if(this.esDirectorio()) {
			resultado.append("Si.");
		}else{
			resultado.append("No.");
		}
		resultado.append("\n");
		resultado.append("Es foto valida: ");
		if(this.esFotoValida()) {
			resultado.append("Si.");
		}else{
			resultado.append("No.");
		}
		resultado.append("\n");
		resultado.append("Es documento valido: ");
		if(this.esDocumentoValido()) {
			resultado.append("Si.");
		}else{
			resultado.append("No.");
		}
		return resultado.toString();
	}
}