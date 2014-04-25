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
			this.comienzo = nodo;
			nodo.setAnterior(this.comienzo);
		}else{
			Nodo aux = this.comienzo;
			while(aux.getSiguiente() != null) {
				aux = aux.getSiguiente();
			}
			aux.setSiguiente(nodo);
			nodo.setAnterior(aux);
		}
		this.tamanioActual ++;
	}
	
	public int size() {
		return this.tamanioActual;
	}
}