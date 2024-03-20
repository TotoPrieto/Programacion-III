public class Cola<T> {
	private int tope= 50;
	private int act=0;
	private ListaGenerica<T> nodo;
	public Cola() {
		 nodo=new ListaGenericaEnlazada<T>();
	}

	public void encolar(T elem) {
		if(act<tope) {
		nodo.agregarFinal(elem);
		act++;
		}
		}

	public T desencolar() {
		if(act!=0) {
			T aux= nodo.elemento(0);
			nodo.eliminarEn(0);
			act--;
			return aux;
	}
		return null;
	}

	public T tope() {
		return nodo.elemento(0);
	}

	public boolean esVacia() {
		return nodo.tamanio()==0;
	}

} 