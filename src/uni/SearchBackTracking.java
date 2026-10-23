package uni;
import java.util.*;

public class SearchBackTracking {

    public boolean existeSubconjunto(int[] array, int target) {
        Booleano solution = new Booleano(false);
        existeSubconjuntoHelper(array, target,0,0,solution);
        return solution.getValor();
    }
    public void existeSubconjuntoHelper(int[] array, int target, int start, int sumaEstado, Booleano solution){
        if(sumaEstado == target){
            solution.setValor(true);
        }else if(!solution.getValor() ){
            for(int i = start; i < array.length; i++){
                int n = array[i];
                if(sumaEstado + n > target){continue;}
                sumaEstado += n;
                existeSubconjuntoHelper(array,target,i+1,sumaEstado,solution);
                sumaEstado -= n;
            }
        }
    }

    /*
    Problema: La Secuencia Alterna de Encriptación

    Se dispone de un vector de números enteros positivos valores de tamaño n y un número entero positivo objetivo.
    Se desea encontrar un subconjunto de elementos del vector tal que se cumplan las siguientes condiciones:
    La suma total de los elementos elegidos debe ser exactamente igual a objetivo.
    Restricción de alternancia: No se pueden seleccionar dos números consecutivos del vector original de forma seguida
    (es decir, si eliges el elemento en el índice i, no puedes elegir el elemento en el índice i + 1 en la misma solución).
    Restricción de paridad: El número total de elementos que formen parte del subconjunto solución debe ser un número par de elementos.
     */

    public boolean esValidaSecuencia(int[] valores, int objetivo){
        Booleano solucion = new Booleano(false);
        esValidaSecuenciaHelper(valores, objetivo, 0, new ArrayList<>(), solucion);
        return solucion.getValor();
    }
    public void esValidaSecuenciaHelper(int[] valores, int objetivo, int inicio, List<Integer> estado, Booleano solucion){
        if(objetivo == 0 && (estado.size() % 2) == 0){
            solucion.setValor(true);
        }else if(!solucion.getValor()){
            for(int i = inicio; i < valores.length && !solucion.getValor(); i++){
                int elemento = valores[i];
                if(elemento > objetivo){continue;}
                estado.add(elemento);
                esValidaSecuenciaHelper(valores, objetivo-elemento, i+2,estado, solucion);
                if(!solucion.getValor()){
                    estado.remove(estado.size()-1);
                }
            }
        }
    }

    /*
    5-DICIEMBRE-2020
    Problema. Dado un vector, v, que contiene los valores de un conjunto de elementos, se
    desea comprobar si existen dos subconjuntos de elementos tal que la suma de valores de
    cada subconjunto sea exactamente igual a un valor predeterminado vObjetivo
     */
    public static boolean dosSubconjuntos(int[] v, int objetivo){
        Booleano solucion = new Booleano(false);
        dosSubconjuntosHelper(v,objetivo,0,0,0,solucion);
        return solucion.getValor();
    }
    public static void dosSubconjuntosHelper(int[] v,int objetivo, int start, int suma1, int suma2, Booleano solucion){
        if(suma1 == objetivo && suma2 == objetivo){
            solucion.setValor(true);
        }else{
            for(int i = start; i < v.length && !solucion.getValor(); i++){
                int elemento = v[i];
                if(suma1 + elemento <= objetivo){
                    suma1 += elemento;
                    dosSubconjuntosHelper(v, objetivo, i+1,suma1, suma2, solucion);
                    suma1 -= elemento;
                }
                if(!solucion.getValor() && suma2 + elemento <= objetivo){
                    suma2 += elemento;
                    dosSubconjuntosHelper(v, objetivo, i+1,suma1, suma2, solucion);
                    suma2 -= elemento;
                }
            }
        }
    }

    /*
    28-OCTUBRE-2024
    Disponemos de una cantidad concreta de varios tipos de
    tuberías, cada tipo de una longitud exacta. Queremos comprobar si existe una
    combinación de estas tuberías que ensambladas consigan una longitud total Ltotal.
    Se pide: implementar un algoritmo en Java con la siguiente cabecera, basado en el
    esquema de backtracking1,2, que permita determinar si existe una combinación de
    tuberías cuyo ensamblado consiga una longitud Ltotal
     */
    public static boolean hayEnsamblaje(int[] longitud, int[] cantidad, int LTotal){
        Booleano solucion = new Booleano(false);
        hayEnsamblajeHelper(longitud, cantidad, LTotal, 0,0, solucion);
        return solucion.getValor();

    }
    public static void hayEnsamblajeHelper(int[] longitud, int[] cantidad, int LTotal, int start, int longitudActual, Booleano solucion){
        if(longitudActual == LTotal){
            solucion.setValor(true);
        }else if(!solucion.getValor() && start < longitud.length){
            int cantidadDisponible = cantidad[start];

            for(int k = 0; k < cantidadDisponible && !solucion.getValor(); k++){
                int l = longitud[start] * k;
                if(longitudActual + l <= LTotal){
                    hayEnsamblajeHelper(longitud, cantidad, LTotal, start+1, longitudActual, solucion);
                }
            }
        }
    }


    /*
    28-JUNIO-2018
    Se pide:
    Implementar un algoritmo en Java, basado en el esquema de backtracking, que
    permita determinar si existe un reparto equitativo de los bienes entre los tres
    herederos. Deberá implementarse el código de la función hayRepartoEquitativo,
    así como todos los métodos adicionales que se consideren necesarios.
     */
    public static boolean hayRepartoEquitativo(int[] bienes){
        int[] estado = new Array[3];
        Booleano solucion = new Booleano(false);
        hayRepartoEquitativoHelper(bienes,0,estado,solucion);
        return solucion.getValor();
    }
    public static void hayRepartoEquitativoHelper(int[] bienes, int start, int[] estado, Booleano solucion){
        if(start == bienes.length){
            if(esEquitativo(estado)){
                solucion.setValor(true);
        }
        }else if(!solucion.getValor()){
            int elemento = bienes[start];
            int k = 0;
            while(k < estado.length && !solucion.getValor()){
                estado[k] += elemento;
                hayRepartoEquitativoHelper(bienes, start+1, estado,solucion);
                estado[k] -= elemento;
                k++;
            }
        }
    }

    public static boolean esEquitativo(int[] lista){
        boolean sol = true;
        int k = lista.length;
        while(k > 0){
            if(lista[k] != lista[k-1]){
                sol = false;
            }
            k--;
        }
        return sol;
    }
}
