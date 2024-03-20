

public class AristaImpl<T> implements Arista<T> {
	private Vertice<T> destino;
	private int peso;
	
	public AristaImpl(Vertice<T> dest, int p){
			destino = dest;
			peso = p;
	}

	@Override
	public Vertice<T> verticeDestino() {
		return destino;
	}
	@Override
	public int peso() {
		return peso;
	}

	@Override
	public Object getVerticeDestino() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getVerticeDestino'");
	}

	
} 

