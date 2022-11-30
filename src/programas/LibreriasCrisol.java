package programas;

import java.util.Arrays;

public class LibreriasCrisol {
    public static void main(String[] args) {
        //String idCliente[] = {"c001","c002","c003","c004","c005","c006","c007","c008","c009","c010"};
        int mesesUltCompra[] = {1,2,1,1,2,3,3,5,6,7}; //meses transcurridos desde su última compra
        int frecuenciaCompra[] = {1,3,2,4,5,3,2,6,8,5}; //clientes compra cada X meses
        double montoCompraAcum[] = {100.57,250.34,57.31,135.55,300.78,86.44,462.99,705.5,500.5,299.4}; //compra acumulada

        int puntajeR[] = asignarPuntajeRecency(mesesUltCompra);
        System.out.println(Arrays.toString(puntajeR));

        int puntajeF[] = asignarPuntajeFrecuency(frecuenciaCompra);
        System.out.println(Arrays.toString(puntajeF));

        int puntajeM[] = asignarPuntajeMonetary(montoCompraAcum);
        System.out.println(Arrays.toString(puntajeM));

        char segmentos[] = asignarSegmentos(mesesUltCompra,frecuenciaCompra,montoCompraAcum);
        System.out.println(Arrays.toString(segmentos));

        int idCliente = 1; // El id del cliente es el índice del array
        int cantidadProductos = 2; // Cantidad de productos que está comprando en este momento
        double precioSinIGV = 85.56; // Precio de los productos que el cliente está comprando en este momento
        double precioFinal = calcularPrecioFinal(idCliente,cantidadProductos,precioSinIGV,mesesUltCompra,frecuenciaCompra,montoCompraAcum);
        System.out.println("Precio final con IGV es:" + precioFinal);
    }

    public static int[] asignarPuntajeRecency (int[] mesesUltCompra) {
        int puntaje[] = new int[mesesUltCompra.length];

        for (int i = 0;i < mesesUltCompra.length;i++) {
            if (mesesUltCompra[i]==1) {
                puntaje[i] = 5;
            } else if (mesesUltCompra[i]>=2 && mesesUltCompra[i]<=3) {
                puntaje[i] = 4;
            } else if (mesesUltCompra[i]>=4 && mesesUltCompra[i]<=6) {
                puntaje[i] = 3;
            } else if (mesesUltCompra[i]>=7 && mesesUltCompra[i]<=12) {
                puntaje[i] = 2;
            } else if (mesesUltCompra[i]>12) {
                puntaje[i] = 1;
            }
        }
        return puntaje;
    }

    public static int[] asignarPuntajeFrecuency (int[] frecuenciaCompra) {
        int puntaje[] = new int[frecuenciaCompra.length];

        for (int i = 0;i < frecuenciaCompra.length;i++) {
            if (frecuenciaCompra[i]==1) {
                puntaje[i] = 5;
            } else if (frecuenciaCompra[i]==2) {
                puntaje[i] = 4;
            } else if (frecuenciaCompra[i]==3) {
                puntaje[i] = 3;
            } else if (frecuenciaCompra[i]>=4 && frecuenciaCompra[i]<=6) {
                puntaje[i] = 2;
            } else if (frecuenciaCompra[i]>=7) {
                puntaje[i] = 1;
            }
        }
        return puntaje;
    }

    public static int[] asignarPuntajeMonetary (double[] montoCompraAcum) {
        int puntaje[] = new int[montoCompraAcum.length];

        for (int i = 0;i < montoCompraAcum.length;i++) {
            if (montoCompraAcum[i]>300) {
                puntaje[i] = 5;
            } else if (montoCompraAcum[i]>=200 && montoCompraAcum[i]<=300) {
                puntaje[i] = 4;
            } else if (montoCompraAcum[i]>=100 && montoCompraAcum[i]<=199) {
                puntaje[i] = 3;
            } else if (montoCompraAcum[i]>=50 && montoCompraAcum[i]<=99) {
                puntaje[i] = 2;
            } else if (montoCompraAcum[i]<50) {
                puntaje[i] = 1;
            }
        }
        return puntaje;
    }

    public static char[] asignarSegmentos (int[] mesesUltCompra, int[] frecuenciaCompra, double[] montoCompraAcum) {
        int puntajeR[] = asignarPuntajeRecency(mesesUltCompra);
        int puntajeF[] = asignarPuntajeFrecuency(frecuenciaCompra);
        int puntajeM[] = asignarPuntajeMonetary(montoCompraAcum);
        char[] segmentos = new char[mesesUltCompra.length];
        String concatenacion;
        int combinacionPuntajes;

        for (int i = 0;i < mesesUltCompra.length;i++) {
            concatenacion = String.valueOf(puntajeR[i]) + String.valueOf(puntajeF[i]) + String.valueOf(puntajeM[i]);
            combinacionPuntajes = Integer.valueOf(concatenacion);

            if (combinacionPuntajes >=500 && combinacionPuntajes <=555) {
                segmentos[i] = 'A';
            } else if (combinacionPuntajes >=400 && combinacionPuntajes <=499) {
                segmentos[i] = 'B';
            } else
                segmentos[i] = 'C';
        }
        return segmentos;
    }

    public static double calcularPrecioFinal (int indexCliente, int cantidadProductos,double precioSinIgv, int[] mesesUltCompra, int[] frecuenciaCompra, double[] montoCompraAcum) {
        char segmentos[] = asignarSegmentos(mesesUltCompra,frecuenciaCompra,montoCompraAcum);
        double descuento = 0.00;

        switch (segmentos[indexCliente]) {
            case 'A': descuento = 0.2; break;
            case 'B': descuento = 0.1; break;
            case 'C': descuento = 0.05; break;
            default: descuento = 0.00; break;
        }
        double precioFinal = ((cantidadProductos * precioSinIgv) * (1 - descuento)) * 1.18;
        return Math.round(precioFinal*100.0)/100.0;
    }
}