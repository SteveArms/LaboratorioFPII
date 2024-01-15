import java.util.*;
//Arquero hereda todos los miembros de clase de parte de la clase Soldado asimismo con ayuda del metodo super
public class Arquero extends Soldado{
    private int cant_flechas;
    public Arquero(String nombre, char figura){
        super(nombre, figura, (int)(Math.random() * 1 + 3));
        cant_flechas = (int)(Math.random() * 5 + 1);
        setAtaque(7);
        setDefensa(3);
        setNivelVida((int)(Math.random() * 3 + 3));
    }
    public void disparo(){
        if(cant_flechas > 0)
            cant_flechas--;
    }
    public String toString(){
        return "Arquero " + super.toString();
    }
}
