public class GuiaDeTurismoTest {
     public static void main(String[] args) {
        Grafo<String> grafo = new GrafoImplListAdy<>();

        // Crear los vértices
        Vertice<String> casa = new VerticeImplListAdy<>("Mi casa");
        Vertice<String> repu = new VerticeImplListAdy<>("La Repu");
        Vertice<String> catedral = new VerticeImplListAdy<>("La Catedral");
        Vertice<String> nacio = new VerticeImplListAdy<>("El Nacional");
        Vertice<String> cine = new VerticeImplListAdy<>("El cine");

        // Agregar los vértices al grafo
        grafo.agregarVertice(casa);
        grafo.agregarVertice(repu);
        grafo.agregarVertice(catedral);
        grafo.agregarVertice(nacio);
        grafo.agregarVertice(cine);

        // Conectar los puntos de interes entre sí (conexiones bidireccionales)
        grafo.conectar(casa, repu,  18);
        grafo.conectar(repu, casa, 18);
        grafo.conectar(casa, catedral, 20);
        grafo.conectar(catedral, casa, 20);
        grafo.conectar(casa, nacio, 12);
        grafo.conectar(nacio, casa, 12);
        grafo.conectar(repu, cine, 40);
        grafo.conectar(cine, repu,  40);
        grafo.conectar(catedral, cine, 10);
        grafo.conectar(cine, catedral, 10);
        grafo.conectar(nacio, cine, 15);
        grafo.conectar(cine, nacio, 15);

        GuiaDeTurismo guia = new GuiaDeTurismo();
        ListaGenerica<String> lista= guia.caminoConMenorNrodeViajes(grafo, "Mi casa", "El cine");
        if(lista==null){
          System.out.println("Vacio");
        }else{
        System.out.println("Menor cantidad de caminos: " +  lista.toString());
        }
    }
}