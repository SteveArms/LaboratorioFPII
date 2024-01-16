import java.util.*;
public class CaballeroFranco extends Soldado{
    private int cantidadLanzas;
    private int longitudLanza;
    private int evolucion = 1;
    public CaballeroFranco(String nombre, char figura){
        super(nombre, figura, 15);
        cantidadLanzas = (int)(Math.random() * 5 + 1);
        longitudLanza = (int)(Math.random() * 5 + 1);
    }
    public void evolucionarSoldado(){
        if(evolucion <= 4){
            if(cantidadLanzas < 5){
                cantidadLanzas++;
            } else if(longitudLanza < 5){
                longitudLanza++;
            }
        }
        evolucion++;
    }
}
