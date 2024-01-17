import java.util.*;

public class Ejercito {
    private ArrayList<Soldado> misSoldados;
    private String reino;
    private char fig;
    private int cantLancero = 0;
    private int cantCaballero = 0;
    private int cantEspadachin = 0;
    private int cantArquero = 0;
    public Ejercito(String nombre, char fig, int numero){
        reino = nombre;
        this.fig = fig;
        misSoldados = new ArrayList<Soldado>();
        datosSoldados(numero, fig);
    }
    public void datosSoldados(int numero, char fig){
        String nombre = "";
        int nroSoldados = (int)(Math.random() * 10 + 1);
        System.out.println("El ejercito tiene un total de " + nroSoldados + " soldados");
        for(int i = 0; i < nroSoldados; i++){
            int random = (int)(Math.random() * 4 + 1);
            if(random == 1){
                nombre = (i + 1) + "C" + numero;
                cantCaballero++;
                misSoldados.add(new Caballero(nombre, fig));
            } else if( random == 2){
                nombre = (i + 1) + "E" + numero;
                cantEspadachin++;
                misSoldados.add(new Espadachin(nombre, fig));
            } else if(random == 3){
                nombre = (i + 1) + "A" + numero;
                cantArquero++;
                misSoldados.add(new Arquero(nombre, fig));
            } else if(random == 4){
                nombre = (i + 1) + "L" + numero;
                cantLancero++;
                misSoldados.add(new Lancero(nombre, fig));
            }
        }    
    }
    public int getLancero(){
        return cantLancero;
    }
    public int getCaballero(){
        return cantCaballero;
    }
    public int getEspadachin(){
        return cantEspadachin;
    }
    public int getArquero(){
        return cantArquero;
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
    //Agrega la vida a cada uno de sus soldados
    public void agregarVida(){
        for(int i = 0; i < misSoldados.size(); i++){
            if(misSoldados.get(i).getNivVidAct() < 5){
                misSoldados.get(i).addVida();
            }
        }
    }
    //Devuelve la cantidad de vida de sus soldados
    public int cantidadVida(){
        int suma = 0;
        for(Soldado m: misSoldados){
            suma += m.getNivVidAct();
        }
        return suma;
    }
    public void imprimir(){
        System.out.println("Ejercito " + reino);
        for(Soldado m: misSoldados){
            System.out.println(m);
        }
    }
    //Metodo Soldados con mayor vida necesario ahcer una busqueda lineal con la mayor cantidad de vida e imprimir todos los soldados que tengan ese nivel de vida
    public void soldadosMayorVida(){
        int mayor = misSoldados.get(0).getVida();
        for(int i = 1; i < misSoldados.size(); i++){
            if(mayor < misSoldados.get(i).getVida()){
                mayor = misSoldados.get(i).getVida();
            }
        }
        System.out.println("Los soldados con mayor vida del ejercito " + reino + " son :");
        for(int i = 0; i < misSoldados.size(); i++){
            if(mayor == misSoldados.get(i).getVida()){
                System.out.println(misSoldados.get(i));
            }
        }
    }
    //Metodo de Seleccion para el ranking de Soldados
    public void rankingSoldados(){
        System.out.println("El ranking del reino " + reino + " es ");
        for(int i = 0; i < misSoldados.size() - 1; i++){
            for(int j = i + 1; j < misSoldados.size(); j++){
                if(misSoldados.get(i).getVida() < misSoldados.get(j).getVida()){
                    Soldado aux = misSoldados.get(i);
                    misSoldados.set(i, misSoldados.get(j));
                    misSoldados.set(j, aux);
                }
            }
        }
        imprimir();
    }
    //Metodo para el promedio de vida mediante la ayuda de otros metodos de instancia
    public void promedioVida(){
        System.out.println("El promedio de vida del ejercito " + reino + " es: ");
        System.out.println(this.cantidadVida() / this.cantidadSoldados());
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
