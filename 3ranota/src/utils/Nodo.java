package utils;

public class Nodo {
	
	protected Object objeto;
	protected Nodo siguiente;
	protected Nodo anterior;
	
	public Nodo(Object objeto) {
		this.objeto = objeto;
	}
	
	public Object getObjeto() {
		return objeto;
	}
	
	protected void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	public Nodo getSiguiente() {
		return siguiente;
	}
	
	protected void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}
	
	public Nodo getAnterior() {
		return anterior;
	}
	
	protected void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}
}