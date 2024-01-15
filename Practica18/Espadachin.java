//Espadachin hereda todos los miembros de clase de parte de la clase Soldado asimismo con ayuda del metodo super
public class Espadachin extends Soldado {
    private int longitudEspada;
    private String accion;
    public Espadachin(String nombre, char figura){
        super(nombre, figura, (int)(Math.random() * 3 + 2));
        longitudEspada = (int)(Math.random() * 5 + 1);
        accion = "Crear muro de escudos";
        agregarDefensa();
    }
    public String toString(){
        return "Espadachin " + super.toString();
    }
}
