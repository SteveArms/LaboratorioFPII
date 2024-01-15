//Clase Lancero hereda miembros de clase por parte de Soldado
import java.util.*;;
public class Lancero extends Soldado{
    private int longitudLanza;
    private boolean schiltrom;
    public Lancero(String nombre, char fig){
        super(nombre, fig, (int)(Math.random() * 3 + 1));
        longitudLanza = (int)(Math.random() * 5 + 1);
        schiltrom = false;
    }
    public void accion(){
        schiltrom = true;
        agregarDefensa();
    }
    public String toString(){
        return "Lancero " + super.toString();
    }
}
