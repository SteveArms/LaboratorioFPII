import java.util.*;
public class BatallaLab4 {
    public static void main(String[] args){
        char[][] mapa = {{'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',},
                        {'-','-','-','-','-','-','-','-','-','-',}};
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
        mostrarNaves(misNaves);
        marMapa(misNaves, mapa);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves).getPuntos());
        //leer un nombre
        //mostrar los datos de la nave con dicho nombre, mensaje de no encontrado en caso contrario
        System.out.println("Ingrese el nombre de la nave");
        String nombre = sc.next();
        int pos=busquedaLinealNombre(misNaves,nombre);
        if(pos != -1){
            System.out.println("Nave Nro" + (pos + 1) + "\nNombre: " + misNaves[pos].getNombre() + 
                                "\nPuntos : " + misNaves[pos].getPuntos());
        } else {
            System.out.println("no encontrado");
        }
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);
        //mostrar los datos de la nave con dicho nombre, mensaje de no encontrado en caso contrario
        System.out.println("Ingrese el nombre de la nave");
        nombre = sc.next();
        pos=busquedaBinariaNombre(misNaves,nombre);
        if(pos != -1){
            System.out.println("Nave Nro" + (pos + 1) + "\nNombre: " + misNaves[pos].getNombre() + 
                                "\nPuntos : " + misNaves[pos].getPuntos());
        } else {
            System.out.println("no encontrado");
        }
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);
    }
    //Método para mostrar todas las naves
    public static void mostrarNaves(Nave[] flota){
        for(int i = 0; i < flota.length; i++){
            System.out.println("Nave Nro" + (i + 1) +"\nNombre: " + flota[i].getNombre() + "\nPuntos: " + flota[i].getPuntos());        
        }
    }
    public static void marMapa(Nave[] flota, char[][] n){
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getEstado()){
                int posy = posNave(flota[i].getColumna());
                int posx = flota[i].getFila() - 1;
                n[posx][posy] = '*';
            }
        }
        impMapa(n);
    }
    public static void impMapa(char[][] n){
        for(int i = 0; i < n.length; i++){
            for(int y = 0; y < n[i].length; y++){
                System.out.print(n[i][y]);
            }
            System.out.println();
        }
    }
    public static int posNave(String n){
        switch(n){
            case "a": return 0;
            case "b": return 1;
            case "c": return 2;
            case "d": return 3;
            case "e": return 4;
            case "f": return 5;
            case "g": return 6;
            case "h": return 7;
            case "i": return 8;
            case "j": return 9;
        }
        return -1;
    }
    
    //Método para mostrar todas las naves de un nombre que se pide por teclado
    public static void mostrarPorNombre(Nave[] flota){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre con el cual quiere buscar naves");
        String nombre = sc.next();
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getNombre().equals(nombre)){
                System.out.println("Nave Nro" + (i + 1) + "\nNombre: " + flota[i].getNombre() +
                "\nPuntos: " + flota[i].getPuntos());
            }
        }
    }
    
    //Método para mostrar todas las naves con un número de puntos inferior o igual
    //al número de puntos que se pide por teclado
    public static void mostrarPorPuntos(Nave[] flota){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantida de puntos con el cual quiere hallar naves");
        int puntos = sc.nextInt();
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getPuntos() <= puntos){
                System.out.println("Nave Nro" + (i + 1) + "\nNombre: " + flota[i].getNombre() + 
                "\nPuntos: " + flota[i].getPuntos());
            }
        }
    //REUTILIZAR
    }
    
    //Método que devuelve la Nave con mayor número de Puntos
    public static Nave mostrarMayorPuntos(Nave[] flota){
        int mayor = 0;
        for(int i = 1; i < flota.length; i++){
            if(flota[i].getPuntos() > flota[mayor].getPuntos()){
                mayor = i;
            }
        }
        return flota[mayor];
    //REUTILIZAR
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaLinealNombre(Nave[] flota, String s){
        for(int i = 0; i < flota.length; i++){
            if(flota[i].getNombre().equals(s)){
                return i;
            }
        }
        return -1;
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por burbuja puntos");
        for(int i = 0; i < flota.length - 1; i++){
            for(int y = 0; y < flota.length - 1; y++){
                if(flota[i].getPuntos() > flota[i + 1].getPuntos()){
                    String n_aux = flota[i].getNombre();
                    int f_aux = flota[i].getFila();
                    int p_aux = flota[i].getPuntos();
                    String c_aux = flota[i].getColumna();
                    boolean est_aux = flota[i].getEstado();
                    flota[i].setNombre(flota[i + 1].getNombre());
                    flota[i].setColumna(flota[i + 1].getColumna());
                    flota[i].setFila(flota[i + 1].getFila());
                    flota[i].setEstado(flota[i + 1].getEstado());
                    flota[i].setPuntos(flota[i + 1].getPuntos());
                    flota[i + 1].setColumna(c_aux);
                    flota[i + 1].setNombre(n_aux);
                    flota[i + 1].setEstado(est_aux);
                    flota[i + 1].setPuntos(p_aux);
                    flota[i + 1].setFila(f_aux);
                }
            }
        }
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por burbuja nombre");
        for(int i = 0; i < flota.length - 1; i++){
            for(int y = 0; y < flota.length - 1; y++){
                if(flota[i].getNombre().toUpperCase().charAt(0) > flota[i + 1].getNombre().toUpperCase().charAt(0)){
                    String n_aux = flota[i].getNombre();
                    int f_aux = flota[i].getFila();
                    int p_aux = flota[i].getPuntos();
                    String c_aux = flota[i].getColumna();
                    boolean est_aux = flota[i].getEstado();
                    flota[i].setNombre(flota[i + 1].getNombre());
                    flota[i].setColumna(flota[i + 1].getColumna());
                    flota[i].setFila(flota[i + 1].getFila());
                    flota[i].setEstado(flota[i + 1].getEstado());
                    flota[i].setPuntos(flota[i + 1].getPuntos());
                    flota[i + 1].setColumna(c_aux);
                    flota[i + 1].setNombre(n_aux);
                    flota[i + 1].setEstado(est_aux);
                    flota[i + 1].setPuntos(p_aux);
                    flota[i + 1].setFila(f_aux);
                }
            }
        }
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaBinariaNombre(Nave[] flota, String s){
        int menor = 0;
        int mayor = flota.length - 1;
        int medio;
		ordenarPorNombreSeleccion(flota);
        while(menor <= mayor){
            medio = (menor + mayor) / 2;
            if(flota[medio].getNombre().equals(s)){
                return medio;
            }
            if(flota[medio].getNombre().toUpperCase().charAt(0) > s.toUpperCase().charAt(0)){
                mayor = medio - 1;
            } else {
                menor = medio + 1;
            }
        }
        return -1;
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por seleccion puntos");
        for(int i = 0; i < flota.length - 1; i++){
            for(int j = i + 1; j < flota.length; j++){
                if(flota[i].getPuntos() > flota[j].getPuntos()){
                    String name_aux = flota[j].getNombre();
                    String col_aux = flota[j].getColumna();
                    int punt_aux = flota[j].getPuntos();
                    boolean est_aux = flota[j].getEstado();
                    int fil_aux = flota[j].getFila();
                    flota[j].setNombre(flota[i].getNombre());
                    flota[j].setEstado(flota[i].getEstado());
                    flota[j].setFila(flota[i].getFila());
                    flota[j].setColumna(flota[i].getColumna());
                    flota[j].setPuntos(flota[i].getPuntos());
                    flota[i].setNombre(name_aux);
                    flota[i].setEstado(est_aux);
                    flota[i].setColumna(col_aux);
                    flota[i].setPuntos(punt_aux);
                    flota[i].setFila(fil_aux);
                }
            }
        }
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por seleccion nombre");
        for(int i = 0; i < flota.length - 1; i++){
            for(int j = i + 1; j < flota.length; j++){
                if(flota[i].getNombre().toUpperCase().charAt(0) > flota[j].getNombre().toUpperCase().charAt(0)){
                    String name_aux = flota[j].getNombre();
                    String col_aux = flota[j].getColumna();
                    int punt_aux = flota[j].getPuntos();
                    boolean est_aux = flota[j].getEstado();
                    int fil_aux = flota[j].getFila();
                    flota[j].setNombre(flota[i].getNombre());
                    flota[j].setEstado(flota[i].getEstado());
                    flota[j].setFila(flota[i].getFila());
                    flota[j].setColumna(flota[i].getColumna());
                    flota[j].setPuntos(flota[i].getPuntos());
                    flota[i].setNombre(name_aux);
                    flota[i].setEstado(est_aux);
                    flota[i].setColumna(col_aux);
                    flota[i].setPuntos(punt_aux);
                    flota[i].setFila(fil_aux);
                }
            }
        }
    }
    //Método que muestra las naves ordenadas por número de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por insercion puntos");
        for(int i = 0; i < flota.length; i++){
            int pos = i;
            int aux = flota[i].getPuntos();
            while((pos > 0) && (flota[pos - 1].getPuntos() > aux)){
                String n_aux = flota[i].getNombre();
                int f_aux = flota[i].getFila();
                String c_aux = flota[i].getColumna();
                int p_aux = flota[i].getPuntos();
                boolean est_aux = flota[i].getEstado();
                flota[pos].setColumna(flota[pos - 1].getColumna());
                flota[pos].setEstado(flota[pos - 1].getEstado());
                flota[pos].setNombre(flota[pos - 1].getNombre());
                flota[pos].setFila(flota[i - 1].getFila());
                flota[pos].setPuntos(flota[i - 1].getPuntos());
                flota[pos - 1].setNombre(n_aux);
                flota[pos - 1].setColumna(c_aux);
                flota[pos - 1].setFila(f_aux);
                flota[pos - 1].setEstado(est_aux);
                flota[pos - 1].setPuntos(p_aux);
            }
        }
    }
    //Método que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota){
        System.out.println("********************************");
        System.out.println("Ordenamiento por insercion nombre");
        for(int i = 0; i < flota.length; i++){
            int pos = i;
            char aux = flota[i].getNombre().toUpperCase().charAt(0);
            while((pos > 0) && (flota[pos - 1].getNombre().toUpperCase().charAt(0) > aux)){
                String n_aux = flota[i].getNombre();
                int f_aux = flota[i].getFila();
                String c_aux = flota[i].getColumna();
                int p_aux = flota[i].getPuntos();
                boolean est_aux = flota[i].getEstado();
                flota[pos].setColumna(flota[pos - 1].getColumna());
                flota[pos].setEstado(flota[pos - 1].getEstado());
                flota[pos].setNombre(flota[pos - 1].getNombre());
                flota[pos].setFila(flota[i - 1].getFila());
                flota[pos].setPuntos(flota[i - 1].getPuntos());
                flota[pos - 1].setNombre(n_aux);
                flota[pos - 1].setColumna(c_aux);
                flota[pos - 1].setFila(f_aux);
                flota[pos - 1].setEstado(est_aux);
                flota[pos - 1].setPuntos(p_aux);
            }
        }
    }   
}
