package uni;

import java.util.ArrayList;
import java.util.List;

import static uni.Documents.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("******EJEMPLO-BASICO-TODAS-LAS-SOLUCIONES******");
        char[] opciones = {'A', 'B', 'C', 'D'};
        generar(new ArrayList<>(), opciones, 3);

        System.out.println("\n\n\n******EJEMPLO-BASICO-UNICA-SOLUCION******");
        List<String> camino = new ArrayList<>();
        generarPrimera(camino, opciones, 3);

        System.out.println("\n\n\n******EJEMPLO-BASICO-UNICA-SOLUCION-CONDICION******");
        generarPrimeraConCondicion(new ArrayList<>(), opciones, 3);



        System.out.println("\n\n\n******EJEMPLO-ENCONTRAR-LA-SUMA-EN-ARRAY******");
        int[] candidatos = {2, 3, 5};
        int target = 8;

        System.out.println("Array de entrada: [2, 3, 5]");
        System.out.println("Target buscado: " + target);
        System.out.println("----------------------------------------");

        //UNICA SOUCION(PRIMERA ENCONTRADA)
        List<Integer> primeraSolucion = buscarCombinacion(candidatos, target);
        System.out.println("Primera solucion encontrada: " + primeraSolucion);
        //TODAS LAS SOLUCIONES PSOIBLES
        List<List<Integer>> todasLasSoluciones = buscarCombinaciones(candidatos, target);
        System.out.println("Todas las soluciones: " + todasLasSoluciones);


        System.out.println("\n\n\n******EJEMPLO-RELLENAR-TABLERO-REINAS******");
        int n = 4;
        List<List<Integer>> soluciones = nQueens(n);
        System.out.println(soluciones);
    }

}
