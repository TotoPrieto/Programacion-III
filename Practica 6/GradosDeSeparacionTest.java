public class GradosDeSeparacionTest {
    public static void main(String args[]) {
    // create an undirected graph with the given structure
    Grafo<String> grafo = new GrafoImplListAdy<String>();

    Vertice<String> vA = new VerticeImplListAdy<String>("A");
    Vertice<String> vB = new VerticeImplListAdy<String>("B");
    Vertice<String> vC = new VerticeImplListAdy<String>("C");
    Vertice<String> vD = new VerticeImplListAdy<String>("D");

    grafo.agregarVertice(vA);
    grafo.agregarVertice(vB);
    grafo.agregarVertice(vC);
    grafo.agregarVertice(vD);

    grafo.conectar(vA, vB);
    grafo.conectar(vA, vC);
    grafo.conectar(vB, vC);
    grafo.conectar(vC, vD);

    grafo.conectar(vB, vA);
    grafo.conectar(vC, vA);
    grafo.conectar(vC, vB);
    grafo.conectar(vD, vC);

    GradosDeSeparacion relaciones = new GradosDeSeparacion();

    int maximoGradoDeSeparacion = relaciones.maximoGradoDeSeparacion(grafo);
    System.out.println("Maximo grado de separacion: " +
        maximoGradoDeSeparacion);
  }
}

