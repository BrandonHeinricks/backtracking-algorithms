package uni;
import java.util.*;

public class OptimunSelection {

    /*

    Un ingeniero debe seleccionar un conjunto de componentes electrónicos para una placa.
    Se dispone de dos vectores de enteros de tamaño n: precios (el coste de cada componente)
    y consumos (los miliamperios que gasta cada uno).
    Se cuenta con un presupuesto estricto de coste máximo presupuestoMax y se exige que la placa
    alcance un rendimiento mínimo obligatorio de miliamperios llamado consumoMinimo.

    Queremos encontrar el menor coste total posible (precio mínimo) que cumpla con alcanzar al menos el
    consumoMinimo sin pasarnos del presupuestoMax. Si ninguna combinación cumple los requisitos,
    el algoritmo debe retornar un valor indicador (como por ejemplo un valor infinito o -1).
     */

    public static int calcularMinimoPrecio(int[] precios, int[] consumos, int presupuestoMax, int consumoMinimo){
        int[] mejorPrecio = new int[]{Integer.MAX_VALUE};
        calcularMinimoPrecioHelper(precios, consumos, presupuestoMax, consumoMinimo,0,mejorPrecio,0,0);
        return mejorPrecio[0] == Integer.MAX_VALUE ? -1 : mejorPrecio[0];
    }
    public static void calcularMinimoPrecioHelper(int[] precios, int[] consumos, int presupuestoMax, int consumoMinimo,int start,int[] mejorPrecio, int sumaActual,int consumoActual){
        if(consumoActual >= consumoMinimo && sumaActual < mejorPrecio[0]){
            mejorPrecio[0] = sumaActual;
        }
        for(int i = start; i < precios.length; i++){
            int precio = precios[i];
            int consumo = consumos[i];
            if(precio > presupuestoMax){continue;}
            sumaActual += precio;
            consumoActual += consumo;
            calcularMinimoPrecioHelper(precios, consumos, presupuestoMax - sumaActual,consumoMinimo,i+1,mejorPrecio,sumaActual,consumoActual);
            sumaActual -= precio;
            consumoActual -= consumo;
        }
    }



    /*

    Problema: El Menor Coste de Despliegue

     Una empresa de software debe desplegar un conjunto de microservicios. Se dispone de dos vectores
     de enteros de tamaño n: costes (el precio en euros de desplegar cada microservicio) y memoria
     (los gigabytes de RAM que consume cada uno).

    Se cuenta con un presupuesto estricto de dinero máximo disponible (presupuestoMax) y se exige que
    la suma total de memoria RAM de los servicios seleccionados alcance obligatoriamente un rendimiento
    mínimo (memoriaMinima).

    Implementar un algoritmo basado en el esquema de Selección Óptima (minimización) que determine cuál
     es el menor coste total necesario para realizar el despliegue cumpliendo ambas restricciones.
     Si no es posible encontrar ninguna combinación válida, el método debe retornar -1.
     */

    public int calcularMenorCoste(int[] costes, int[] memoria, int presupuestoMax, int memoriaMinima){
        int[] mejorCoste = new int[]{Integer.MAX_VALUE};
        calcularMenorCosteHelper(costes,memoria,presupuestoMax,memoriaMinima,0,0,0,mejorCoste);
        return mejorCoste[0] == Integer.MAX_VALUE ? -1 : mejorCoste[0];
    }
    public static void calcularMenorCosteHelper(int[] costes, int[] memorias, int presupuestoMax, int memoriaMinima,int start,int costeActual,int memoriaActual,int[] mejorCoste){
        if(costeActual < mejorCoste[0] && memoriaActual >= memoriaMinima){
            mejorCoste[0] = costeActual;
        }
        for(int i = start; i < costes.length; i++){
            int coste = costes[i];
            int memoria = memorias[i];
            if(coste > presupuestoMax || costeActual + coste >= mejorCoste[0]){continue;}
            costeActual += coste;
            memoriaActual += memoria;
            calcularMenorCosteHelper(costes, memorias, presupuestoMax-coste, memoriaMinima, i+1, costeActual, memoriaActual, mejorCoste);
            costeActual -= coste;
            memoriaActual -= memoria;
        }
    }




    /*
    EXAMEN 27 OCTUBRE 2025
    Dado un conjunto de ejercicios gimnásticos de los que
    disponemos del tiempo necesario para realizarlos (medido en minutos) y de las unidades
    de esfuerzo que consume su realización, queremos buscar el subconjunto de ejercicios
    que puedan realizarse en un tiempo total exacto de T (minutos) que minimicen las
    unidades totales de esfuerzo consumidas al realizarlos.


     */

    public static boolean[] minEsfuerzoEnT(int[] tiempo, int[] esfuerzo, int T){
        int n = tiempo.length;
        boolean[] solucion = new boolean[n];
        int[] mejorEsfuerzo = new int[]{Integer.MAX_VALUE};
        minEsfuerzoEnTHelper(tiempo,esfuerzo,T,0,0,0,mejorEsfuerzo,new boolean[n],solucion);
        return solucion;
    }
    public static void minEsfuerzoEnTHelper(int[] tiempo, int[] esfuerzo, int T, int start, int tiempoActual, int esfuerzoActual,int[] mejorEsfuerzo,boolean[] estado, boolean[] solucion){
        if(tiempoActual == T){
            if( esfuerzoActual < mejorEsfuerzo[0]) {
                mejorEsfuerzo[0] = esfuerzoActual;
                for (int i = 0; i < estado.length; i++) {
                    solucion[i] = estado[i];
                }
            }return;
        }
        for(int i = start; i < tiempo.length; i++){
            int t = tiempo[i];
            int e = esfuerzo[i];
            if(tiempoActual + t > T || esfuerzoActual + e > mejorEsfuerzo[0]){continue;}
            tiempoActual += t;
            esfuerzoActual += e;
            estado[i] = true;
            minEsfuerzoEnTHelper(tiempo,esfuerzo,T,i+1,tiempoActual,esfuerzoActual,mejorEsfuerzo,estado,solucion);
            tiempoActual -= t;
            esfuerzoActual -= e;
            estado[i] = false;
        }
    }

    /*
    8-NOVIEMBRE-2019
    Se dispone de una lista de trabajos que tienen asociado un instante de
    comienzo ci y un instante de finalización fi, donde ci<fi, de forma que cada trabajo
    debe realizarse durante el periodo [ci,fi). Durante la realización de un trabajo es
    necesario hacer uso de un recurso único de manera exclusiva, por lo que dos
    trabajos i, j son compatibles si los intervalos [ci,fi) y [cj,fj) no se superponen. Se
    quiere obtener el conjunto de trabajos mutuamente compatibles que maximice el
    tiempo que está en uso el recurso.

    Comienzo: [5, 2, 4, 2, 6, 0, 6, 2]
    Fin: [9, 4, 5, 5, 7, 3, 8, 5]

    Tiempo:    0 1 2 3 4 5 6 7 8 9
    -----------------------------------------
    Trabajo 0:           [=======]     (5 a 9)
    Trabajo 1:    [====]               (2 a 4)
    Trabajo 2:        [===]            (4 a 5)
    Trabajo 3:    [=======]            (2 a 5)
    Trabajo 4:            [===]        (6 a 7)
    Trabajo 5:[=====]                  (0 a 3)
    Trabajo 6:            [===]        (6 a 8)
    Trabajo 7:    [===]                (2 a 5)

    SOLUCION: Los trabajos compatibles que maximizan el uso del recurso son el 0, el 2 y el 5, que
    en total hacen uso del recurso durante 8 unidades de tiempo.
     */

    public static boolean[] maxUsoRecurso(int[] comienzo, int[] fin){
        int n = comienzo.length;
        int[] maxTiempo = new int[]{Integer.MIN_VALUE};
        boolean[] solucion = new boolean[n];
        boolean[] estado = new boolean[n];
        maxUsoRecursoHelper(comienzo, fin, 0, 0, maxTiempo, estado, solucion);
        return solucion;
    }
    public static void maxUsoRecursoHelper(int[] comienzos, int[] fin, int start,int tiempoActual, int[] maxTiempo, boolean[] estado, boolean[] solucion){
        if(tiempoActual > maxTiempo[0]){
            maxTiempo[0] = tiempoActual;
            for(int i = 0; i < estado.length; i++){
               solucion[i] = estado[i];
           }
        }
        for(int i = start; i < comienzos.length; i++){
            int c = comienzos[i];
            int f = fin[i];
            if(!esValido(comienzos, fin, estado,i)){continue;}
            estado[i] = true;
            tiempoActual += (f-c);
            maxUsoRecursoHelper(comienzos, fin, i+1, tiempoActual, maxTiempo, estado, solucion);
            tiempoActual -= (f-c);
            estado[i] = false;
        }
    }
    public static boolean esValido(int[] comienzos, int[] fines, boolean[] usados, int nuevo){
        boolean sol = true;
        for(int i = 0; i < comienzos.length; i++){
            if(usados[i] == true){
                if(comienzos[i] < fines[nuevo] && fines[i] > comienzos[nuevo]){
                    sol = false;
                }
            }
        }return sol;
    }





    /*
    26-NOVIEMBRE-2021

    Dado un array de números enteros, se pide obtener el subconjunto de valores
    del array con mayor número de elementos cuya suma sea igual a 0.

    Valores: [-4, 3, 5, -5, 0, 1, 8, 10, -21]
    En este ejemplo, el subconjunto de valores del array con mayor número de elementos
    cuya suma es igual a 0 es el formado por {3, 5, -5, 0, 8, 10, -21} (tiene 7 elementos).
     */

    public static boolean[] subSuma0MaxElem(int[] array){
        int n = array.length;
        int[] maxElem = new int[]{Integer.MIN_VALUE};
        boolean[] solucion = new boolean[n];
        boolean[] estado = new boolean[n];
        subSuma0MaxElemHelper( array, 0,0,0, maxElem, estado, solucion);
        return solucion;
    }
    public static void subSuma0MaxElemHelper(int[] array, int start, int sumaActual,int elemActual, int[] maxElem, boolean[] estado, boolean[] solucion){
        if(elemActual > maxElem[0] && sumaActual == 0){
            maxElem[0] = elemActual;
            for(int i = 0; i < array.length; i++){
                solucion[i] = estado[i];
            }
        }
        for(int i = start; i < array.length; i++){
            int elemento = array[i];
            sumaActual += elemento;
            estado[i] = true;
            elemActual ++;
            subSuma0MaxElemHelper(array, i+1, sumaActual, elemActual, maxElem, estado, solucion);
            sumaActual -= elemento;
            estado[i] = false;
            elemActual --;
        }
    }



    /*
    11-NOVIEMBRE-2022

    Problema. Se dispone de dos contenedores, cada uno capaz de soportar un peso máximo,
    y un conjunto de productos, cada uno con un peso característico. Se pretende distribuir
    todos los productos entre los dos contenedores de forma que el reparto del peso sea lo
    más equilibrado posible. Ejemplo: dado un peso máximo de 10 para cada contenedor y
    el siguiente array de pesos de los productos:

    Valores: [1, 3, 2, 5, 1, 4, 2, 1]

    El reparto más equilibrado distribuye los objetos {0,1,2,4,6,7} en el contenedor 1 (con un
    peso total de 10) y {3,5} en el contenedor 2 (con un peso total de 9).
     */
    public static int[] distribucionCarga2(int[] pesos, int pMax){
        int[] solucion = new int[2];
        int[] estado = new int[2];
        Entero mejorDistribucion = new Entero(Integer.MIN_VALUE);
        distribucionCarga2Helper(pesos, pMax, 0, mejorDistribucion, estado, solucion);
        return solucion;
    }
    public static void distribucionCarga2Helper(int[] pesos, int pMax, int start, Entero mejorDistribucion, int[] estado, int[] solucion){
        if(start == pesos.length){
            int distribucionActual = Math.abs(estado[0] - estado[1]);
            if(distribucionActual < mejorDistribucion.getValor()){
                mejorDistribucion.setValor(distribucionActual);
                for(int i = 0; i< pesos.length; i++){
                    solucion[i] = estado[i];
                }
            }
        }else{
            int elemento = pesos[start];
            for(int i = 0; i < estado.length; i++){
                if(estado[i] + elemento <= pMax){
                    estado[i] += elemento;
                    distribucionCarga2Helper(pesos, pMax, start+1, mejorDistribucion, estado, solucion);
                    estado[i] -= elemento;
                }
            }
        }
    }






}
