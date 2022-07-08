import java.util.*;

public class ImplementEstaticaDirigido implements GrafosTDA{
    int indice;
    int[][] matrizAdy;
    int dim;
    int[] etiquetas;
    //opcional
    List<Integer> camino = new ArrayList<>();

    public void inicializarGrafo(int dim) {
        this.dim = dim;
        indice = 0;
        matrizAdy = new int[dim][dim];
        etiquetas = new int[dim];
    }

    public void agregarVertice(int v) {
        if (indice < dim) {
            etiquetas[indice] = v;
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
            if (etiquetas[i] == v) {
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
        int vEncontrados = 1;
        vVisitados[0] = etiquetas[0]; //arranca del primero


        for (int i = 0; i < indice - 1; i++) {
            for (int j = 0; j < vEncontrados; j++) {
                for (int k = 0; k < indice; k++) {
                    int aux = this.posicionDeNodo(vVisitados[j]);
                    if (aux != -1) {
                        if (matrizAdy[aux][k] > 0) {
                            if (pesoMenor <= 0 && !this.contiene(vVisitados, etiquetas[k])) {
                                pesoMenor = matrizAdy[aux][k]; //toma el peso de esa posicion de la matriz
                                posPesoMenorJ = j;
                                posPesoMenorK = k;
                            } else if (pesoMenor > matrizAdy[aux][k]
                                    && !this.contiene(vVisitados, etiquetas[k])) {

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
            vVisitados[vEncontrados] = etiquetas[posPesoMenorK];
            vEncontrados++;
            pesoMenor = 0;

        }
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
        int[] distancia = new int[dim];
        int[] padre = new int[dim];
        boolean[] visto = new boolean[dim];

        //Inicialización
        for (int i = 0; i < dim; i++) {
            distancia[i] = Integer.MAX_VALUE;
            padre[i] = -1;
            visto[i] = false;
        }

        distancia[inicio] = 0; //porq mi origen es 0
        //esto es una cola?
        PriorityQueue<Integer> pila = new PriorityQueue<>();
        pila.add(distancia[inicio]);

        while (!pila.isEmpty()) {
            int u = pila.poll(); //saca el primer elemento de la fila y lo asigna a u
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


        /*int mayor =Arrays.stream(distancia).max().getAsInt() - Integer.MAX_VALUE;

        //System.out.println("soy el costo" + costoDelCaminoMinimo());

        System.out.println("soy el camino"  + this.camino);*/

        return distancia;


    }

    public void imprimirCamino(int[] padre, int inicio, int destino) {
        if (destino == 0) {
            System.out.println(destino + 1);
            camino.add(destino+1);
        } else {
            imprimirCamino(padre, inicio, padre[destino]);
            System.out.println(destino + 1);
            camino.add(destino+1);
        }
    }

    /*public int costoDelCaminoMinimo(){

        int acumulador = 0;
        for (int i = 0; i < camino.size() - 1; i++) {
            acumulador += matrizAdy[camino.get(i)][camino.get(i)+1];

        }
        return acumulador;
    }*/

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
                aislados[i] = etiquetas[i];
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
                System.out.print(etiquetas[i] + "\t");
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
            etiquetas[this.posicionDeNodo(v)] = etiquetas[indice - 1];
            indice--;
        } else {
            System.out.println("No se encontro el vertice");
        }
    }
    public int[] vertices() {
        int[] aux = new int[indice];
        for (int i = 0; i < indice; i++) {
            aux[i] = etiquetas[i];
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
            System.out.print(etiquetas[i] + "\t");
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
