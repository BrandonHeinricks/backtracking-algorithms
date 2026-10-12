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

    public static boolean generarPrimera(List<String> camino, char[] letras, int longitudObjetivo) {
        if (camino.size() == longitudObjetivo) {
            System.out.println("Primera solución encontrada: " + camino);
            return true;
        }
        for (char letra : letras) {
            camino.add(String.valueOf(letra));
            if (generarPrimera(camino, letras, longitudObjetivo)) {
                return true;
            }
            camino.remove(camino.size() - 1);
        }
        return false;
    }

    public static boolean generarPrimeraConCondicion(List<String> camino, char[] letras, int longitudObjetivo) {
        if (camino.size() == longitudObjetivo) {
            System.out.println("Primera solución con condicion encontrada: " + camino);
            return true;
        }
        for (char letra : letras) {
            String c = String.valueOf(letra);
            if(!camino.contains(c)){
                camino.add(c);
                if (generarPrimeraConCondicion(camino, letras, longitudObjetivo)) {
                    return true;
                }
                camino.remove(camino.size() - 1);
            }
        }
        return false;
    }



    public static List<List<Integer>> nQueens (int n){
        List<List<Integer>> resultado = new ArrayList<>();
        nQueensAux(n,0,new ArrayList<Integer>(),resultado);
        return resultado;
    }
    public static void nQueensAux(int n, int fila,List<Integer> estado, List<List<Integer>> resultado){
        if(fila == n){
            resultado.add(new ArrayList<>(estado));
        }else{
            for(int columnas = 0; columnas < n; columnas++){
                estado.add(columnas);
                if(esValido(estado)){
                    nQueensAux(n,fila+1, estado,resultado);
                }
                estado.remove(estado.size()-1);
            }
        }
    }

    public static boolean esValido(List<Integer> estado){
        //Logica para validar si se permite la reina nueva, no implementada.
        return true;
    }
}
