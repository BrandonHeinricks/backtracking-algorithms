package uni;

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
                if(sumaEsado + n > target){continue;}
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
        if(objetivo == 0 && (estado.length % 2) == 0){
            solucion.setValor(true);
        }else if(!solucion.getValor()){
            for(int i = start; i < valores.length && !solucion.getValor(); i++){
                int elemento = array[i];
                if(elemento > objetivo){continue;}
                estado.add(elemento);
                esValidaSecuenciaHelper(valores, objetivo-elemento, i+2,estado, solucion);
                if(!solucion.getValor()){
                    estado.remove(estado.length-1);
                }
            }
        }
    }
}
