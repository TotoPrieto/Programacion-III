public class GuiaDeTurismo {
  

ListaGenerica <String> caminoConMenorNrodeViajes(Grafo<String> grafo, String puntoInteresOrigen, String puntoInteresDestino){
  ListaGenerica<String> caminoMenos=null;

  if( grafo!=null && !grafo.esVacio()){
    Vertice<String> origen=buscarVertice(puntoInteresOrigen, grafo);
    Vertice<String> destino=buscarVertice(puntoInteresDestino, grafo);
  
    if(origen.dato().equals(puntoInteresOrigen) && destino.dato().equals(puntoInteresDestino)){
      boolean visitados[]= new boolean [grafo.listaDeVertices().tamanio()];
      ResultadoTurismo res= new ResultadoTurismo();
      ResultadoTurismo posibleRes=new ResultadoTurismo();
    
      caminoConMenorNrodeViajes(grafo, origen, destino, visitados, res, posibleRes);
      return res.getLista();
    }
  
  }
  
  return caminoMenos;
} 

public Vertice<String> buscarVertice(String buscado, Grafo<String> grafo){
Vertice<String> vertAct=null;
int i=0;
int numTot= grafo.listaDeVertices().tamanio()-1;

vertAct=grafo.vertice(i);

while(i<numTot && !vertAct.dato().equals(buscado)){
  i++;
  vertAct= grafo.vertice(i);
}
return vertAct;
}


public void caminoConMenorNrodeViajes(Grafo<String> grafo, Vertice<String> actual, Vertice<String> destino, boolean visitados[], ResultadoTurismo res, ResultadoTurismo posibleRes){
  visitados[actual.posicion()]=true;
  posibleRes.getLista().agregarFinal(actual.dato());

  if(actual.dato().equals(destino.dato())){

    if((res.getMin())<(posibleRes.getMin())){
      res.setLista(posibleRes.getLista().clonar());

      res.setMin(posibleRes.getMin());
    }
  }else{

    ListaGenerica<Arista<String>> ady=grafo.listaDeAdyacentes(actual);
    ady.comenzar();
    int minAnterior;
    while(!ady.fin()){
      Arista<String> aux=ady.proximo();
    
      if(!visitados[aux.verticeDestino().posicion()]){
        minAnterior=posibleRes.getMin();
        posibleRes.chequearMin(aux.peso());
        
        caminoConMenorNrodeViajes(grafo, aux.verticeDestino(), destino, visitados, res, posibleRes);
      
        posibleRes.setMin(minAnterior);
        posibleRes.getLista().eliminarEn(posibleRes.getLista().tamanio()-1);
        visitados[aux.verticeDestino().posicion()]=false;
      }
    }
  }

}



}