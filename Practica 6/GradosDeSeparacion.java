public class GradosDeSeparacion {
  
  public int maximoGradoDeSeparacion(Grafo<String> grafo){
    int grado=0;
    
    if(grafo!=null && !grafo.esVacio()){
      ListaGenerica<Vertice<String>> listaV=grafo.listaDeVertices();
      listaV.comenzar();
      Vertice<String> act;
      int num;
  
      while(!listaV.fin()){
        act=listaV.proximo();

        boolean[] visitado= new boolean[listaV.tamanio()];  
        num=maximoGradoDeSeparacion(grafo, act, visitado);
    
        if(grado<num){
          grado=num;
        }
      }
    }
    return grado;
  }

  private int maximoGradoDeSeparacion(Grafo<String> grafo, Vertice<String> actual, boolean[] visitado){
    int cant= -1; //Es menos uno por si no tiene adyacentes
    visitado[actual.posicion()]=true;
    Cola<Vertice<String>> c=new Cola<Vertice<String>>();
    ListaGenerica<Arista<String>> ady;

    c.encolar(actual);
    c.encolar(null);

    while(!c.esVacia()){
      Vertice<String> aux=c.desencolar();

      
      if(aux!=null){
        ady= grafo.listaDeAdyacentes(aux);
        ady.comenzar();
      
        while(!ady.fin()){
          Vertice<String> sig=ady.proximo().verticeDestino();
      
          if(visitado[sig.posicion()]==false){
            c.encolar(sig);
            visitado[sig.posicion()]=true;
          }
        }

      }else{
        cant++; //En el caso de cant=-1, se pone en 0 cuando sale del primer nodo. Si 
      
        if(!c.esVacia()){
          c.encolar(null);
        }
      }
    }
    return cant;
  }


}
