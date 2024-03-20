public class RutaMinima{

  private ListaGenerica<String> caminoMasCorto;
  private int cantBoletos;

  public RutaMinima(){
    caminoMasCorto=new ListaGenericaEnlazada<String>();
    cantBoletos=0;
  }

  public ListaGenerica<String> getLista(){
    caminoMasCorto.comenzar();
    return caminoMasCorto;
  }

  public void setLista(ListaGenerica<String> lista){
    this.caminoMasCorto=lista;
  }

  public int getBoletos(){
    return cantBoletos;
  }

  public void agregarBoletos(){
    cantBoletos++;
  }

  public void eliminarBoletos(){
    cantBoletos--;  
  }


  public void setBoletos(int i){
    cantBoletos=i;
  }



}

