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


}
