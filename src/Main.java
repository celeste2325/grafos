public class Main {
    public static ImplemEstaticaNoDirigido grafoNoDirigido = new ImplemEstaticaNoDirigido();
    public static ImplementEstaticaDirigido grafoDirigido = new ImplementEstaticaDirigido();


    public static void main(String[] args) {
        grafoNoDirigido.inicializarGrafo(6);



        grafoNoDirigido.agregarVertice(1);
        grafoNoDirigido.agregarVertice(2);
        grafoNoDirigido.agregarVertice(3);
        grafoNoDirigido.agregarVertice(4);
        grafoNoDirigido.agregarVertice(5);
        grafoNoDirigido.agregarVertice(6);

        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(1, 2, 6);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(1, 3, 1);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(1, 4, 5);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(2, 3, 5);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(2, 5, 3);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(3, 4, 5);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(3, 5, 6);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(3, 6, 4);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(4, 6, 2);
        grafoNoDirigido.agregarAristaParaGrafoNoDirigido(5, 6, 6);
        grafoNoDirigido.grafoPrim();

        grafoDirigido.inicializarGrafo(6);
        grafoDirigido.agregarVertice(1);
        grafoDirigido.agregarVertice(2);
        grafoDirigido.agregarVertice(3);
        grafoDirigido.agregarVertice(4);
        grafoDirigido.agregarVertice(5);
        grafoDirigido.agregarVertice(6);
        grafoDirigido.agregarAristaParaGrafoDirigido(1,6,5);
        grafoDirigido.agregarAristaParaGrafoDirigido(1,3,40);
        grafoDirigido.agregarAristaParaGrafoDirigido(1,5,10);
        grafoDirigido.agregarAristaParaGrafoDirigido(2,4,5);
        grafoDirigido.agregarAristaParaGrafoDirigido(3,2,10);
        grafoDirigido.agregarAristaParaGrafoDirigido(3,5,5);
        grafoDirigido.agregarAristaParaGrafoDirigido(4,3,5);
        grafoDirigido.agregarAristaParaGrafoDirigido(5,4,20);
        grafoDirigido.agregarAristaParaGrafoDirigido(6,5,10);
        grafoDirigido.agregarAristaParaGrafoDirigido(6,2,20);

        System.out.println("----------------------------");

        System.out.println("camino dijkstra");
        grafoDirigido.dijkstra(1,3);

    }
}
