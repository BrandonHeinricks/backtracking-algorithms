package uni;
import java.util.*;
public class Documents {

    /*
        Ejemplo muy basico de como corre un algortimo de backtracking, en este caso tira todas las soluciones posibles,
        el criterio de si algo es solucion es solo q se llegue a tamaño 2.
     */
    public static void generar(List<String> camino, char[] letras, int longitudObjetivo) {
        if (camino.size() == longitudObjetivo) {
            System.out.println("Solución encontrada: " + camino);
            return;
        }
        for (char letra : letras) {
            camino.add(String.valueOf(letra));
            generar(camino, letras, longitudObjetivo);
            camino.remove(camino.size() - 1);
        }
    }
    public static void main(String[] args) {
        System.out.println("******EJEMPLO-BASICO-TODAS-LAS-SOLUCIONES******");
        char[] opciones = {'A', 'B', 'C', 'D'};
        generar(new ArrayList<>(), opciones, 3);
        System.out.println("************************************************");
    }
}
