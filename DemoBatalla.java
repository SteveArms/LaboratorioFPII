//Laboratorio 3 : Ejercicio Naves
//Autor : Armando Steven Cuno Cahuari
// Tiempo : 
import java.util.*;
public class DemoBatalla {
    public static void main(String [] args){
        char[][] mapa = {
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
            {'-','-','-','-','-','-','-','-','-','-',},
        };
        Nave [] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb, col;
        int fil, punt;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.println("Fila ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            col = sc.next();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
            misNaves[i] = new Nave(); //Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves, mapa);
        resetMap(mapa);
        System.out.println("Ingrese el nombrer de la nave");
        String nombre = sc.next();
        mostrarPorNombre(misNaves, mapa, nombre);
        resetMap(mapa);
        System.out.println("Ingrese el puntos a inferior que desea buscar");
        int puntos = sc.nextInt();
        mostrarPorPuntos(misNaves, mapa, puntos);
        System.out.println("\nNave con mayor número de puntos: ");
        int w = mayorPuntos(misNaves);
        System.out.println("La nave con mayor cantidad de puntos es " + misNaves[w].getNombre() + " con " + misNaves[w].getPuntos());
    }
        //Método para mostrar todas las naves
    public static void mostrarNaves(Nave [] flota, char[][] pos){
        for(int i = 0; i < flota.length; i++){
            int x = flota[i].getFila();
            int y = pos_y(flota[i].getColumna());
            if(flota[i].getEstado())
                pos[x-1][y] = '*';
        }
        impMapa(pos);
    }
            //Método para mostrar todas las naves de un nombre que se pide por teclado
    public static void mostrarPorNombre(Nave [] flota, char[][] n, String name){
        int posx;
        int posy;
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getNombre().equals(name) && flota[i].getEstado()){
                posx = flota[i].getFila();
                posy = pos_y(flota[i].getColumna());
                n[posx - 1][posy] = '*';
            }
        }
       impMapa(n);
    }
        //Método para mostrar todas las naves con un número de puntos inferior o igual
        //al número de puntos que se pide por teclado
    public static void mostrarPorPuntos(Nave [] flota, char[][] n, int pts){
        int x;
        int y;
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getPuntos()<=pts && flota[i].getEstado()){
                x = flota[i].getFila();
                y = pos_y(flota[i].getColumna());
                n[x - 1][y] = '*';
            }
        }
        impMapa(n);
    }
        //Metodo que imprime el mapa
    public static void impMapa(char[][] mapa){
        for(int i = 0; i < mapa.length; i++){
            for(int y  = 0;  y < mapa[i].length; y++){
                System.out.print(mapa[i][y]);
            }
            System.out.println();
        }
    }
        //Metodo para ubicar la posicion en el eje de la horizontal 
    public static int pos_y(String n){
        switch (n){
            case "a" :
                return 0;
            case "b" :
                return 1;
            case "c" :
                return 2;
            case "d" :
                return 3;
            case "e" :
                return 4;
            case "f" :
                return 5;
            case "g" :
                return 6;
            case "h" :
                return 7;
            case "i" :
                return 8;
            case "j" :
                return 9;
        }
        return 0;
    }
        //Metodo para reiniciar el mapa
    public static char [][] resetMap(char[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                m[i][j] = '-';
            }
        }
        return m;
    }
    //Método que devuelve la Nave con mayor número de Puntos
    public static int mayorPuntos(Nave[] naves){
        int cont = 0;
        for(int i = 1; i < naves.length; i++){
            if(naves[cont].getPuntos() <= naves[i].getPuntos()){
                cont = i;
            }
        }
        return cont;
    }
        //Crear un método que devuelva un nuevo arreglo de objetos con todos los objetos previamente ingresados
        //pero aleatoriamente desordenados
    public static void datRandom(){
        String alf = "abcdefghij";
        String alfN = "abcdefghijklmnopqrstuvwxyz";
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Nave[] naves = new Nave[n];
        for(int i = 0; i < naves.length; i++){
            int pts = (int)(Math.random()* 50);
            int fila = (int)(Math.random()* 10 + 1);
            String c = alf.substring((int)(Math.random()* alf.length()), (int)(Math.random()* alf.length()) + 1);
            String g = alfN.substring((int)(Math.random()* alfN.length()), (int)(Math.random()* alfN.length()) + 1);
            naves[i].setNombre(g);
            naves[i].setPuntos(pts);
            naves[i].setFila(fila);
            naves[i].setColumna(c);
        }
        for(int j = 0; j < naves.length; j++){
            System.out.println(naves[j].getPuntos() + " " + naves[j].getFila() + " " + naves[j].getNombre() + " " + naves[j].getColumna());
        }
    }
}