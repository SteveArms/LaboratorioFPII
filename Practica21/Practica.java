import java.util.*;
public class Practica{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String nombre = "";
        Ejercito actual, enemigo;
        String territorio = aleatorioMapa();
        Mapa tablero = new Mapa(territorio);
        nombre = ingresarNombre();
        actual = new Ejercito(nombre, '*', 1);
        nombre = ingresarNombre();
        enemigo = new Ejercito(nombre, '%', 2);
        tablero.comprobarValores(actual.getSoldados());
        tablero.comprobarValores(enemigo.getSoldados());
        Mapa.mapaSoldados(actual.getSoldados(), enemigo.getSoldados());
        tablero.vidaAdicional(actual);
        tablero.vidaAdicional(enemigo);
        char[][] mapa = tablero.getMapa();
        juego(actual.getSoldados(), enemigo.getSoldados(), mapa);
        resumen(actual, enemigo);
    }
    public static void juego(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][]tablero){
        Scanner sc = new Scanner(System.in);
        boolean validez = true;
        String coordenada, seguir = "";
        int movimiento;
        int i = 0;
        while(validez){
            if(ejercito1.size()== 0 || ejercito2.size() == 0 || seguir.equals("NO")){
                validez = false;
                break;
            } else if(i % 2 == 0){
                System.out.println("Turno del Ejercito *");
                coordenada = ingresar(ejercito1.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito1, ejercito2, tablero);
                Mapa.mapaSoldados(ejercito1, ejercito2);
            } else {
                System.out.println("Turno del Ejercito % ");
                coordenada = ingresar(ejercito2.get(0).getFigura(), tablero);
                movimiento = ingresarMovimiento(coordenada);
                movimientoJugado(coordenada, movimiento, ejercito2, ejercito1, tablero);
                Mapa.mapaSoldados(ejercito1, ejercito2);
            }
            System.out.println("Desea seguir jugando? Escriba no si no desea");
            seguir = sc.next().toUpperCase();
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
        agregarVidaBatalla(usuario, contrincante, us, cont);
        int total = usuario.get(us).getNivVidAct() + contrincante.get(cont).getNivVidAct();
        double probabilidad1 = usuario.get(us).getNivVidAct() * 100 / total;
        probabilidad1 = Math.round(probabilidad1 * 100.0) / 100.0;
        System.out.println("Posibilidad de porcentaje del Jugador actual: " + probabilidad1 + "%");
        System.out.println("Posibilidad de porcentaje del Jugador enemigo: " + (100 - probabilidad1) + "%");
        double nroRandom =Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
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
    public static void agregarVidaBatalla(ArrayList<Soldado> usuario, ArrayList<Soldado> contrincante, int posAct, int posCont){
        Soldado prim = usuario.get(posAct);
        Soldado seg = contrincante.get(posCont);
        if((usuario.get(posAct) instanceof Caballero) && (contrincante.get(posCont) instanceof Arquero)){
            prim.addVida();
        } else if((usuario.get(posAct) instanceof CaballeroFranco) && (contrincante.get(posCont) instanceof Arquero)){
            prim.addVida();
            prim.addVida();
        } else if((usuario.get(posAct) instanceof CaballeroMoro) && (contrincante.get(posCont) instanceof Arquero)){
            prim.addVida();
            prim.addVida();
        } else if((usuario.get(posAct) instanceof Caballero) && (contrincante.get(posCont) instanceof Lancero)){
            seg.addVida();
        } else if((usuario.get(posAct) instanceof Arquero) && (contrincante.get(posCont) instanceof Lancero)){
            prim.addVida();
        } else if((usuario.get(posAct) instanceof Caballero) && (contrincante.get(posCont) instanceof Espadachin)){
            prim.addVida();
        } else if((usuario.get(posAct) instanceof Espadachin) && (contrincante.get(posCont) instanceof Lancero)){
            prim.addVida();
        } else if((usuario.get(posAct) instanceof EspadachinConquistador) && (contrincante.get(posCont) instanceof Lancero)){
            prim.addVida();
            prim.addVida();
        } else if((usuario.get(posAct) instanceof EspadachinReal) && (contrincante.get(posCont) instanceof Lancero)){
            prim.addVida();
            prim.addVida();
        } else if((usuario.get(posAct) instanceof EspadachinTeutocnico) && (contrincante.get(posCont) instanceof Lancero)){
            prim.addVida();
            prim.addVida();
        } else if((usuario.get(posAct) instanceof Espadachin) && (contrincante.get(posCont) instanceof EspadachinConquistador)){
            seg.addVida();
        } else if((usuario.get(posAct) instanceof Espadachin) && (contrincante.get(posCont) instanceof EspadachinReal)){
            seg.addVida();
        } else if((usuario.get(posAct) instanceof Espadachin) && (contrincante.get(posCont) instanceof EspadachinTeutocnico)){
            seg.addVida();
        } else if((usuario.get(posAct) instanceof Caballero) && (contrincante.get(posCont) instanceof CaballeroFranco)){
            seg.addVida();
        } else if((usuario.get(posAct) instanceof Caballero) && (contrincante.get(posCont) instanceof CaballeroMoro)){
            seg.addVida();
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
    //Resumen que cuenta con indicacion del reino, cantidad de unidades, distribucion por unidades, nivel de vida total y porcentajes mas ganador.
    public static void resumen(Ejercito actual, Ejercito enemigo){
        System.out.println("Ejercito 1: " + actual.getReino());
        System.out.println("Cantidad de Soldados creados: " + actual.cantidadSoldados());
        System.out.println("Espadachines : " + actual.getEspadachin());
        System.out.println("Arqueros : " + actual.getArquero());
        System.out.println("Caballerows : " + actual.getCaballero());
        System.out.println("Lanceros : " + actual.getLancero());
        System.out.println("Espadachin Real : " + actual.getEspadachinReal());
        System.out.println("Espadachin Conquistador : " + actual.getEspadachinConquistador());
        System.out.println("Espadachin Teutonico : " + actual.getEspadachinTeutocnico());
        System.out.println("Caballero Franco : " + actual.getCaballeroFranco());
        System.out.println("Caballero Moro : " + actual.getCaballeroMoro());
        System.out.println();
        System.out.println("Ejercito 2: " + enemigo.getReino());
        System.out.println("Cantidad de Soldados creados: " + enemigo.cantidadSoldados());
        System.out.println("Espadachines : " + enemigo.getEspadachin());
        System.out.println("Arqueros : " + enemigo.getArquero());
        System.out.println("Caballerows : " + enemigo.getCaballero());
        System.out.println("Lanceros : " + enemigo.getLancero());
        System.out.println("Espadachin Real : " + enemigo.getEspadachinReal());
        System.out.println("Espadachin Conquistador : " + enemigo.getEspadachinConquistador());
        System.out.println("Espadachin Teutonico : " + enemigo.getEspadachinTeutocnico());
        System.out.println("Caballero Franco : " + enemigo.getCaballeroFranco());
        System.out.println("Caballero Moro : " + enemigo.getCaballeroMoro());
        System.out.println();
        double total = actual.cantidadSoldados() + enemigo.cantidadSoldados();
        double prob = Math.round(((actual.cantidadSoldados() * 100.0)/ total) * 100.0) / 100.0;
        double probabilidad = Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
        System.out.println("Ejercito 1 " + actual.getReino() + " : " + actual.cantidadSoldados() + " Probabilidad: " + prob);
        System.out.println("Ejercito 2 " + enemigo.getReino() + " : " + enemigo.cantidadSoldados() + " Probabilidad: " + (100 - prob));
        System.out.println("Por metricas el ganador es: El numero aleatorio fue " + probabilidad);
        if(probabilidad <= prob && probabilidad >= 0){
            System.out.println("Ganador el ejercito 1 " + actual.getReino());
        } else {
            System.out.println("Ganador el ejercito 2 " + enemigo.getReino());
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
    public static void datosSoldado(Ejercito actual, char figura){
        String nombre = ingresarNombre();

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
}