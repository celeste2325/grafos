public interface GrafosTDA {

    void inicializarGrafo(int dim);

    void eliminarVertice(int v);

    void agregarVertice(int v);

    int[] vertices();

    void agregarAristaParaGrafoNoDirigido(int v1, int v2, int peso);
    void agregarAristaParaGrafoDirigido(int v1, int v2, int peso);

    void eliminarArista(int v1, int v2);

    boolean existeArista(int v1, int v2);

    int pesoArista(int v1, int v2);

    void mostrarMatriz();

    boolean pertenece(int x);

    int mayorArista(int v);

    int[] conjuntoAislados();

    void imparAristas();

    int[] dephtFirstSearch();
}
