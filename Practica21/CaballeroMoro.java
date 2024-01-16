public class CaballeroMoro extends Soldado{
    private int cantidadFlechas;
    private int longitudFlechas;
    private int evolucion = 1;
    public CaballeroMoro(String nombre, char figura){
        super(nombre, figura, 13);
        cantidadFlechas = (int)(Math.random() * 5 + 1);
        longitudFlechas = (int)(Math.random() * 5 + 1);
    } 
    public void evolucionarSoldado(){
        if(evolucion <= 4){
            if(cantidadFlechas < 5){
                cantidadFlechas++;
            } else if(longitudFlechas < 5){
                longitudFlechas++;
            }
        }
        evolucion++;
    }
}
