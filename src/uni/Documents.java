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

    private static boolean esValido(List<Integer> estado) {
        int filaActual = estado.size() - 1;
        int colActual = estado.get(filaActual);
        for (int filaPrevia = 0; filaPrevia < filaActual; filaPrevia++) {
            int colPrevia = estado.get(filaPrevia);
            if (colActual == colPrevia) {
                return false;
            }
            if (Math.abs(filaActual - filaPrevia) == Math.abs(colActual - colPrevia)) {
                return false;
            }
        }
        return true;
    }

    /*
     bucarCombinaciones dentro de una lista que generen sumando el valor numerico de un target(valor de entrada)
     */

    public static List<Integer> buscarCombinacion(int[] array, int target){
        List<Integer> state = new ArrayList<>();
         if(buscarCombinacionAux(array, target, 0, state)){
             return state;
         }
         return new ArrayList<>();
    }
    private static boolean buscarCombinacionAux(int[] array, int objetivoRestante, int inicio, List<Integer> estado){
        if(objetivoRestante == 0){
            return true;
        }else{
            for(int i = inicio; i<array.length; i++){
                int num = array[i];
                if(num > objetivoRestante){
                    continue;
                }
                estado.add(num);
                if(buscarCombinacionAux(array, objetivoRestante-num,i,estado)){
                    return true;
                }
                estado.remove(estado.size()-1);
            }
            return false;
        }
    }




    public static List<List<Integer>> buscarCombinaciones(int[] array, int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        buscarCombinacionesAux(array, target, 0,state, result);
        return result;
    }

    public static void buscarCombinacionesAux(int[] array, int target, int start, List<Integer> state, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(state));
            return;
        }else{
            for(int i = start; i< array.length; i++){
                if(array[i] > target){
                    continue;
                }else{
                    state.add(array[i]);
                    buscarCombinacionesAux(array,target-array[i], i,state,result);
                    state.remove(state.size()-1);
                }
            }
        }
    }
}
