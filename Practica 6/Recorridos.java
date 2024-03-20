public class Recorridos<T>{

public  ListaGenerica<Vertice<T>>  dfs(Grafo<T> grafo){
  ListaGenerica<Vertice<T>> L= new  ListaGenericaEnlazada<Vertice<T>>() ;
  if(!grafo.esVacio()){
    boolean[] visitado = new boolean[grafo.listaDeVertices().tamanio()];
    for(int i=0; i<visitado.length; i++){
      if(!visitado[i])
        dfsRec(i,grafo, L, visitado);
      }
    }
  return L;
}

private void dfsRec(int i, Grafo<T> grafo, ListaGenerica<Vertice<T>> L, boolean[] visitados){
  visitados[i]= true;
  Vertice<T> v= grafo.listaDeVertices().elemento(i);
  L.agregarFinal(v);
  ListaGenerica<Arista<T>> ady= grafo.listaDeAdyacentes(v);
  ady.comenzar();

  while(!ady.fin()){
    int j= ady.proximo().verticeDestino().posicion();
      if(!visitados[j]){
        dfsRec(j, grafo, L, visitados);
    }
  }
}



public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo){
  ListaGenerica<Vertice<T>> L= new  ListaGenericaEnlazada<Vertice<T>>() ;
  if(!grafo.esVacio()){
    boolean[] visitados= new boolean[grafo.listaDeVertices().tamanio()];
    for(int i=0; i<visitados.length; i++){
      if(!visitados[i])
        bfsRec(i,grafo,L, visitados);
      }
  }
  return L;
}

private void bfsRec (int i, Grafo<T> grafo, ListaGenerica<Vertice<T>> L, boolean[] visitados){
  Cola<Vertice<T>> cola= new Cola<Vertice<T>>();
  Vertice<T> inicial = grafo.listaDeVertices().proximo();
  ListaGenerica<Arista<T>> listaDeAdyacentes=null;

  cola.encolar(inicial);
  
  visitados[inicial.posicion()]=true;
  
  while(!cola.esVacia()){
    Vertice<T> actual= cola.desencolar();
    L.agregarFinal(actual);
    
    listaDeAdyacentes= grafo.listaDeAdyacentes((actual));
    listaDeAdyacentes.comenzar();
    
    while(!listaDeAdyacentes.fin()){
      Vertice<T> vSig= listaDeAdyacentes.proximo().verticeDestino();
      
      if(!visitados[vSig.posicion()]){
        visitados[vSig.posicion()]=true;
        cola.encolar(vSig);
      }
    }
  }
}

}


//bfs(Grafo<T> grafo): ListaGenerica<Vertice<T>> // Retorna una lista de vértices con el
//recorrido en amplitud del grafo recibido como parámetro.


