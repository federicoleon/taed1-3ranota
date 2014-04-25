package utils;

public class Lista {
	private Nodo comienzo;
	private int tamanioActual;
	
	public Lista() {
		this.tamanioActual = 0;
	}
	
	public Nodo getComienzo() {
		return this.comienzo;
	}
	
	public void agregar(Object objeto) {
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
	}
	
	public int size() {
		return this.tamanioActual;
	}
}