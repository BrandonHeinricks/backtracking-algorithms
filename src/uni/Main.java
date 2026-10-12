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
    }

}
