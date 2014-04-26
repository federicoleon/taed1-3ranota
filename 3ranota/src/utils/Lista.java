package utils;

public class Lista {
	private Nodo comienzo;
	private int tamanioActual;
	private boolean esListaOrdenada;
	
	public Lista(boolean esListaOrdenada) {
		this.tamanioActual = 0;
		this.esListaOrdenada = esListaOrdenada;
	}
	
	public Nodo getComienzo() {
		return this.comienzo;
	}
	
	public boolean insertar(Object objeto) {
		if(this.esListaOrdenada) {
			return this.insertarOrdenado(objeto);
		}
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
	
	public boolean insertarOrdenado(Object objeto) {
		return true;
	}
	
	public int size() {
		return this.tamanioActual;
	}
}