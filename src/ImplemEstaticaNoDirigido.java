import java.util.PriorityQueue;

public class ImplemEstaticaNoDirigido implements GrafosTDA {

    int indice;
    int[][] matrizAdy;
    int dim;
    int[] vertices;

    public void inicializarGrafo(int dim) {
        this.dim = dim;
        indice = 0;
        matrizAdy = new int[dim][dim];
        vertices = new int[dim];
    }

    public void agregarVertice(int v) {
        if (indice < dim) {
            vertices[indice] = v;
            for (int i = 0; i < dim; i++) {
                matrizAdy[i][indice] = 0;
                matrizAdy[indice][i] = 0;
            }
            indice++;
        } else {
            System.out.println("No se puden agregar mas nodos");
        }
    }

    public int posicionDeNodo(int v) {
        for (int i = 0; i < indice; i++) {
            if (vertices[i] == v) {
                return i;
            }
        }
        return -1;

    }

    public void agregarAristaParaGrafoNoDirigido(int v1, int v2, int peso) {
        if (this.posicionDeNodo(v1) != -1 && this.posicionDeNodo(v2) != -1) {
            matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = peso;
            matrizAdy[this.posicionDeNodo(v2)][this.posicionDeNodo(v1)] = peso;//Aristas sin direccion
        } else {
            System.out.println("Alguno de los nodos no existe");
        }
    }
    public void agregarAristaParaGrafoDirigido(int v1, int v2, int peso) {
        if (this.posicionDeNodo(v1) != -1 && this.posicionDeNodo(v2) != -1) {
            matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = peso;
        } else {
            System.out.println("Alguno de los nodos no existe");
        }
    }

    public boolean contiene(int[] nums, int x) {
        if (x > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == x)
                    return true;
            }
        }
        return false;
    }

    public int[][] grafoPrim() {
        int pesoMenor = 0;
        int posPesoMenorJ = 0;
        int posPesoMenorK = 0;
        int[][] prim;
        prim = new int[dim][dim];
        int[] vVisitados;
        vVisitados = new int[dim];
        int cantVectoresEncontrados = 1; //empieza con 1 vector encontrado, el origen.
        vVisitados[0] = vertices[0]; //el nodo con el q empieza es el de la posicion 0
        int pesoArbolDeRecubrimientoMinimo = 0 ;


        for (int i = 0; i < indice - 1; i++) {
            for (int j = 0; j < cantVectoresEncontrados; j++) {
                for (int k = 0; k < indice; k++) {
                    int aux = this.posicionDeNodo(vVisitados[j]);
                    if (aux != -1) {
                        if (matrizAdy[aux][k] > 0) {
                            if (pesoMenor <= 0 && !this.contiene(vVisitados, vertices[k])) {
                                pesoMenor = matrizAdy[aux][k]; //toma el peso de esa posicion de la matriz
                                posPesoMenorJ = j;
                                posPesoMenorK = k;
                            } else if (pesoMenor > matrizAdy[aux][k]
                                    && !this.contiene(vVisitados, vertices[k])) {

                                pesoMenor = matrizAdy[aux][k];
                                posPesoMenorJ = aux;
                                posPesoMenorK = k;
                            }
                        }
                    }
                }
            }
            prim[posPesoMenorJ][posPesoMenorK] = pesoMenor;
            prim[posPesoMenorK][posPesoMenorJ] = pesoMenor;
            pesoArbolDeRecubrimientoMinimo += pesoMenor;
            vVisitados[cantVectoresEncontrados] = vertices[posPesoMenorK];
            cantVectoresEncontrados++;
            pesoMenor = 0;

        }
        System.out.println("peso del arbol de recubrimiento minimo:" + pesoArbolDeRecubrimientoMinimo);
        System.out.println();
        System.out.println("Arbol de recubrimiento minimo Prim");
        this.mostrar(prim);


        return prim;
    }

    public void mostrar(int[][] prim) {
        for (int fila = 0; fila < dim; fila++) {
            for (int columna = 0; columna < dim; columna++) {
                System.out.print(prim[fila][columna] + "   ");
            }
            System.out.println();
        }
    }

    public int[] dijkstra(int inicio, int destino) {
        int[] distancia = new int[dim + 1];
        int[] padre = new int[dim + 1];
        boolean[] visto = new boolean[dim + 1];

        //Inicialización
        for (int i = 1; i < dim + 1; i++) {
            distancia[i] = Integer.MAX_VALUE;
            padre[i] = -1;
            visto[i] = false;
        }

        distancia[inicio] = 0;
        PriorityQueue<Integer> pila = new PriorityQueue<>();
        pila.add(distancia[inicio]);

        while (!pila.isEmpty()) {
            int u = pila.poll();
            visto[u] = true;

            for (int i = 0; i < dim; i++) {
                if (matrizAdy[u][i] != 0) {
                    if (distancia[i] > distancia[u] + matrizAdy[u][i]) {
                        distancia[i] = distancia[u] + matrizAdy[u][i];
                        padre[i] = u;
                        pila.add(i);
                    }
                }
            }
        }
        imprimirCamino(padre, inicio, destino - 1);
        return distancia;
    }

    public void imprimirCamino(int[] padre, int inicio, int destino) {
        if (destino == 0) {
            System.out.println(destino + 1);
        } else {
            imprimirCamino(padre, inicio, padre[destino]);
            System.out.println(destino + 1);
        }
    }

    public void imprimirVertices() {
        // TODO Auto-generated method stub

    }
    public int mayorArista(int v) {
        int fila = this.posicionDeNodo(v);
        if (v != -1) {
            int aux = 0;
            for (int i = 0; i < indice; i++) {
                if (aux < matrizAdy[fila][i]) {
                    aux = matrizAdy[fila][i];
                }
            }
            return aux;
        } else {
            System.out.println("El nodo no se encuentra");
            return v;
        }
    }

    public int[] conjuntoAislados() {
        int[] aislados = new int[indice];
        int flag = 0;
        for (int i = 0; i < indice; i++) {
            flag = 0;
            for (int j = 0; j < indice; j++) {
                if (matrizAdy[i][j] != 0 || matrizAdy[j][i] != 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                aislados[i] = vertices[i];
                System.out.print(aislados[i] + "\t");
            }
        }
        return aislados;
    }

    public void imparAristas() {
        int contar;
        for (int i = 0; i < indice; i++) {
            contar = 0;
            for (int j = 0; j < indice; j++) {
                if (matrizAdy[i][j] != 0) {
                    contar++;
                }
            }
            if (contar % 2 != 0) {
                System.out.print(vertices[i] + "\t");
            }
        }
    }

    public int[] dephtFirstSearch() {
        return new int[0];
    }
    public void eliminarVertice(int v) {
        int aux = this.posicionDeNodo(v);
        if (aux != -1) {
            for (int i = 0; i < indice; i++) {
                matrizAdy[aux][i] = matrizAdy[indice - 1][i];
                matrizAdy[i][aux] = matrizAdy[i][indice - 1];
            }
            vertices[this.posicionDeNodo(v)] = vertices[indice - 1];
            indice--;
        } else {
            System.out.println("No se encontro el vertice");
        }
    }
    public int[] vertices() {
        int[] aux = new int[indice];
        for (int i = 0; i < indice; i++) {
            aux[i] = vertices[i];
            System.out.print(aux[i] + "\t");
        }
        System.out.println();
        return aux;
    }

    public void eliminarArista(int v1, int v2) {
        if (this.posicionDeNodo(v1) >= 0 && this.posicionDeNodo(v2) >= 0) {
            matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = 0;
        } else {
            System.out.println("No existe alguno de los nodos");
        }
    }

    public boolean existeArista(int v1, int v2) {
        return (matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] != 0);
    }

    public int pesoArista(int v1, int v2) {
        return matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)];
    }

    public void mostrarMatriz() {
        System.out.print("\t");
        this.vertices();
        System.out.println();
        for (int i = 0; i < indice; i++) {
            System.out.print(vertices[i] + "\t");
            for (int j = 0; j < indice; j++) {
                System.out.print(this.matrizAdy[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    public boolean pertenece(int x) {
        return (this.posicionDeNodo(x) != -1);
    }

}
