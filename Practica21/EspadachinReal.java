import java.util.*;
public class EspadachinReal extends Soldado{
    private int cantCuchillos;
    private int longitudCuchillos;
    private boolean lanzarCuchillos;
    private int evolucion = 0;
    public EspadachinReal(String nombre, char figura){
        super(nombre, figura, 12);
        cantCuchillos = (int)(Math.random() * 5 + 1);
        longitudCuchillos = (int)(Math.random() * 5 +1);
        lanzarCuchillos = true;
    }
    public void accionLanzarCuchillos(){
        if(cantCuchillos == 0){
            System.out.println("Ya no quedan cuchillos");
        } else {
            cantCuchillos--;
        }
    }
    public void evolucionarSoldado(){
        if(evolucion <= 4){
            if(cantCuchillos < 5){
                cantCuchillos++;
            } else if(longitudCuchillos < 5){
                longitudCuchillos++;
            }
        }
        evolucion++;
    }
}
