import java.util.*;
public class Practica{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
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
        ArrayList<Soldado> ejercito1 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2 = new ArrayList<Soldado>();
        datosEjercito(1, ejercito1, '*');
        datosEjercito(2, ejercito2, '&');
        actTablero(ejercito1, ejercito2, tablero);
        ArrayList<Soldado> ejercito1_cop1 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito1_cop2 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2_cop1 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2_cop2 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito1_Juego = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2_Juego = new ArrayList<Soldado>();
        char[][] tablero_Juego;
        boolean continuar = true;
        while(continuar){
            System.out.println("Opcion 1: Imprimir Tablero \nOpcion 2: Soldado con mayor vida del ejercito1\n" +
                            "Opcion 3: Soldado con mayor vida del ejercito2\nOpcion 4: Promedio de vida del ejercito 1\n" +
                            "Opcion 5: Promedio de vida del ejercito 2\nOpcion 6: Mostrar datos del ejercito1\n" + 
                            "Opcion 7: Mostrar datos del ejercito2\n" + "Opcion 8: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 9: 2do metodo de orden Ejercito 1\n" + "Opcion 10: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 11: 2do metodo de orden Ejercito 2\n" +  "Opcio 12: Iniciar Juego\n" + 
                            "Opcion 13: Salir");
            System.out.println("******************************");
            System.out.println("Ingrese una opcion");
            int opcion = sc.nextInt();
            System.out.println("******************************");
            switch(opcion){
                case 1:
                    System.out.println("El tablero es ");
                    impTablero(tablero);
                    break;
                case 2:
                    mayorVida(ejercito1, 1);
                    break;
                case 3:
                    mayorVida(ejercito2, 2);
                    break;
                case 4:
                    promVida(ejercito1, 1);
                    break;
                case 5:
                    promVida(ejercito2, 2);
                    break;
                case 6:
                    System.out.println("Los datos del ejercito 1 son : ");
                    mostDatos(ejercito1);
                    break;
                case 7:
                    System.out.println("Los datos del ejercito 2 son : ");
                    mostDatos(ejercito2);
                    break;
                case 8:
                    System.out.println("Primer metodo de orden - Ejercito 1");
                    copiaDatos(ejercito1, ejercito1_cop1);
                    ordenarBurbuja(ejercito1_cop1);
                    ejercito1_cop1.clear();
                    break;
                case 9:
                    System.out.println("2do metodo de orden - Ejercito 1");
                    copiaDatos(ejercito1, ejercito1_cop2);
                    ordenarSeleccion(ejercito1_cop2);
                    ejercito1_cop2.clear();
                    break;
                case 10:
                    System.out.println("1er metodo de orden - Ejercito 2");
                    copiaDatos(ejercito2, ejercito2_cop1);
                    ordenarBurbuja(ejercito2_cop1);
                    ejercito2_cop1.clear();
                    break;
                case 11:
                    System.out.println("2do metodo de orden - Ejercito 2");
                    copiaDatos(ejercito2, ejercito2_cop2);
                    ordenarSeleccion(ejercito2_cop2);
                    ejercito2_cop2.clear();
                    break;
                case 12:
                    System.out.println("Inicio de Juego");
                    tablero_Juego = tableroCopia(tablero);
                    copiaDatos(ejercito1, ejercito1_Juego);
                    copiaDatos(ejercito2, ejercito2_Juego);
                    impTablero(tablero_Juego);
                    juego(ejercito1_Juego, ejercito2_Juego, tablero_Juego);
                    ejercito1_Juego.clear();
                    ejercito2_Juego.clear();
                case 13:
                    System.out.println("Saliendo del programa");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }                   
    }
    public static void juego(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][]tablero){
        boolean validez = true;
        String coordenada;
        int movimiento, fila, columna;
        int i = 0;
        while(validez){
            if(ejercito1.size()== 0 || ejercito2.size() == 0){
                validez = false;
                break;
            } else if(i % 2 == 0){
                System.out.println("Turno del Ejercito 1");
                coordenada = ingresar(ejercito1.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito1, ejercito2, tablero);
                impTablero(tablero);
            } else {
                System.out.println("Turno del Ejercito 2");
                coordenada = ingresar(ejercito2.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito2, ejercito1, tablero);
                impTablero(tablero);
            }
            i++;
        }
        if(ejercito1.size() == 0){
            System.out.println("Salio victorio el ejercito 2");
            for(Soldado n: ejercito2){
                System.out.println(n);
            }
        } else{
            System.out.println("Salio victorioso el ejercito 1");
            for(Soldado m: ejercito2){
                System.out.println(m);
            }
        }
    }
    public static void movimientoJugado(String coordenada, int movimiento, ArrayList<Soldado> usuario, ArrayList<Soldado> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
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
    public static void batalla(String coordenada, int movimiento, ArrayList<Soldado> usuario, ArrayList<Soldado> contrincante, char[][] tablero){
        int fila_act, columna_act, fila_mov, columna_mov, posAct, us, cont, vidaAd;
        fila_act = Integer.parseInt(coordenada.substring(1, coordenada.length())) - 1;
        columna_act = nroColumna(coordenada.charAt(0));
        fila_mov = Movimiento.movFila(fila_act, movimiento);
        columna_mov = Movimiento.movColumna(columna_act, movimiento);
        String movRealizado = conversionBusqueda(columna_mov) + String.valueOf(fila_mov + 1);
        us = buscadorPosicion(usuario, coordenada);
        usuario.get(us).atacar();
        cont = buscadorPosicion(contrincante, movRealizado);
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
            contrincante.remove(cont);
            usuario.get(us).setFila(fila_mov + 1);
            usuario.get(us).setCol(conversionBusqueda(columna_mov));
            tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
            tablero[fila_act][columna_act] = '-';
            usuario.get(us).addVida();           
        } else {
            usuario.remove(us);
            tablero[fila_act][columna_act] = '-';
            contrincante.get(cont).addVida();
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
    public static void datosEjercito(int n, ArrayList<Soldado> ejercito, char fig){
        int nroSoldados = (int)(Math.random() * 10 + 1);
        System.out.println("El ejercito " + n + " tiene un total de " + nroSoldados + " soldados");
        for(int i = 0; i < nroSoldados; i++){
            String nombre = "Soldado " + ( i + 1) + "X" + n;
            ejercito.add(new Soldado(nombre, fig));
        }     
    }
    public static void impTablero(char[][] tablero){
        System.out.println("El tablero es :");
        for(int x = 0; x < tablero.length; x++){
            for(int y = 0; y < tablero[x].length; y++){
                System.out.print(tablero[x][y]);
            }
            System.out.println();
        }
    }
    public static void actTablero(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][] tablero){
        int fila;
        char columna;
        for(int i = 0; i < ejercito1.size(); i++){
            fila = ejercito1.get(i).getFila();
            columna = ejercito1.get(i).getColumna();
            while(tablero[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            ejercito1.get(i).setFila(fila);
            ejercito1.get(i).setCol(columna);
            tablero[fila - 1][nroColumna(columna)] = ejercito1.get(i).getFigura();
        }
        for(int j = 0; j < ejercito2.size(); j++){
            fila = ejercito2.get(j).getFila();
            columna = ejercito2.get(j).getColumna();
            while(tablero[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            ejercito2.get(j).setFila(fila);
            ejercito2.get(j).setCol(columna);
            tablero[fila - 1][nroColumna(columna)] = ejercito2.get(j).getFigura();
        }
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
    public static int buscadorPosicion(ArrayList<Soldado> ejercito, String posicion){
        for(int i = 0; i < ejercito.size(); i++){
            if(posicion.charAt(0) == ejercito.get(i).getColumna() && Integer.parseInt(posicion.substring(1, posicion.length())) == ejercito.get(i).getFila()){
                return i;
            }
        }
        return 0;
    }
    //Metodos antiguos
    public static char[][] tableroCopia(char[][] tablero){
        char[][] tableroNuevo = new char[tablero.length][tablero[0].length];
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                tableroNuevo[i][j] = tablero[i][j];
            }
        }
        return tableroNuevo;
    }
    public static void mayorVida(ArrayList<Soldado> ejercito, int n){
        int mayor = ejercito.get(0).getVida();
        for(int i = 1; i < ejercito.size(); i++){
            if(ejercito.get(i).getVida() > mayor)
                mayor = ejercito.get(i).getVida();
        }
        System.out.println("Los soldados con mayor vida del ejercito " + n + " son:");
        for(int j = 0; j < ejercito.size(); j++){
            if(ejercito.get(j).getVida() == mayor){
                System.out.println(ejercito.get(j));
            }
        }
    }
    public static void promVida(ArrayList<Soldado> ejercito, int m){
        double n = 0;
        for(int i = 0; i < ejercito.size(); i++)
            n += ejercito.get(i).getVida();
        n /= ejercito.size();
        System.out.println("El promedio de vida del ejercito " + m  + " es " + n);
    }
    public static void copiaDatos(ArrayList<Soldado> ejercito, ArrayList<Soldado> copia){
        for(int i = 0; i < ejercito.size(); i++){
            Soldado pos = ejercito.get(i);
            Soldado c = new Soldado(pos.getNombre(), pos.getAtaque(), pos.getDefensa(), pos.getVida(), pos.getNivVidAct(), pos.getVelocidad(), pos.getActitud(), pos.getVive(), pos.getFila(), pos.getColumna(), pos.getFigura());
            copia.add(c);
        }
    }
    public static void ordenarSeleccion(ArrayList<Soldado> ejercito){
        for(int i = 0; i < ejercito.size() - 1; i++){
            for(int j = i + 1; j < ejercito.size(); j++){
                Soldado may = ejercito.get(i);
                Soldado men = ejercito.get(j);
                if(men.getVida() > may.getVida()){
                    Soldado mayor = new Soldado(men.getNombre(), men.getAtaque(), men.getDefensa(), men.getVida(), men.getNivVidAct(), men.getVelocidad(), men.getActitud(), men.getVive(), men.getFila(), men.getColumna(), men.getFigura());
                    Soldado menor = new Soldado(may.getNombre(), may.getAtaque(), may.getDefensa(), may.getVida(), may.getNivVidAct(), may.getVelocidad(), may.getActitud(), may.getVive(), may.getFila(), may.getColumna(), may.getFigura());
                    ejercito.set(i, mayor);
                    ejercito.set(j, menor);
                }
            }
        }
        mostDatos(ejercito);
    }
    public static void ordenarBurbuja(ArrayList<Soldado> ejercito){
        for(int i = 0; i < ejercito.size() - 1; i++){
            for(int j = 0; j < ejercito.size() - 1; j++){
                Soldado may = ejercito.get(i);
                Soldado men = ejercito.get(i + 1);
                if(men.getVida() > may.getVida()){
                    Soldado mayor = new Soldado(men.getNombre(), men.getAtaque(), men.getDefensa(), men.getVida(), men.getNivVidAct(), men.getVelocidad(), men.getActitud(), men.getVive(), men.getFila(), men.getColumna(), men.getFigura());
                    Soldado menor = new Soldado(may.getNombre(), may.getAtaque(), may.getDefensa(), may.getVida(), may.getNivVidAct(), may.getVelocidad(), may.getActitud(), may.getVive(), may.getFila(), may.getColumna(), may.getFigura());
                    ejercito.set(j, mayor);
                    ejercito.set(j + 1, menor);
                }
            }
        }
        mostDatos(ejercito);
    }
    public static void mostDatos(ArrayList<Soldado> ejercito){
        for(Soldado n: ejercito)
            System.out.println(n);
    }  
}