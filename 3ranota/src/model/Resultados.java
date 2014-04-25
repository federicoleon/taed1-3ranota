package model;

import utils.Lista;

public class Resultados {
	
	private Ejecucion ejecucion;
	private Lista fotosValidas;
	private Lista documentosValidos;
	private int cantidadDirectorios;
	private int cantidadFotosJPG;
	private int cantidadFotosBMP;
	private int cantidadFotosPNG;
	
	public Resultados(Ejecucion ejecucion) {
		this.ejecucion = ejecucion;
		this.fotosValidas = new Lista();
		this.documentosValidos = new Lista();
		this.cantidadDirectorios = 0;
		this.cantidadFotosJPG = 0;
		this.cantidadFotosBMP = 0;
		this.cantidadFotosPNG = 0;
		
		this.procesarResultados();
	}
	
	private void procesarResultados() {
		System.out.println(this.ejecucion.getArchivos().size());
		System.out.println(this.ejecucion.getDirectorios().size());
	}
}