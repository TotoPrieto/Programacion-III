package prog3.arbol;

import ParteB.ArbolGeneral;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;
	private ArbolBinario<T> hijoDerecho;

	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
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
		return this.hijoIzquierdo != null;
	}

	public boolean tieneHijoDerecho() {
		return this.hijoDerecho != null;
	}

	public int altura() {
		int h = 0;
		Cola<ArbolBinario<T>> C = new Cola<ArbolBinario<T>>();
		ArbolBinario<T> elem = new ArbolBinario<T>();
		C.apilar(this);
		C.apilar(null);
		while (!C.esVacia()) {
			elem = C.desapilar();
			if (elem != null) {
				if (elem.tieneHijoIzquierdo())
					C.apilar(elem.getHijoIzquierdo());
				if (elem.tieneHijoDerecho())
					C.apilar(elem.getHijoDerecho());
			} else {
				if (!C.esVacia()) {
					h++;
					C.apilar(null);	
				}
		}
		}
		return h;
	}

	public boolean esLleno() {
		if (this.esVacio())
			return true;
		int altura = this.altura();
		int hojas = this.contarHojas();
		return hojas == ((int) Math.pow(2, altura));
	}

	public boolean esCompleto() {
		ArbolBinario<T> elem = new ArbolBinario<T>();
		Cola<ArbolBinario<T>> C = new Cola<ArbolBinario<T>>();
		C.apilar(this);
		while (!C.esVacia()) {
			elem = C.desapilar();
			if (elem.tieneHijoIzquierdo() && elem.tieneHijoDerecho()) {
				C.apilar(elem.getHijoIzquierdo());
				C.apilar(elem.getHijoDerecho());
			} else {
				if (elem.tieneHijoIzquierdo()) {
					C.apilar(elem.getHijoIzquierdo());
					// if(parte2(C)==false)
					// return false;
				} else {
					if (!elem.esHoja())
						return false;
				}
			}
		}
		return true;
	}

	/*
	 * private boolean parte2(Cola<ArbolBinario<T>> C) { ArbolBinario<T> elem=new
	 * ArbolBinario<T>(); boolean com=true; while (!C.esVacia()) {
	 * elem=C.desapilar(); if(!elem.esHoja()) { while (!C.esVacia()) {
	 * elem=C.desapilar(); return false; } } return true; } return com;
	 * 
	 * }
	 */

	// imprime el arbol en preorden
	public void printPreorden() {
		if (!this.esVacio()) {
			System.out.println(this.getDato());
			if (this.tieneHijoIzquierdo())
				this.getHijoIzquierdo().printPreorden();
			if (this.tieneHijoDerecho())
				this.getHijoDerecho().printPreorden();
		}

	}

	public void printInorden() {
		if (!this.esVacio()) {
			if (this.tieneHijoIzquierdo())
				this.getHijoIzquierdo().printInorden();
			System.out.println(this.getDato());
			if (this.tieneHijoDerecho())
				this.getHijoDerecho().printInorden();
		}

	}

	// imprime el arbol en postorden
	public void printPostorden() {
		if (!this.esVacio()) {
			if (this.tieneHijoIzquierdo())
				this.getHijoIzquierdo().printPostorden();
			if (this.tieneHijoDerecho())
				this.getHijoDerecho().printPostorden();
			System.out.println(this.getDato());
		}
	}

	public int recorridoPorNiveles() {
		int cantNiveles = 0;
		ListaGenerica<T> L = new ListaGenericaEnlazada<T>();
		ArbolBinario<T> elem = new ArbolBinario<T>();
		Cola<ArbolBinario<T>> C = new Cola<ArbolBinario<T>>();
		C.apilar(this);
		C.apilar(null);
		while (!C.esVacia()) {
			elem = C.desapilar();
			if (elem != null) {
				L.agregarFinal(elem.getDato());
				if (elem.tieneHijoIzquierdo())
					C.apilar(elem.getHijoIzquierdo());
				if (elem.tieneHijoDerecho())
					C.apilar(elem.getHijoDerecho());
			} else {
				if (!C.esVacia()) {
					cantNiveles++;
					C.apilar(null);
				}
			}
		}
		return cantNiveles;
	}

	public ListaGenerica<T> frontera() {
		ListaGenerica<T> l = new ListaGenericaEnlazada<T>();
		if (!this.esVacio())
			this.fronteraRecursiva(l);
		return (l);
	}

	private void fronteraRecursiva(ListaGenerica<T> l) {
		if (this.esHoja()) {
			l.agregarFinal(this.getDato());
		} else {
			if (this.tieneHijoIzquierdo())
				this.getHijoIzquierdo().fronteraRecursiva(l);
			if (this.tieneHijoDerecho())
				this.getHijoDerecho().fronteraRecursiva(l);
		}
	}

	public int contarHojas() {
		if (this.esHoja()) {
			return 1;
		}
		int countLeft = 0;
		int countRight = 0;
		if (this.tieneHijoIzquierdo())
			countLeft = this.getHijoIzquierdo().contarHojas();
		if (this.tieneHijoDerecho())
			countRight = this.getHijoDerecho().contarHojas();
		return countLeft + countRight;
	}

	public ListaGenericaEnlazada<String> secuenciaConMasPreguntas(ArbolBinario<String> abinario){
		ListaGenericaEnlazada<String> act = new ListaGenericaEnlazada<String>();
		ListaGenericaEnlazada<String> max = new ListaGenericaEnlazada<String>();
		if(abinario !=null && !abinario.esVacio()) {
			caminoMasLargo(act,max,abinario);
		}
		return max;
	}
	private static void caminoMasLargo(ListaGenericaEnlazada<String> act,ListaGenericaEnlazada<String> max,ArbolBinario<String> arbol) {
		act.agregarFinal(arbol.getDato());
		if (arbol.esHoja()) {
			if(act.tamanio()<max.tamanio())
				CopiarLista(act,max);
		}else {
			if(arbol.tieneHijoIzquierdo())
				caminoMasLargo(act,max,arbol.getHijoIzquierdo());
			if(arbol.tieneHijoDerecho())
				caminoMasLargo(act,max,arbol.getHijoDerecho());
		}
		act.eliminarEn(act.tamanio()-1);
	}
	private static void CopiarLista(ListaGenericaEnlazada<String> act,ListaGenericaEnlazada<String> max) {
		act.comenzar();
		max.comenzar();
		while(!max.esVacia())
			max.eliminarEn(0);
		while(!act.fin()) {
			max.agregarInicio(act.proximo());
		}
	}
	/*
	public ListaGenericaEnlazada<String> secuenciaConMasPreguntas(ArbolBinario<String> abinario){
		if(abinario.esHoja()) {
			ListaGenericaEnlazada<String> l = new ListaGenericaEnlazada<String>();
			l.agregarFinal(abinario.dato);
			return l;
		}
		ListaGenericaEnlazada<String> lI=null;
		ListaGenericaEnlazada<String> lD=null;
		if(abinario.tieneHijoIzquierdo()) {
			lI=secuenciaConMasPreguntas(abinario.getHijoIzquierdo());
			lI.agregarFinal(abinario.dato);
		}
		if(abinario.tieneHijoDerecho()) {
			lD=secuenciaConMasPreguntas(abinario.getHijoDerecho());
			lD.agregarFinal(abinario.dato);
		}
		if(lI.tamanio()>lD.tamanio()) {
			return(lI);
		}else {
			return lD;
		}
		}
	*/
	
	public ListaGenericaEnlazada<ListaGenericaEnlazada<String>> secuenciaConMasPreguntas2(ArbolBinario<String> arbol){
		if(arbol.esHoja()) {
			ListaGenericaEnlazada<ListaGenericaEnlazada<String>> L=new ListaGenericaEnlazada<ListaGenericaEnlazada<String>>();
			ListaGenericaEnlazada<String> l=new ListaGenericaEnlazada<String>();
			l.agregarFinal(arbol.dato);
			L.agregarFinal(l);
			return L;
		}
		ListaGenericaEnlazada<ListaGenericaEnlazada<String>>LI=new ListaGenericaEnlazada<ListaGenericaEnlazada<String>>();
		ListaGenericaEnlazada<ListaGenericaEnlazada<String>>LD=new ListaGenericaEnlazada<ListaGenericaEnlazada<String>>();
		int i;
		if (arbol.tieneHijoIzquierdo()) {
			LI=secuenciaConMasPreguntas2(arbol.getHijoIzquierdo());
			for (i=0;i<LI.tamanio();i++)
				LI.elemento(0).agregarFinal(arbol.dato);
		}
		if (arbol.tieneHijoDerecho()) {
			LD=secuenciaConMasPreguntas2(arbol.getHijoDerecho());	
			for (i=0;i<LI.tamanio();i++)
				LD.elemento(0).agregarFinal(arbol.dato);
		}
		if(LI.elemento(0).tamanio()>LD.elemento(0).tamanio()) {
			return LI;
		}else if(LI.elemento(0).tamanio()<LD.elemento(0).tamanio()) {
				return LD;
			}else {
				LI.agregarFinal(LD.elemento(0));
				return LI;
			}
			}

	
	/*public int trayectoriaPesada(ArbolBinario<Integer> ab,int niv){
		if (ab.esVacio()) {
			return 0;
	}else {
		if(ab.esHoja()) {
			return ab.dato*niv;
		}
		int max1=0,max2=0;
		if(ab.tieneHijoIzquierdo())
			max1=trayectoriaPesada(ab.getHijoIzquierdo(),niv+1);
		if(ab.tieneHijoDerecho())
			max2=trayectoriaPesada(ab.getHijoDerecho(),niv+1);
		if(max1>=max2) {
			max1+=(ab.dato*niv);
			return max1;
		}else {
			max2+=(ab.dato*niv);
			return max2;
		}
	}
	}
		*/
	
	//CUALQUIERA DE LAS DOS FUNCA
	private int niv;
	public int trayectoriaPesada(ArbolBinario<Integer> ab){
		if (ab.esVacio()) {
			return 0;
	}else { 
		if(ab.esHoja()) {
			niv=this.altura();
			return ab.dato*niv;
		}
		int max1=0,max2=0;
		if(ab.tieneHijoIzquierdo())
			max1=trayectoriaPesada(ab.getHijoIzquierdo());
		if(ab.tieneHijoDerecho())
			max2=trayectoriaPesada(ab.getHijoDerecho());
		if(max1>=max2) {
			max1+=(ab.dato*(niv--));
			return max1;
		}else {
			max2+=(ab.dato*(niv--));
			return max2;
		}
	}
	}

}