public class ResultadoTurismo {
  int minPasajeros;
  ListaGenerica<String> lista;

  public ResultadoTurismo(){
    minPasajeros=0;
    lista=new ListaGenericaEnlazada<>();
  }

  public void chequearMin(int num){
    if(minPasajeros!=0){
      if(minPasajeros>num){
        minPasajeros=num;
      }
    }else{
      this.minPasajeros=num; //Esto esta en el else, porque 0 siempre va a ser mas chico, asi que es para el primer caso
    }
  }
  
public int getMin(){
  return minPasajeros;
}

public void setMin(int num){
  minPasajeros=num;
}

  public ListaGenerica<String> getLista(){
    return this.lista;
  }

  public void setLista(ListaGenerica<String> lista){
    this.lista=lista;
    }

}




