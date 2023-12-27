import java.util.ArrayList;

public class Mapa {
    private String territorio;
    public char[][] mapa = {{'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'},
                            {'-','-','-','-','-','-','-','-','-','-'}};
    public Mapa(){
        int rand = (int)(Math.random() * 5 + 1);
        switch(rand){
            case 1:
                territorio = "BOSQUE";
            case 2:
                territorio = "CAMPO ABIERTO";
            case 3:
                territorio = "MONTANIA";
            case 4:
                territorio = "DESIERTO";
            case 5:
                territorio = "PLAYA";
            default:
                territorio = " ";
        }
    }
    public void comprobarValores(ArrayList<Ejercito>actual){
        int fila;
        char columna;
        for(int i = 0; i < actual.size(); i++){
            fila = actual.get(i).getPosF();
            columna = actual.get(i).getPosC();
            while(mapa[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            actual.get(i).setposF(fila);
            actual.get(i).setposC(columna);
            mapa[fila - 1][nroColumna(columna)] = actual.get(i).getFigura();
        }
    }
    public void actualizarMapa(ArrayList<Ejercito>actual){
        int posF;
        char posC;
        for(int i = 0; i < actual.size(); i++){
            posF = actual.get(i).getPosF();
            posC = actual.get(i).getPosC();
            mapa[posF - 1][nroColumna(posC)] = actual.get(i).getFigura();
        }
    }                       
    public void imprimirMapa(){
        System.out.println("El mapa se encuentra en " + territorio);
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int nroColumna(char m){
        switch(m){
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
            case 'E': return 4;
            case 'F': return 5;
            case 'G': return 6;
            case 'H': return 7;
            case 'I': return 8;
            case 'J': return 9;
            default: return 0;
        }
    }
    public void vidaAdicional(ArrayList<Ejercito> ejer){
        if(territorio.equals("BOSQUE") && ejer.get(0).getReino().equals("INGLATERRA")){
            agregarVida(ejer);
        } else if(territorio.equals("CAMPO ABIERTO")){
            agregarVida(ejer);
        } else if(territorio.equals("MONTANIA")){
            agregarVida(ejer);
        } else if(territorio.equals("PLAYA")){
            agregarVida(ejer);
        } else if(territorio.equals("DESIERTO")){
            agregarVida(ejer);
        }
    }
    public static void agregarVida(ArrayList<Ejercito> ejer){
        for(int i = 0; i < ejer.size(); i++){
            for(int j = 0; j < ejer.get(i).getSoldados().size(); j++){
                ejer.get(i).getSoldados().get(j).addVida();
            }
        }
    }
    public String getTerritorio(){
        return territorio;
    }
    public static void imprimirEjercitosMapa(ArrayList<Ejercito>actual){
        System.out.println("Ejercito 1: ");
        for(int i = 0; i < actual.size(); i++){
            System.out.println("Ejercito 1." + (i + 1) + " " + actual.get(i).cantidadVida() + " Soldados: " + actual.get(i).getSoldados());
        }
    }
}
