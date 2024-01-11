import java.util.ArrayList;

public class Mapa {
    public String territorio;
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
    public Mapa(String mapa){
        territorio = mapa;
    }
    public char[][] getMapa(){
        return mapa;
    }
    //El metodo encargado de verificar que las posiciones de los ejercitos no ocupen dos o mas un mismo recuadro
    public void comprobarValores(ArrayList<Ejercito>actual){
        int fila;
        char columna;
        for(int i = 0; i < actual.size(); i++){
            fila = actual.get(i).getFila();
            columna = actual.get(i).getColumna();
            while(mapa[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            actual.get(i).setFila(fila);
            actual.get(i).setCol(columna);
            mapa[fila - 1][nroColumna(columna)] = actual.get(i).getFigura();
        }
    }
    public void actualizarMapa(ArrayList<Ejercito>actual){
        int posF;
        char posC;
        for(int i = 0; i < actual.size(); i++){
            posF = actual.get(i).getFila();
            posC = actual.get(i).getColumna();
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
        } else if(territorio.equals("CAMPO ABIERTO") && ejer.get(0).getReino().equals("FRANCIA")){
            agregarVida(ejer);
        } else if(territorio.equals("MONTANIA") && (ejer.get(0).getReino().equals("CASTILLA") || ejer.get(0).getReino().equals("ARAGON"))){
            agregarVida(ejer);
        } else if(territorio.equals("PLAYA") && ejer.get(0).getReino().equals("MOROS")){
            agregarVida(ejer);
        } else if(territorio.equals("DESIERTO") && ejer.get(0).getReino().equals("SACRO")){
            agregarVida(ejer);
        }
    }
    //Metodo acoplado para agregar vida si tienen ventaja de territorio
    public static void agregarVida(ArrayList<Ejercito> ejer){
        for(int i = 0; i < ejer.size(); i++){
            for(int j = 0; j < ejer.get(i).getSoldados().size(); j++){
                if(ejer.get(i).getSoldados().get(j).getNivVidAct() < 5){
                    ejer.get(i).getSoldados().get(j).addVida();
                }
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