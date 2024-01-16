public class EspadachinTeutocnico extends Soldado{
    private int numeroJabalinas;
    private int longitudJabalina;
    private int evolucion = 1;
    public EspadachinTeutocnico(String nombre, char figura){
        super(nombre, figura, 14);
        numeroJabalinas = (int)(Math.random() * 5 + 1);
        longitudJabalina = (int)(Math.random() * 5 + 1);
    }
    public void lanzar(){
        if(numeroJabalinas == 0){
            System.out.println("No es posible que el espadachin lance jabalinas");
        } else {
            System.out.println("El Espadachin lanzo una jabalina");
            numeroJabalinas--;
        }
    }
    public void evolucionarSoldado(){
        if(evolucion <= 4){
            if(numeroJabalinas < 5){
                numeroJabalinas++;
            } else if(longitudJabalina < 5){
                longitudJabalina++;
            }
        }
        evolucion++;
    }
}
