import java.util.ArrayList;
import java.util.Scanner;
public class Practica{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Ejercito> actual = new ArrayList<Ejercito>();
        ArrayList<Ejercito> enemigo = new ArrayList<Ejercito>();
        boolean validez = true;
        String map = aleatorioMapa();
        Mapa tablero = new Mapa(map);
        datosEjercitos(actual, '*');
        System.out.println("_____________________________________");
        datosEjercitos(enemigo, '%');
        tablero.comprobarValores(actual);
        tablero.comprobarValores(enemigo);
        tablero.actualizarMapa(actual);
        tablero.actualizarMapa(enemigo);
        char[][] tabl = tablero.getMapa();
        tablero.imprimirMapa();
        imprimir(actual, enemigo);  
        System.out.println("Tienen vida adicional los que tienen ventaja en entorno " + tablero.getTerritorio());
        tablero.vidaAdicional(actual);
        tablero.vidaAdicional(enemigo);
        juego(actual, enemigo, tabl);
    }
    public static void imprimir(ArrayList<Ejercito> ejercito1, ArrayList<Ejercito> ejercito2){
        System.out.println("Reino " + ejercito1.get(0).getReino());
        for(int i = 0; i < ejercito1.size(); i++){
            System.out.println("Ejercito " + (i + 1) + ejercito1.get(i));
        }
        System.out.println("Reino " + ejercito2.get(0).getReino());
        for(int x = 0; x < ejercito2.size(); x++){
            System.out.println("Ejercito " + (x + 1) + ejercito2.get(x));
        }
    }
    //Inicia el juego entre ambos ejercitos
    public static void juego(ArrayList<Ejercito> ejercito1, ArrayList<Ejercito> ejercito2, char[][]tablero){
        Scanner sc = new Scanner(System.in);
        boolean validez = true;
        String coordenada, jugar = "";
        int movimiento, fila, columna;
        int i = 0;
        while(validez){
            if(ejercito1.size()== 0 || ejercito2.size() == 0 || jugar.toUpperCase().equals("NO")){
                validez = false;
                break;
            } else if(i % 2 == 0){
                System.out.println("Turno del Ejercito 1");
                coordenada = ingresar(ejercito1.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito1, ejercito2, tablero);
                imprimirTablero(tablero);
            } else {
                System.out.println("Turno del Ejercito 2");
                coordenada = ingresar(ejercito2.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito2, ejercito1, tablero);
                imprimirTablero(tablero);
            }
            System.out.println("Desea seguir jugando: Ingrese si o no");
            jugar = sc.next();
            i++;
        }
        if(ejercito1.size() == 0){
            System.out.println("Salio victorio el ejercito 2");
            for(Ejercito n: ejercito1){
                System.out.println(n);
            }
        } else{
            System.out.println("Salio victorioso el ejercito 1");
            for(Ejercito m: ejercito2){
                System.out.println(m);
            }
        }
    }
    public static void movimientoJugado(String coordenada, int movimiento, ArrayList<Ejercito> usuario, ArrayList<Ejercito> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct;
        fila_act = conversionFila(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        if(tablero[fila_mov][columna_mov] == usuario.get(0).getFigura()){
            System.out.println("Posicion ocupada por una figura de tu mismo ejercito");
        } else if(tablero[fila_mov][columna_mov] == '-'){
            posAct = buscadorPosicion(usuario, coordenada);
            usuario.get(posAct).setFila(fila_mov + 1);
            usuario.get(posAct).setCol(conversionBusqueda(columna_mov));
            tablero[fila_act][columna_act] = '-';
            tablero[fila_mov][columna_mov] = usuario.get(0).getFigura();
        } else {
            System.out.println("Enemigo hallado");
            batalla(coordenada, movimiento, usuario, contrincante, tablero);
        }
    }
    //La metrica cuando se encuentran 2 ejercitos es por medio de porcentaje asimismo el valor total de vida por cada soldado por cada ejercito
    public static void batalla(String coordenada, int movimiento, ArrayList<Ejercito> usuario, ArrayList<Ejercito> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct, us, cont;
        fila_act = conversionFila(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        String movRealizado = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
        us = buscadorPosicion(usuario, coordenada);
        cont = buscadorPosicion(contrincante, movRealizado);
        double vidaEjercitoActual = usuario.get(us).cantidadVida();
        double vidaEjercitoEnemigo = contrincante.get(cont).cantidadVida();
        double nroRandom = (int)(Math.random() * 100 + 1);
        double probabilidad1 = (vidaEjercitoActual * 100) / (vidaEjercitoActual + vidaEjercitoEnemigo);
        System.out.println("Posibilidad de porcentaje del Ejercito actual: " + probabilidad1 + "%");
        System.out.println("Posibilidad de porcentaje del Ejercito enemigo: " + (100 - probabilidad1) + "%");
        System.out.println("El numero aleatorio fue " + nroRandom);
        if(0 <= nroRandom && nroRandom <= probabilidad1){
            contrincante.remove(cont);
            usuario.get(us).setFila(fila_mov + 1);
            usuario.get(us).setCol(conversionBusqueda(columna_mov));
            tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
            tablero[fila_act][columna_act] = '-';
            usuario.get(us).agregarVida();          
        } else {
            usuario.remove(us);
            tablero[fila_act][columna_act] = '-';
            contrincante.get(cont).agregarVida();
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
            fila = conversionFila(coordenada.substring(1, coordenada.length()));
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
            fila = conversionFila(coordenada.substring(1, coordenada.length())) - 1;
            columna = nroColumna(coordenada.charAt(0));
            fila = Movimiento.movFila(fila, movimiento);
            columna = Movimiento.movColumna(columna, movimiento);
            System.out.println(fila + "  Movimiento aplicado    " + columna);
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
    public static boolean validezMovimiento(int fila, int columna){
        return fila <= 9 && fila >= 0 && columna <= 9 && columna >= 0;       
    }
    public static int nroColumna(char n){
        switch(n){
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
    public static int conversionFila(String n){
        switch(n){
            case "1": return 1;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            default: return 0;
        }
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
    public static int buscadorPosicion(ArrayList<Ejercito> ejercito, String posicion){
        for(int i = 0; i < ejercito.size(); i++){
            if(posicion.charAt(0) == ejercito.get(i).getColumna() && conversionFila(posicion.substring(1, posicion.length())) == ejercito.get(i).getFila()){
                return i;
            }
        }
        return 0;
    }

    public static void imprimirDatos(ArrayList<Ejercito> ejer){
        for(int i = 0; i < ejer.size(); i++){
            for(int j = 0; j < ejer.get(i).getSoldados().size(); j++){
                System.out.println(ejer.get(i).getSoldados().get(j));
            }
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
    public static void imprimirTablero(char[][] tablero){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Metodo para definir el entorno del territorio
    public static String aleatorioMapa(){
        int rand = (int)(Math.random() * 5 + 1);
        switch(rand){
            case 1:
                return "BOSQUE";
            case 2:
                return "CAMPO ABIERTO";
            case 3:
                return "MONTANIA";
            case 4:
                return "DESIERTO";
            case 5:
                return "PLAYA";
            default:
                return " ";
        }
    }
}