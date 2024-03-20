package ParteA;
import ParteB.ListaGenerica;
import ParteB.ListaGenericaEnlazada;
import ParteB.Cola;

	public class ArbolGeneral<T> {

		private T dato;

		private ListaGenerica<ArbolGeneral<T>> hijos = new ListaGenericaEnlazada<ArbolGeneral<T>>();

		public T getDato() {
			return dato;
		}

		public void setDato(T dato) {
			this.dato = dato;
		}

		public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
			this.hijos = hijos;
		}
		
		public ArbolGeneral() {
			}
		
		public ArbolGeneral(T dato) {
			this.dato = dato;
			this.hijos = new ListaGenericaEnlazada<ArbolGeneral<T>>();
		}

		public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
			this(dato);
			this.hijos = hijos;
		}

		public ListaGenerica<ArbolGeneral<T>> getHijos() {
			return this.hijos;
		}

		public void agregarHijo(ArbolGeneral<T> unHijo) {

			this.getHijos().agregarFinal(unHijo);
		}

		public boolean esHoja() {

			return !this.tieneHijos();
		}
		
		public boolean tieneHijos() {
			return this.hijos != null && !this.hijos.esVacia();
		}
		
		public boolean esVacio() {

			return this.dato == null && !this.tieneHijos();
		}

		

		public void eliminarHijo(ArbolGeneral<T> hijo) {
			if (this.tieneHijos()) {
				ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
				if (hijos.incluye(hijo)) 
					hijos.eliminar(hijo);
			}
		}
		
		public ListaGenericaEnlazada<T> preOrden() {
			return null;
		}
		
		public Integer altura() {
			if(!this.esVacio()) {
				if(this.esHoja()) {
					return 0;
				}
				ListaGenerica<ArbolGeneral<T>> hijos=this.getHijos();
				hijos.comenzar();
				int h=0;
				int act=0;
				while(!hijos.fin()) {
					ArbolGeneral<T> hijito=hijos.proximo();
					act=hijito.altura();
					if(act>h)
						h=act;
			}
			return 1+h;
		}
			return 0;
		}
		
		public boolean include(T dato) {
			if(!this.esVacio()) {
				if(this.dato!=dato) {
					boolean res=false;
					if(!this.esHoja()) {
						ListaGenerica<ArbolGeneral<T>>hijos=this.getHijos();
						hijos.comenzar();
						ArbolGeneral<T> act;
						while(!hijos.fin()&& res==false) {
								act=hijos.proximo();
								if(act.include(dato)==true)
									res=true;
							}
						}
					return res;
					}
				return true;
			}
			return false;
		}
		
		public Integer nivel(T dato) {
			ArbolGeneral<T> aux=null;
			Cola<ArbolGeneral<T>> C=new Cola<ArbolGeneral<T>>();
			C.apilar(this);
			C.apilar(null);
			int nivel=0;
			while(!C.esVacia()) {
				aux=C.desapilar();
				if(aux!=null) {
					if(aux.dato==dato) {
						return nivel;
					}else {
						ListaGenerica<ArbolGeneral<T>> hijos=aux.getHijos();
						hijos.comenzar();
						while(!hijos.fin()) 
							C.apilar(hijos.proximo());	
						}
				}else {
					if(!C.esVacia()) {
						nivel++;
						C.apilar(null);
					}
				}
			}
			return -1;
			
		}

		public Integer ancho() {
			ArbolGeneral<T> aux=null;
			Cola<ArbolGeneral<T>> C=new Cola<ArbolGeneral<T>>();
			C.apilar(this);
			C.apilar(null);
			int cant=0,max=0;
			while(!C.esVacia()) {
				aux=C.desapilar();
				if(aux!=null) {
					ListaGenerica<ArbolGeneral<T>> hijos=aux.getHijos();
					hijos.comenzar();
					while(!hijos.fin()) {
						C.apilar(hijos.proximo());
						cant++;
					}
					}else {
						if(cant>max)
							max=cant;
						if(!C.esVacia()) {
							cant=0;
							C.apilar(null);
					}
				}
				return max;
			}
			return 1;
	}
	
	
	
}
