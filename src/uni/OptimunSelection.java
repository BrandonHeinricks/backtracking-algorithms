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
}
