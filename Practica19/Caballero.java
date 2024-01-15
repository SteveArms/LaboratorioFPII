//Caballero hereda todos los miembros de clase de parte de la clase Soldado asimismo con ayuda del metodo super
public class Caballero extends Soldado{
    private boolean montar;
    private boolean envestir;
    private String arma;
    public Caballero(String nombre, char figura){
        super(nombre, figura, (int)(Math.random() * 3 + 3));
        arma = armaAleatoria();
        montar = estarMontado();
        envestir = true;
    }
    public static String armaAleatoria(){
        int rand = (int)(Math.random() * 2 + 1);
        if(rand == 1){
            return "Espada";
        } else {
            return "Lanza";
        }
    }
    //Metodos necesarios paras las acciones que realizar el caballero cuando esta montado y cuando
    public static boolean estarMontado(){
        int rand = (int)(Math.random() * 2 + 1);
        return rand == 1;
    } 
    public void montarse(){
        if(!montar && arma.equals("Espada")){
            montar = true;
            arma = "Lanza";
        }
    }  
    public void desmontarse(){
        if(montar && arma.equals("Lanza") && getActitud().equals("Defensiva")){
            montar = false;
            arma = "Espada";
        }
    }
    public void atacar(){
        if(montar){
            for(int i = 0; i < 3; i++){
                atacar();
            }
        } else {
            for(int i = 0; i < 2; i++){
                atacar();
            }
        }
    }
    public String toString(){
        return "Caballero " + super.toString();
    }
}
