//Estrategico - Tactico: Mecanismos de Agregacion y Composicion
import java.util.*;
public class Practica{
    public static void main(String[] args){
        char[][] tableroEjercitos = {{'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'}};
        Scanner sc = new Scanner(System.in);
        ArrayList<Ejercito> actual = new ArrayList<Ejercito>();
        ArrayList<Ejercito> enemigo = new ArrayList<Ejercito>();
        datosEjercitos(actual, '*');
        datosEjercitos2(enemigo, '%');
        System.out.println("_____________________________________");
        comprobarValores(actual, tableroEjercitos);
        comprobarValores(enemigo, tableroEjercitos);
        imprimirMapa(tableroEjercitos); 
        juegoEjercito(actual, enemigo, tableroEjercitos);
        imprimirDatos(actual);
        System.out.println("\n*****************************************************\n");
        imprimirDatos(enemigo);             
    }
    public static void juegoEjercito(ArrayList<Ejercito> ejercito1, ArrayList<Ejercito> ejercito2, char[][]tablero){
        Scanner sc = new Scanner(System.in);
        boolean validez = true;
        String coordenada;
        int movimiento, fila, columna;
        String jugar = "";
        int i = 0;
        while(validez){
            if(ejercito1.size()== 0 || ejercito2.size() == 0 || jugar.equals("NO")){
                validez = false;
                break;
            } else if(i % 2 == 0){
                System.out.println("Turno del Ejercito 1");
                coordenada = ingresar('*', tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugadoEjercito(coordenada, movimiento, ejercito1, ejercito2, tablero);
                imprimirMapa(tablero);
            } else {
                System.out.println("Turno del Ejercito 2");
                coordenada = ingresar('%', tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugadoEjercito(coordenada, movimiento, ejercito2, ejercito1, tablero);
                imprimirMapa(tablero);
            }
            System.out.println("Desea seguir jugando?");
            jugar = sc.next();
            jugar = jugar.toUpperCase();
            i++;
        }
        if(ejercito1.size() == 0){
            System.out.println("Salio victorio el ejercito 2");
            for(Ejercito n: ejercito1){
                System.out.println(n);
            }
        } else if(ejercito2.size()==0){
            System.out.println("Salio victorioso el ejercito 1");
            for(Ejercito m: ejercito2){
                System.out.println(m);
            }
        } else {
            System.out.println("Se cancelo el juego");
        }
    }
    public static boolean validezMovimiento(int fila, int columna){
        return fila <= 9 && fila >= 0 && columna <= 9 && columna >= 0;       
    }
    public static void movimientoJugadoEjercito(String coordenada, int movimiento, ArrayList<Ejercito> usuario, ArrayList<Ejercito> contrincante, char[][] tablero){
        Scanner sc = new Scanner(System.in);
        String coordContrincante = "";
        int fila_act, columna_act, fila_mov, columna_mov,us, cont, posAct = 0;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        String movRealizado = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
        us = buscadorPosicionEjercito(usuario, coordenada);
        cont = buscadorPosicionEjercito(contrincante, movRealizado);
        if(tablero[fila_mov][columna_mov] == usuario.get(0).getFigura()){
            System.out.println("Posicion ocupada por una figura de tu mismo ejercito");
        } else if(tablero[fila_mov][columna_mov] == '-'){
            posAct = buscadorPosicionEjercito(usuario, coordenada);
            usuario.get(posAct).setposF(fila_mov + 1);
            usuario.get(posAct).setposC(conversionBusqueda(columna_mov));
            tablero[fila_act][columna_act] = '-';
            tablero[fila_mov][columna_mov] = usuario.get(0).getFigura();
        } else {
            System.out.println("Ejercito hallado");
            System.out.println("Ingrese como quiere resolver la batalla\n1: Metrica automatica por medio del acumulado de vida\n2: Jugar el tablero ");
            int opcion = sc.nextInt();
            coordContrincante = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
            if(opcion == 1){
                batallaMetrica(coordenada, movimiento, usuario, contrincante, tablero);
            } else {
                juegoSoldados(usuario.get(us).getSoldados(), contrincante.get(cont).getSoldados());
                if(contrincante.get(cont).getSoldados().size() == 0){
                    usuario.get(us).setposF(fila_mov + 1);
                    usuario.get(us).setposC(conversionBusqueda(columna_mov));
                    tablero[fila_act][columna_act] = '-';
                    tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
                }
            }
        }
    }
    public static void juegoSoldados(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2){
        System.out.println("El ejercito actual tiene " + ejercito1.size() + " soldados");
        System.out.println("El ejercito enemigo tiene " + ejercito2.size() + " soldados");
        char[][] tablero = {{'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'},
                                    {'-','-','-','-','-','-','-','-','-','-'}};
        System.out.println("_____________________________________");
        comprobarValoresSoldados(ejercito1, tablero);
        comprobarValoresSoldados(ejercito2, tablero);
        System.out.println("Tablero de la ubicacion de soldados");
        imprimirMapa(tablero);    
        Scanner sc = new Scanner(System.in);
        boolean validez = true;
        String coordenada;
        int movimiento, fila, columna;
        String jugar = "";
        int i = 0;
        while(validez){
            if(ejercito1.size()== 0 || ejercito2.size() == 0 || jugar.equals("NO")){
                validez = false;
                break;
            } else if(i % 2 == 0){
                System.out.println("Turno del Ejercito 1");
                coordenada = ingresar('*', tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugadoSoldado(coordenada, movimiento, ejercito1, ejercito2, tablero);
                imprimirMapa(tablero);
            } else {
                System.out.println("Turno del Ejercito 2");
                coordenada = ingresar('%', tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugadoSoldado(coordenada, movimiento, ejercito2, ejercito1, tablero);
                imprimirMapa(tablero);
            }
            System.out.println("Desea seguir jugando?");
            jugar = sc.next();
            jugar = jugar.toUpperCase();
            i++;
        }
        if(ejercito1.size() == 0){
            System.out.println("Salio victorio el ejercito 2");
            for(Soldado n: ejercito2){
                System.out.println(n);
            }
        } else if(ejercito2.size()==0){
            System.out.println("Salio victorioso el ejercito 1");
            for(Soldado m: ejercito2){
                System.out.println(m);
            }
        } else {
            System.out.println("Se cancelo el juego");
        }
    }
    public static void movimientoJugadoSoldado(String coordenada, int movimiento, ArrayList<Soldado> usuario, ArrayList<Soldado> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        if(tablero[fila_mov][columna_mov] == usuario.get(0).getFigura()){
            System.out.println("Posicion ocupada por una figura de tu mismo ejercito");
        } else if(tablero[fila_mov][columna_mov] == '-'){
            posAct = buscadorPosicionSoldado(usuario, coordenada);
            usuario.get(posAct).setFila(fila_mov + 1);
            usuario.get(posAct).setCol(conversionBusqueda(columna_mov));
            tablero[fila_act][columna_act] = '-';
            tablero[fila_mov][columna_mov] = usuario.get(0).getFigura();
        } else {
            System.out.println("Enemigo hallado");
            batalla(coordenada, movimiento, usuario, contrincante, tablero);
        }
    }
    public static void batalla(String coordenada, int movimiento, ArrayList<Soldado> usuario, ArrayList<Soldado> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct, us, cont, vidaAd;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        String movRealizado = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
        us = buscadorPosicionSoldado(usuario, coordenada);
        usuario.get(us).atacar();
        cont = buscadorPosicionSoldado(contrincante, movRealizado);
        contrincante.get(cont).defender();
        System.out.println("Jugador actual : " + usuario.get(us).getNivVidAct());
        System.out.println("Jugador enemigo : " + contrincante.get(cont).getNivVidAct());
        int total = usuario.get(us).getNivVidAct() + contrincante.get(cont).getNivVidAct();
        double probabilidad1 = usuario.get(us).getNivVidAct() * 100 / total;
        probabilidad1 = Math.round(probabilidad1 * 10.0) / 10.0;
        System.out.println("Posibilidad de porcentaje del Jugador actual: " + probabilidad1 + "%");
        System.out.println("Posibilidad de porcentaje del Jugador enemigo: " + (100 - probabilidad1) + "%");
        int nroRandom = (int)(Math.random() * 100 + 1);
        System.out.println("El numero aleatorio fue de " + nroRandom);
        if(0 <= nroRandom && nroRandom <= probabilidad1){
            System.out.println("El ganador de la batalla es el jugador actual");
            contrincante.remove(cont);
            usuario.get(us).setFila(fila_mov + 1);
            usuario.get(us).setCol(conversionBusqueda(columna_mov));
            tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
            tablero[fila_act][columna_act] = '-';           
        } else {
            System.out.println("El ganador de la batalla es el jugador enemigo");
            usuario.remove(us);
            tablero[fila_act][columna_act] = '-';
        }
    }
    public static void batallaMetrica(String coordenada, int movimiento, ArrayList<Ejercito> usuario, ArrayList<Ejercito> contrincante, char[][] tablero){
        int suma1 = 0, suma2 = 0;
        int fila_act, columna_act, fila_mov, columna_mov, posAct, us, cont, vidaAd;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        String movRealizado = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
        us = buscadorPosicionEjercito(usuario, coordenada);
        cont = buscadorPosicionEjercito(contrincante, movRealizado);
        for(Soldado m: usuario.get(us).getSoldados()){
            suma1 += m.getNivVidAct();
        }
        for(Soldado m: contrincante.get(cont).getSoldados()){
            suma2 += m.getNivVidAct();
        }
        double probabilidad1 = suma1 * 100 / (suma1 + suma2);
        probabilidad1 = Math.round(suma1 * 100.0) / 100.0;
        int nroRandom = (int)(Math.random() * 100 + 1);
        System.out.println("El rango del ejercito actuale es desde 0 - " + probabilidad1);
        System.out.println("El rango del ejercito enemigo es " + (probabilidad1 + 1) + " - 100");
        System.out.println("El numero aleatorio fue de " + nroRandom);
        if(0 <= nroRandom && nroRandom <= probabilidad1){
            System.out.println("El ganador de la batalla es el jugador actual");
            contrincante.get(cont).getSoldados().clear();
            usuario.get(us).setposF(fila_mov + 1);
            usuario.get(us).setposC(conversionBusqueda(columna_mov));
            tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
            tablero[fila_act][columna_act] = '-';           
        } else {
            System.out.println("El ganador de la batalla es el jugador enemigo");
            usuario.get(us).getSoldados().clear();
            tablero[fila_act][columna_act] = '-';
        }
    }
    public static String ingresar(char n, char[][] tablero){
        String coordenada = "";
        int fila, columna;
        boolean validez = true;
        Scanner sc = new Scanner(System.in);
        while(validez){
            System.out.println("1 2 3\n8 x 4\n7 6 5");
            System.out.println("Ingrese otra coordenada");
            coordenada = sc.next();
            fila = Integer.parseInt(coordenada.substring(1, coordenada.length()));
            columna = nroColumna(coordenada.charAt(0));
            System.out.println(fila + "       " + columna);
            if(tablero[fila - 1][columna] == n){
                break;
            } else {
                System.out.println("Coordenada incorrecta");
            }
        }
        return coordenada;
    }
    public static int ingresarMovimiento(String coordenada){
        int movimiento = 0;
        boolean validez = true;
        int fila, columna;
        Scanner sc = new Scanner(System.in);
        while(validez){
            System.out.println("Ingresar Movimiento: ");
            movimiento = sc.nextInt();
            fila = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
            columna = nroColumna(coordenada.charAt(0));
            fila = Movimiento.movFila(fila, movimiento);
            columna = Movimiento.movColumna(columna, movimiento);
            if(validezMovimiento(fila, columna)){
                break;
            } else {
                System.out.println("Movimiento invalido");
                fila = Movimiento.restFila(fila, movimiento);
                columna = Movimiento.restColumna(columna, movimiento);
            }
        }
        return movimiento;
    }
    public static void comprobarValores(ArrayList<Ejercito> actual, char[][] mapa){
        int fila;
        char columna;
        for(int i = 0; i < actual.size(); i++){
            fila = actual.get(i).getFila();
            columna = actual.get(i).getColumna();
            while(mapa[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            actual.get(i).setposF(fila);
            actual.get(i).setposC(columna);
            mapa[fila - 1][nroColumna(columna)] = actual.get(i).getFigura();
        }
    }
    public static void comprobarValoresSoldados(ArrayList<Soldado> actual, char[][] mapa){
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
    public static void imprimirMapa(char[][] mapa){
        System.out.println("Se imprime el territorio");
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int nroColumna(char m){
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
    public static void imprimirDatos(ArrayList<Ejercito> ejer){
        for(Ejercito actual: ejer){
            System.out.println(actual);
        }
    }
    public static void datosEjercitos(ArrayList<Ejercito> ejer, char m){
        int rand = (int)(Math.random() * 10 + 1);
        System.out.println("El nro de ejercitos es " + rand);
        System.out.println("Ingrese el nombre del territorio: ");
        String ingresar = ingresarNombre();
        for(int i = 0; i < rand; i++){
            ejer.add(new Ejercito(ingresar, m));
        }
    }
    public static void datosEjercitos2(ArrayList<Ejercito> ejer, char m){
        int rand = (int)(Math.random() * 10 + 1);
        System.out.println("El nro de ejercitos es " + rand);
        System.out.println("Ingrese el nombre del territorio: ");
        String ingresar = ingresarNombre();
        Ejercito nuevo;
        for(int i = 0; i < rand; i++){
            nuevo = new Ejercito(ingresar, m);
            ejer.add(nuevo);
        }
    }
    public static String ingresarNombre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del ejercito solo validos :");
        System.out.println("Inglaterra - Francia - Castilla o Aragon - Sacro - Moros");
        String nombre = sc.next().toUpperCase();
        while(!validarNombre(nombre)){
            System.out.println("Reino incorrecto ingrese otro");
            nombre = sc.next().toUpperCase();
        }
        return nombre;
    }
    public static boolean validarNombre(String nombre){
        return nombre.equals("INGLATERRA") || nombre.equals("FRANCIA") || nombre.equals("CASTILLA") || nombre.equals("ARAGON") || nombre.equals("SACRO") || nombre.equals("MOROS");
    }
    public static int buscadorPosicionEjercito(ArrayList<Ejercito> ejercito, String posicion){
        for(int i = 0; i < ejercito.size(); i++){
            if(posicion.charAt(0) == ejercito.get(i).getColumna() && Integer.parseInt(posicion.substring(1, posicion.length())) == ejercito.get(i).getFila()){
                return i;
            }
        }
        return 0;
    }
    public static int buscadorPosicionSoldado(ArrayList<Soldado> ejercito, String posicion){
        for(int i = 0; i < ejercito.size(); i++){
            if(posicion.charAt(0) == ejercito.get(i).getColumna() && Integer.parseInt(posicion.substring(1, posicion.length())) == ejercito.get(i).getFila()){
                return i;
            }
        }
        return 0;
    }
    public static char conversionBusqueda(int m){
        switch(m){
            case 0: return 'A';
            case 1: return 'B';
            case 2: return 'C';
            case 3: return 'D';
            case 4: return 'E';
            case 5: return 'F';
            case 6: return 'G';
            case 7: return 'H';
            case 8: return 'I';
            case 9: return 'J';
            default: return 0;
        }
    }
}