package ParteB;


public class ArbolGeneral<Integer> {
	private Integer dato;
	private ArbolGeneral<Integer> hijoIzquierdo;   
	private ArbolGeneral<Integer> hijoDerecho; 


	public ArbolGeneral() {
		super();
	}

	public ArbolGeneral(Integer dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public Integer getDato() {
		return dato;
	}

	public void setDato(Integer dato) {
		this.dato = dato;
	}
	

	public ArbolGeneral<Integer> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolGeneral<Integer> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolGeneral<Integer> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolGeneral<Integer> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int sumaMaximaVertical() {
		if(this.esVacio())
			return 0;
		return RecursionSumaMax(this);
	}

	private int RecursionSumaMax(ArbolGeneral<Integer> arbol) {
		if(arbol.esHoja()) {
			return (int) arbol.getDato();
		}
		int maxI=0,maxD=0;
		if(arbol.tieneHijoIzquierdo())
			maxI=RecursionSumaMax(arbol.getHijoIzquierdo());
		if(arbol.tieneHijoDerecho())
			maxD=RecursionSumaMax(arbol.getHijoDerecho());
		if((maxI>=maxD)) {
			return maxI+(int)arbol.getDato();
		}else { 
			return maxD+(int)arbol.getDato();
		}
	}
	
	public int sumaMaximaHorizontal() {
		if(this.esVacio())
			return 0;
		Cola <ArbolGeneral<Integer>> C= new Cola<ArbolGeneral<Integer>>();
		ArbolGeneral<Integer> elem=new ArbolGeneral<Integer>();
		int act=0;
		int max=0;
		C.apilar(this);
		C.apilar(null);
		while(!C.esVacia()) {
			elem=C.desapilar();
			if(elem!=null) {
				act+=(int)elem.getDato();
				if(elem.tieneHijoIzquierdo())
					C.apilar(elem.getHijoIzquierdo());
				if(elem.tieneHijoDerecho())
					C.apilar(elem.getHijoDerecho());
				}else{
					if(!C.esVacia()) {
						C.apilar(null);
						if(max<act)
							max=act;
						act=0;
					}
				}	
			}
			return max;
		}
	
		
}

