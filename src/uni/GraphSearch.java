package uni;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class GraphSearch {

    /*
    21-ENERO-2025

    dada una matriz NxM de números enteros positivos, se quiere encontrar
    el recorrido de menor valor acumulado desde la celda inicial (0,0) hasta la celda final (N-1,M-1),
    pudiendo únicamente moverse a celdas hacia abajo, hacia la derecha o hacia la diagonal inferior
    derecha.

     */

    public static int recorridoOptimo(int[][] matriz){
        Entero solucion = new Entero(Integer.MAX_VALUE);
        int[][] movimientos = {{1,0},{0,1},{1,1}};
        int[] estado = {0,0};
        int sumaInicial = matriz[0][0];
        recorridoOptimoHelper(matriz, estado, movimientos, sumaInicial, solucion);
        return solucion.getValor();
    }
    public static void recorridoOptimoHelper(int[][] matriz, int[] estado, int[][] movimientos, int sumaActual, Entero solucion ){
        int n = matriz.length;
        int m = matriz[0].length;
        if(estado[0] == n-1 && estado[1] == m-1){
            if(sumaActual < solucion.getValor()){
                solucion.setValor(sumaActual);
            }
        }else{
            for(int i = 0; i < movimientos.length; i++){
                int[] mov = movimientos[i];
                int sigFila = estado[0] + mov[0];
                int sigCol  = estado[1] + mov[1];
                if(sigFila < n && sigCol < m){
                    int valorCasilla = matriz[sigFila][sigCol];
                    if(sumaActual < solucion.getValor()){
                        estado[0] += mov[0];
                        estado[1] += mov[1];
                        sumaActual += valorCasilla;
                        recorridoOptimoHelper(matriz, estado, movimientos, sumaActual, solucion);
                        estado[0] -= mov[0];
                        estado[1] -= mov[1];
                        sumaActual -= valorCasilla;
                    }
                }
            }
        }
    }
}
