import java.util.*;

public class Ejercito {
    private ArrayList<Soldado> misSoldados;
    private String reino;
    private int posFEjer;
    private char posCEjer;
    private char fig;
    public Ejercito(String nombre, char fig){
        posFEjer = (int)(Math.random() * 10 + 1);
        posCEjer = numCol();
        reino = nombre;
        this.fig = fig;
        misSoldados = new ArrayList<Soldado>();
        datosSoldados(nombre, fig);
    }
    public static void resetearTablero(char[][] tablero){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[i].length; j++){
                tablero[i][j] = '-';
            }
        }
    }
    public void datosSoldados(String nombre, char fig){
        int nroSoldados = (int)(Math.random() * 10 + 1);
        System.out.println("El ejercito tiene un total de " + nroSoldados + " soldados");
        for(int i = 0; i < nroSoldados; i++){
            misSoldados.add(new Soldado(nombre, fig));
        }     
    }
    public int getFila(){
        return posFEjer;
    }
    public char getColumna(){
        return posCEjer;
    }
    public void setposF(int m){
        posFEjer = m;
    }
    public void setposC(char m){
        posCEjer = m;
    }
    public char getFigura(){
        return fig;
    }
    public String getReino(){
        return reino;
    }
    public ArrayList<Soldado> getSoldados(){
        return misSoldados;
    }
    public int cantidadSoldados(){
        return misSoldados.size();
    }
    public int cantidadVida(){
        int suma = 0;
        for(Soldado m: misSoldados){
            suma += m.getNivVidAct();
        }
        return suma;
    }
    public String toString(){
        String informacion = "Reino " + reino + "\n";
        for(int i = 0; i < misSoldados.size(); i++){
            informacion = informacion + "Soldado Nro " + (i + 1) + " || " +misSoldados.get(i) + "\n";
        }
        return informacion;
    }
    public static char numCol(){
        String a = "ABCDEFGHIJ";
        int n = (int)(Math.random() * a.length());
        char car = a.charAt(n);
        return car;
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
}
