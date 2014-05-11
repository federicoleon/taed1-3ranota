package utils;

public class Arbol {
	private Object objeto;
	private Arbol padre;
	private Lista hijos;
	
	public Arbol() {}
	
	public Arbol(Object objeto) {
		this.objeto = objeto;
	}
	
	public void agregarHijo(Arbol arbol) {
		if(this.hijos == null) {
			this.hijos = new Lista();
		}
		this.hijos.insertarAlFinal(arbol);
	}
	
	public void setHijos(Lista hijos) {
		this.hijos = hijos;
	}
	
	public void setPadre(Arbol padre) {
		this.padre = padre;
	}
	
	public Object getObjeto() {
		return this.objeto;
	}
	
	public Arbol getPadre() {
		return this.padre;
	}
	
	public Lista getHijos() {
		return this.hijos;
	}
	
	public boolean esRaiz() {
		return (this.padre == null);
	}
	
	public boolean esRama() {
		return ( !this.esRaiz() && !this.esHoja());
	}
	
	public boolean esHoja() {
		return (this.hijos == null);
	}
	
	public int getAltura() {
		int altura = 1;
		if( this.objeto == null && this.getHijos() == null ) {
			return 0;
		}
		if(this.getHijos() != null) {
			altura += getAltura(this.getHijos());
		}
		return altura;
	}
	
	private int getAltura(Lista hijos) {
		Nodo nodo = hijos.getComienzo();
		if(nodo == null) {
			return 0;
		}
		int altura = 0;
		do {
			Arbol arbol = (Arbol)nodo.getObjeto();
			if(arbol.getHijos() != null) {
				altura ++;
				altura += this.getAltura(arbol.getHijos());
			}
			nodo = nodo.getSiguiente();
		} while(nodo != null);
		return altura;
	}
	
	public int getPeso() {
		int peso = 0;
		if(this.esHoja()) {
			peso++;
		}else{
			peso += this.getPeso(this.getHijos());
		}
		return peso;
	}
	
	private int getPeso(Lista hijos) {
		Nodo nodo = hijos.getComienzo();
		if(nodo == null) {
			return 0;
		}
		int peso = 0;
		do {
			Arbol arbol = (Arbol)nodo.getObjeto();
			if(arbol.esHoja()) {
				peso ++;
			}else{
				peso += getPeso(arbol.getHijos());
			}
			nodo = nodo.getSiguiente();
		} while(nodo != null);
		return peso;
	}
	
	public String getArbolEnTexto() {
		StringBuilder resultado = new StringBuilder();
		String tabulado = "|-- ";
		if(this.getObjeto() == null) {
			return resultado.toString();
		}
		resultado.append(tabulado);
		resultado.append((String)this.getObjeto());
		resultado.append("\n");
		resultado.append(this.getArbolEnTexto(this.getHijos(), tabulado));
		return resultado.toString();
	}
	
	private String getArbolEnTexto(Lista hijos, String tabulado) {
		tabulado = this.getNuevoTabulado(tabulado);
		StringBuilder resultado = new StringBuilder();
		Nodo nodo = hijos.getComienzo();
		do {
			Arbol arbol = (Arbol)nodo.getObjeto();
			resultado.append(tabulado);
			if(arbol.getHijos() == null) {
				resultado.append((String)arbol.getObjeto());
				resultado.append("\n");
			}else{
				resultado.append((String)arbol.getObjeto());
				resultado.append("\n");
				resultado.append(getArbolEnTexto(arbol.getHijos(), tabulado));
			}
			nodo = nodo.getSiguiente();
		} while(nodo != null);
		return resultado.toString();
	}
	
	private String getNuevoTabulado(String tabuladoActual) {
		StringBuilder resultado = new StringBuilder("");
		for(int i=0; i<tabuladoActual.length(); i++) {
			resultado.append(" ");
		}
		resultado.append("|-- ");
		return resultado.toString();
	}
}