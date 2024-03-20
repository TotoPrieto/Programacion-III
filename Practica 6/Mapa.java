public class Mapa {

  //Construccion basica
  public Grafo<String> mapaCiudades;

  public Mapa(Grafo<String> ciudades){
    this.mapaCiudades=ciudades;
  }

  public Mapa(){
  
  }

  public void setmapaCiudades(Grafo<String> ciudades){
    this.mapaCiudades=ciudades;
  }

  public Grafo<String> getmapaCiudades(){
    return mapaCiudades;
  }

  //Metodos

// <----------------------------------------------------------------------------------------------------------------------------------------------------------------->

//1.    Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a ciudad2 en caso que se pueda llegar, 
//si no retorna la lista vacía. (Sin tener en cuenta el combustible).

public ListaGenerica<String> devolverCamino(String ciudad1, String ciudad2){
  ListaGenerica<String> listaCaminos = new ListaGenericaEnlazada<String>();
    
  if(!mapaCiudades.esVacio()){
    boolean[] visitado= new boolean[mapaCiudades.listaDeVertices().tamanio()];
    Vertice<String> origen= devolverVerice(ciudad1);
    
    if (origen.dato().equals(ciudad1)){

        //Doy por hecho que es un unico grafo conexo, porque si no se conectan chau.  
      devolverCamino(listaCaminos, visitado, origen, ciudad2);
      
      }
      
    }
  return listaCaminos;
}


  private void devolverCamino (ListaGenerica<String> listaCamino, boolean[] visitado, Vertice<String> vActual, String destino){
    visitado[vActual.posicion()]=true;  
    
    if(vActual.dato().equals(destino)){  
      listaCamino.agregarInicio(vActual.dato());
  
    }else{
      ListaGenerica<Arista<String>> ady= mapaCiudades.listaDeAdyacentes(vActual);
      ady.comenzar();
      Arista<String> siguiente=null;

      while(!ady.fin()&&(listaCamino.esVacia())){
        siguiente= ady.proximo();
        //si el siguiente Vertice ya fue visitado, pregunto por otro
        if(!visitado[siguiente.verticeDestino().posicion()])
          devolverCamino(listaCamino, visitado, siguiente.verticeDestino(), destino);
  
      }  
      //Una vez que sale(porque puede ser que la lista ya no este vacia) pregunta si agrega si ciudad, y vuelve de la recursion
      if(!listaCamino.esVacia())
        listaCamino.agregarInicio(vActual.dato());
    }
    }
  
  private Vertice<String> devolverVerice(String c1){
    
    Vertice<String> vertAct=null;

    int i=0;
    int numTot= mapaCiudades.listaDeVertices().tamanio()-1;
    
    vertAct= mapaCiudades.vertice(i);
    
    while((i<numTot)&&(!vertAct.dato().equals(c1))){
      i++;
      vertAct= mapaCiudades.vertice(i);
    }

    return vertAct;
  }


  // <----------------------------------------------------------------------------------------------------------------------------------------------------------------->
  
  //2.    Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2, sin pasar por las ciudades que están
  // contenidas en la lista ciudades pasada por parámetro, si no existe camino retorna la lista vacía. (Sin tener en cuenta el combustible).

  public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, ListaGenerica<String> ciudades){
    ListaGenerica<String> listaCaminos = new ListaGenericaEnlazada<String>();
    
    if(!mapaCiudades.esVacio()){
      boolean[] visitado= new boolean[mapaCiudades.listaDeVertices().tamanio()];
      Vertice<String> origen= devolverVerice(ciudad1);
    
      if (origen.dato().equals(ciudad1)){

        //Doy por hecho que es un unico grafo conexo, porque si no se conectan chau.
        
        devolverCaminoExceptuando(listaCaminos, ciudades, visitado, origen, ciudad2);
      
      }
      
    }

    return listaCaminos;
  }

  private void devolverCaminoExceptuando(ListaGenerica<String> listaCamino, ListaGenerica<String> ciudades,boolean[] visitado, Vertice<String> vActual, String destino){
    
    visitado[vActual.posicion()]=true;  
    
    if(vActual.dato().equals(destino) && !pertenece(vActual.dato(),ciudades)){  
      listaCamino.agregarInicio(vActual.dato()); 
  
    }else{
      ListaGenerica<Arista<String>> ady= mapaCiudades.listaDeAdyacentes(vActual);
      ady.comenzar();
      
      while(!ady.fin()&&(listaCamino.esVacia())){
        Arista<String> siguiente= ady.proximo();
        //si el siguiente Vertice ya fue visitado, pregunto por otro
        if(!visitado[siguiente.verticeDestino().posicion()])
          devolverCaminoExceptuando(listaCamino, ciudades, visitado, siguiente.verticeDestino(), destino);
  
      }  
      //Una vez que sale(porque puede ser que la lista ya no este vacia) pregunta si agrega si ciudad, y vuelve de la recursion
      if(!listaCamino.esVacia())
        listaCamino.agregarInicio(vActual.dato());
    }
    }

  private boolean pertenece(String actual, ListaGenerica<String> ciudades){
    if(!ciudades.esVacia()){
      ciudades.comenzar();
      String aux;
      while(!ciudades.fin()){
        aux= ciudades.proximo();
        if(actual.equals(aux))
          return true;
      }    
  }
  return false;
}

// <----------------------------------------------------------------------------------------------------------------------------------------------------------------->

//3.     Retorna la lista de ciudades que forman el camino más corto para llegar de ciudad1 a ciudad2, si no existe camino retorna la lista vacía. 
//(Las rutas poseen la distancia). (Sin tener en cuenta el combustible).

public ListaGenerica<String> caminoMasCorto(String ciudad1, String ciudad2){
  ListaGenerica<String> listaCorto= new ListaGenericaEnlazada<String>();
  ListaGenerica<String> posibleMin= new ListaGenericaEnlazada<String>();

  if(!mapaCiudades.esVacio()){
    boolean[] visitado= new boolean[mapaCiudades.listaDeVertices().tamanio()];
    Vertice<String> origen =devolverVerice(ciudad1);
  
    if(origen.dato().equals(ciudad2)){
      int min=9999;
      int pesoActual=0;
      caminoMasCorto(listaCorto, origen, min, ciudad2, visitado, posibleMin, pesoActual);
    }
  }
  return listaCorto;
}

//Lo hago que devuelva un int para poder alterar el minimo, y encontrar el mas corto. La lista al ser dinamica se devuelve sola.
private int caminoMasCorto(ListaGenerica<String> listaCorto, Vertice<String> actual, int min, String c2, boolean[] visitado, ListaGenerica<String> posibleMin, int pesoActual){
  visitado[actual.posicion()]=true;
  posibleMin.agregarFinal(actual.dato());
  
  if(actual.dato().equals(c2)){
  //  visitado[actual.posicion()]=false; //Asi otros caminos pueden llegar de vuelta a esta ciudad
  
    if(min>pesoActual){
      listaCorto= clonar(posibleMin); //Como es mas corto clona la lista a la lista importante
    //  posibleMin.eliminar(actual.dato());
      min= pesoActual;
    }
    return min; // Hago que pesoActual sea por referencia
  }

  ListaGenerica<Arista<String>> ady= mapaCiudades.listaDeAdyacentes(actual);
  ady.comenzar();  
  
  while(!ady.fin()){
    Arista<String> siguiente= ady.proximo();
    pesoActual+=siguiente.peso(); //Solo lo uso para saber cual de los caminos esta mas cerca. 
  
    if(!visitado[siguiente.verticeDestino().posicion()]){
      min=caminoMasCorto(listaCorto, siguiente.verticeDestino(), min, c2, visitado, posibleMin, pesoActual); //Devuelve el min asi lo puedo mantener actualizado
    }
    if(posibleMin.tamanio()>1)//no quiero eliminar el origen de la lista en caso de tener que borrarla
      posibleMin.eliminarEn(posibleMin.tamanio()-1);
    //Elimino en la lista temporal el actual, pero fuera del while porque tal vez en el mismo camino, con otra arista llega antes
    visitado[siguiente.verticeDestino().posicion()]=false; //Desmarco por si otro camino pasa por aca de vuelta
    pesoActual-=siguiente.peso();
    }  
    
    return min;
}


public ListaGenerica<String> clonar(ListaGenerica<String> original){
  ListaGenerica<String> nueva= new ListaGenericaEnlazada<String>();
  original.comenzar();
  while(!original.fin())
    nueva.agregarFinal(original.proximo());
  return nueva;
}


//<----------------------------------------------------------------------------------------------------------------------------------------------------------------->

//4.     Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2. El auto no debe quedarse sin combustible y no puede
//cargar. Si no existe camino retorna la lista vacía.

public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
  ListaGenerica<String> caminoSinC= new ListaGenericaEnlazada<String>();
  
  if(!mapaCiudades.esVacio()){
    Vertice<String> origen= devolverVerice(ciudad1);

    if(origen.dato().equals(ciudad1)){
      boolean [] visitado= new boolean[mapaCiudades.listaDeVertices().tamanio()];
      caminoSinCargarCombustible(caminoSinC, origen, visitado, ciudad2, tanqueAuto);
    }
  }
  return caminoSinC;
}
//Como me pide un camino en el que llegue con el tanque y sin cargar, no reviso todos los caminos posibles. Devuelvo el primero que encuentra

private void caminoSinCargarCombustible(ListaGenerica<String> caminoSinC, Vertice<String> actual, boolean [] visitado, String ciudad2, int tanqueAuto){
  visitado[actual.posicion()]=true;

  if(actual.dato().equals(ciudad2) && tanqueAuto>0){
    caminoSinC.agregarInicio(actual.dato());
  }else{

    ListaGenerica<Arista<String>> ady= mapaCiudades.listaDeAdyacentes(actual);
    ady.comenzar();
    Arista<String> siguiente=null;
    while(!ady.fin() && caminoSinC.esVacia()){
      siguiente= ady.proximo();
      if(!visitado[siguiente.verticeDestino().posicion()])
        caminoSinCargarCombustible(caminoSinC, siguiente.verticeDestino(), visitado, ciudad2, (tanqueAuto-siguiente.peso()));
      if(!caminoSinC.esVacia())
        caminoSinC.agregarInicio(siguiente.verticeDestino().dato());
    }
  }

}


// <----------------------------------------------------------------------------------------------------------------------------------------------------------------->

//5.      Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2 teniendo en cuenta que el auto debe cargar la menor
// cantidad de veces. El auto no se debe quedar sin combustible en medio de una ruta, además puede completar su tanque al llegar a cualquier ciudad. 
// Si no existe camino retorna la lista vacía.



public ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
  ListaGenerica<String> caminoMenosC= new ListaGenericaEnlazada<String>();
  ListaGenerica<String> posibleCamino= new ListaGenericaEnlazada<String>();
  if(!mapaCiudades.esVacio()){
    boolean[] visitado= new boolean[mapaCiudades.listaDeVertices().tamanio()];
    Vertice<String> origen =devolverVerice(ciudad1);
    if(origen.dato().equals(ciudad2)){
      int cant=0; //Se va a mandar cuantas veces cargo en un camino
      int cantMin=9999; //Se va a ir actualizando esta variable y se compara con la anterior
      caminoConMenosCargaDeCombustible(caminoMenosC, origen, cant, ciudad2, visitado, posibleCamino, cantMin, tanqueAuto);

    }
  }
  return caminoMenosC;
}


// Lo hago int para poder actualizar la cantidad de veces minima que cargo
private int caminoConMenosCargaDeCombustible(ListaGenerica<String> caminoMenosC, Vertice<String> actual, int cant, String c2, boolean[] visitado, ListaGenerica<String> posibleCamino, int cantMin, int tanque){
  visitado[actual.posicion()]=true;
  posibleCamino.agregarFinal(actual.dato());
  
  if(actual.dato().equals(c2)){
    visitado[actual.posicion()]=false; //Asi otros caminos pueden llegar de vuelta a esta ciudad
  
    if(cantMin>cant && tanque>0){
      caminoMenosC= clonar(posibleCamino); //Como es mas corto clona la lista a la lista importante
      posibleCamino.eliminar(c2);
      return cantMin; // Hago que pesoActual sea por referencia
    }
  }

  ListaGenerica<Arista<String>> ady= mapaCiudades.listaDeAdyacentes(actual);
  ady.comenzar();  
  cant++;  
  while(!ady.fin()){
    Arista<String> siguiente= ady.proximo();
    
    if(!visitado[siguiente.verticeDestino().posicion()]){
      cantMin=caminoConMenosCargaDeCombustible(caminoMenosC, siguiente.verticeDestino(), cant, c2, visitado, posibleCamino, cantMin, (tanque-siguiente.peso())); //Devuelve el min asi lo puedo mantener actualizado
    }
    visitado[siguiente.verticeDestino().posicion()]=false; //Desmarco por si otro camino pasa por aca de vuelta
    }  
    cant--;
    posibleCamino.eliminar(actual.dato());


  return cantMin;
}

}
