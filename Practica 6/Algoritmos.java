public class Algoritmos<T>{
  


  //<--------------------------------------------------------------------------------------------->
  //1.     Retorna true si un dígrafo contiene un subgrafo cuadrado, false en caso contrario. 
  // Un subgrafo cuadrado es un ciclo simple de longitud 4

  public boolean subgrafoCuadrado(Grafo<T> grafo){
    boolean b=false;
    
    if(!grafo.esVacio()){
      int i=0;
      int longi;
      ListaGenerica<Vertice<T>> lisV= grafo.listaDeVertices();
      Vertice<T> act;
      boolean[] visitado= new boolean [lisV.tamanio()];
    
      while(b==false && i<lisV.tamanio()){
        longi=0;
        act=lisV.elemento(i);
        b=subgrafoCuadrado(act, lisV.proximo(), visitado, grafo, longi); //Mando como aux al lisV.proximo() para que no se toque el act y el aux al principio
        i++;
      }
    }
    return b;
  }

 //siempre mando el mismo actual. Lo que hago es revisar todas sus aristas para ver si alguna se conecta consigo misma
 private boolean subgrafoCuadrado(Vertice<T>actual, Vertice<T> aux, boolean[] visitado, Grafo<T> grafo, int longi){
  boolean b=false;
  
  if(!actual.dato().equals(aux.dato()) ){
    visitado[aux.posicion()]=true;
  }

  if(actual.dato().equals(aux.dato()) && longi==4){
    return true;
  }else{

  ListaGenerica<Arista<T>> ady= grafo.listaDeAdyacentes(aux);
  ady.comenzar();
  longi++;
  while(!ady.fin() && b==false){
  Arista<T> sig=ady.proximo();
   int pos= sig.verticeDestino().posicion();

   if(!visitado[pos])
      b=subgrafoCuadrado(actual, sig.verticeDestino(), visitado, grafo, longi);

    visitado[pos]=false;  
  }
}
  return b;
 }


  //<--------------------------------------------------------------------------------------------->
  //Retorna el grado del digrafo pasado como parámetro. El grado de un digrafo es el de su vértice de grado máximo. 
  //El grado de un vértice en un grafo dirigido es la suma del número de aristas que salen de él (grado de salida) 
  //y el número de aristas que entran en él (grado de entrada).

  public int getGrado(Grafo<T> grafo){
    int max=0, grado=0;
  
    if(!grafo.esVacio()){
      ListaGenerica<Vertice<T>> listV= grafo.listaDeVertices();
      int gradActS, i;
      int [] gradActE= new int[listV.tamanio()];
      Vertice<T> act= null;
      ListaGenerica<Arista<T>> sig= null;
      Arista<T> ari;
    
      for(i=0; i<listV.tamanio(); i++){
        act= grafo.vertice(i);
        sig=grafo.listaDeAdyacentes(act);
        sig.comenzar();
      
        while(!sig.fin()){ //corroboro las aristas de salida
          ari=sig.proximo();
          gradActE[ari.verticeDestino().posicion()]++;
        }
      }
      
      for(i=0; i<listV.tamanio(); i++){
          gradActS=0;
          act= grafo.vertice(i);
          sig=grafo.listaDeAdyacentes(act);
          gradActS=sig.tamanio();

          grado= gradActS + gradActE[i];
          if(grado>max)
            max=grado;
      }
    }
    return grado;
}


//// Retorna true si el grafo dirigido pasado como parámetro tiene al menos un ciclo, false en caso contrario.


 public boolean tieneCiclo(Grafo<T> grafo){
  boolean b=false;
  
  if(!grafo.esVacio()){
    int i=0;
    int longi;
    ListaGenerica<Vertice<T>> lisV= grafo.listaDeVertices();
    Vertice<T> act;
    boolean[] visitado= new boolean [lisV.tamanio()];
  
    while(b==false && i<lisV.tamanio()){
      longi=0;
      act=lisV.elemento(i);
      b=tieneCiclo(act, lisV.proximo(), visitado, grafo, longi); //Mando como aux al lisV.proximo() para que no se toque el act y el aux al principio
      //No importa que en el ultimo llamado del for mande null el proximo, total su valor no lo uso hasta que recursa
      i++;
    }
  }
  return b;
 }

 //siempre mando el mismo actual. Lo que hago es revisar todas sus aristas para ver si alguna se conecta consigo misma
 private boolean tieneCiclo(Vertice<T>actual, Vertice<T> aux, boolean[] visitado, Grafo<T> grafo, int longi){
  boolean b=false;

  if(!actual.dato().equals(aux.dato()))
    visitado[aux.posicion()]=true;

  if(actual.dato().equals(aux.dato()) && longi>0){
    return true;
  }else{

  
  
  ListaGenerica<Arista<T>> ady= grafo.listaDeAdyacentes(aux);
  ady.comenzar();
  longi++;
  
  while(!ady.fin() && b==false){
  Arista<T> sig=ady.proximo();
   int pos= sig.verticeDestino().posicion();

   if(!visitado[pos])
      b=tieneCiclo(actual, sig.verticeDestino(), visitado, grafo, longi);

    visitado[pos]=false;  
  }
}
  return b;
 }


}
