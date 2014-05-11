package utils;

import model.Archivo;

public class Lista {
	private Nodo comienzo;
	private int tamanioActual;
	
	public Lista() {
		this.tamanioActual = 0;
	}
	
	public Nodo getComienzo() {
		return this.comienzo;
	}
	
	public boolean insertar(Object objeto) {
		Nodo nodo = new Nodo(objeto);
		if(this.comienzo == null) {
			nodo.setAnterior(this.comienzo);
			this.comienzo = nodo;
		}else{
			this.comienzo.setAnterior(nodo);
			nodo.setSiguiente(this.comienzo);
			this.comienzo = nodo;
		}
		this.tamanioActual ++;
		return true;
	}
	
	public Archivo[] getContenido() {
		Archivo resultado[] = new Archivo[this.size()];
		Nodo aux = this.getComienzo();
		if(aux == null) {
			return resultado;
		}
		int i = 0;
		Archivo archivo = null;
		do {
			archivo = (Archivo)aux.getObjeto();
			resultado[i] = archivo;
			aux = aux.getSiguiente();
			i++;
		} while(aux != null);
		return resultado;
	}
	
	public Lista ordernar(int criterioOrdenamiento) {
		switch(criterioOrdenamiento) {
			case Constantes.LISTA_ORDENADA_ASC:
				return this.getListaOrdenadaASC();
				
			case Constantes.LISTA_ORDENADA_DESC:
				return this.getListaOrdenadaDESC();
				
			default:
				return this;
		}
	}
	
	public int size() {
		return this.tamanioActual;
	}
	
	private Lista getListaOrdenadaASC() {
		Lista lista = new Lista();
		Archivo aux[] = Ordenamiento.ordenarConBurbuja(this.getContenido());
		for(int i=0; i<aux.length; i++) {
			lista.insertar(aux[i]);
		}
		return lista;
	}
	
	private Lista getListaOrdenadaDESC() {
		Lista lista = new Lista();
		Archivo aux[] = Ordenamiento.ordenarConBurbuja(this.getContenido());
		for(int i=(aux.length - 1); i>=0; i--) {
			lista.insertar(aux[i]);
		}
		return lista;
	}
}