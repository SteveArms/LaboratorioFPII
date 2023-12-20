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
        System.out.println("La cantidad de objetos Soldado creados es de : " + Soldado.cantSoldados);
        actTablero(ejercito1, tablero);
        actTablero(ejercito2, tablero);
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
                    opcionesJuego(ejercito1_Juego, ejercito2_Juego, tablero);  
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
    public static void juegoRapido(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][]tablero){
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean validez = true;
        while(validez){
            juego(ejercito1, ejercito2, tablero);
            System.out.println("Ingrese otra opcion");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Se inicio nuevamente la partida");
                    juego(ejercito1, ejercito2, tablero);
                    break;
                default:
                    System.out.println("Redireccionando al menu principal");
                    validez = false;
                    break;  
            }
        }
    }
    public static void opcionesJuego(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][]tablero){
        Scanner sc = new Scanner(System.in);
        boolean validez = true;
        int opcion;
        while(validez){
            System.out.println("Ingrese una opcion");
            System.out.println("Opcion 1: Juego Rapido\nOpcion 2: Juego Personalizado\nOpcion 3: Salir");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Se inicio una partida rapida");
                    juegoRapido(ejercito1, ejercito2, tablero);
                    break;
                case 2:
                    System.out.println("Se inicio una partida personalizada");
                    juegoPersonalizado(ejercito1, ejercito2, tablero);
                    break;
                case 3:
                    validez = false;
                    break;
            }
        }
    }
    public static void juegoPersonalizado(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][] tablero){
        Scanner sc = new Scanner(System.in);
        ArrayList<Soldado> ejercitoElegido;
        int opcion;
        boolean validez = true;
        while(validez){
            System.out.println("¿Que ejercito deseas aplicar modificacion?");
            ejercitoElegido = elegido(ejercito1, ejercito2);
            System.out.println("Opcion 1: Ingresar Soldado\nOpcion 2: Eliminar Soldado\nOpcion 3: Clonar Soldado" + 
            "\nOpcion 4: Modificar Soldado\nOpcion 5: Comparar Soldados\nOpcion 6: Intercambiar Soldados" + 
            "\nOpcion 7: Ver Soldado\nOpcion 8: Ver ejercito\nOpcion 9: Suma de Niveles\nOpcion 10: Iniciar juego" +
            "\nOpcion 11: Salir al menu principal");
            System.out.println("Ingrese la opcion que desea realizar:");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingresar soldado:");
                    addSoldado(ejercitoElegido, tablero);
                    break;
                case 2:
                    System.out.println("Eliminar soldado:");
                    eliminarSoldado(ejercitoElegido, tablero);
                    break;
                case 3:
                    System.out.println("Clonar soldado:");
                    clonarSoldado(ejercitoElegido, tablero);
                    break;
                case 4:
                    System.out.println("Modificar soldado:");
                    modificarSoldado(ejercitoElegido);
                    break;
                case 5:
                    System.out.println("Comparar soldados:");
                    compararSoldados(ejercitoElegido);
                    break;
                case 6:
                    System.out.println("Intercambiar soldados:");
                    intercambiarSoldados(ejercitoElegido);
                    break;
                case 7:
                    System.out.println("Ver Soldado:");
                    verSoldado(ejercitoElegido);
                    break;
                case 8:
                    System.out.println("Ver Ejercito:");
                    verEjercito(ejercitoElegido);
                    break;
                case 9:
                    System.out.println("Sumar niveles:");
                    sumarNiveles(ejercitoElegido);
                    break;
                case 10:
                    juego(ejercito1, ejercito2, tablero);
                case 11:
                    System.out.println("Redirigiendo al menu principal");
                    validez = false;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
    public static void addSoldado(ArrayList<Soldado> ejercito, char[][] tablero){
        Scanner sc = new Scanner(System.in);
        if(ejercito.size() == 10){
            System.out.println("Ya no es posible agregar un soldado a este ejercito ya que cuenta con 10 soldados");
        } else {
            System.out.println("Ingrese el nombre");
            String nombre = sc.next();
            System.out.println("Ingrese el nivel de Vida");
            int nivVida = sc.nextInt();
            Soldado nuevo = new Soldado(nombre, nivVida, ejercito.get(0).getFigura());
            ejercito.add(nuevo);
            actTablero(ejercito, tablero);
        }
    }
    public static void eliminarSoldado(ArrayList<Soldado> ejercito, char[][] tablero){
        Scanner sc = new Scanner(System.in);
        if(ejercito.size() == 1){
            System.out.println("No es posible eliminar ya que este es el ultimo soldado de este ejercito");
        } else {
            System.out.println("Datos de los Soldados");
            imprimirDatosEjercito(ejercito);
            System.out.println("Que soldado desea eliminar");
            int indice = sc.nextInt();
            System.out.println("Se eliminara el soldado Nro " + indice);
            System.out.println("Con datos: " + ejercito.get(indice));
            ejercito.remove(indice);
            actTablero(ejercito, tablero);
        }
    }
    public static void clonarSoldado(ArrayList<Soldado> ejercito, char[][] tablero){
        Scanner sc = new Scanner(System.in);
        if(ejercito.size() == 10){
            System.out.println("No es posible clonar porque el ejercito ya cuenta con 10 soldados");
        } else {
            System.out.println("Datos de los Soldados");
            imprimirDatosEjercito(ejercito);
            System.out.println("Que soldado deseas clonar");
            int indice = sc.nextInt();
            Soldado original = ejercito.get(indice);
            Soldado copia = new Soldado(original.getNombre(), original.getAtaque(), original.getDefensa(), original.getVida(), original.getNivVidAct(), original.getVelocidad(), original.getActitud(), original.getVive(), original.getFila(), original.getColumna(), original.getFigura());
            ejercito.add(copia);
            actTablero(ejercito, tablero);
        }
    }
    public static void modificarSoldado(ArrayList<Soldado> ejercito){
        Scanner sc = new Scanner(System.in);
        int valor;
        System.out.println("Datos de los Soldados");
        imprimirDatosEjercito(ejercito);
        System.out.println("Que soldado deseas modificar");
        int indice = sc.nextInt();
        System.out.println("Opcion 1: Nivel de Ataque\nOpcion 2: Nivel de defensa\nOpcion 3: Vida Actual");
        System.out.println("Ingrese la opcion que desea modificar");
        int opcion = sc.nextInt();
        System.out.println("Ingrese la cantidad que quiere cambiar:");
        valor = sc.nextInt();
        if(opcion == 1){
            if(valor <= 5 && valor>= 1){
                System.out.println("Cambio realizado");
                ejercito.get(indice).setAtaque(valor);
            } else {
                System.out.println("No es posible realizar el cambio porque rebasa los limites");
            }
        } else if(opcion == 2){
            if(valor <= 5 && valor>= 1){
                System.out.println("Cambio realizado");
                ejercito.get(indice).setDefensa(valor);
            } else {
                System.out.println("No es posible realizar el cambio porque rebasa los limites");
            }
        } else if(opcion == 3){
            if(valor <= 5 && valor>= 1){
                System.out.println("Cambio realizado");
                ejercito.get(indice).setNivVidAct(valor);
            } else {
                System.out.println("No es posible realizar el cambio porque rebasa los limites");
            }
        } else{
            System.out.println("Opcion invalida");
        }
    }
    public static void compararSoldados(ArrayList<Soldado> ejercito){
        Scanner sc = new Scanner(System.in);
        System.out.println("Datos  de los soldados");
        imprimirDatosEjercito(ejercito);
        System.out.println("Escoja el indice del 1er soldado");
        int primero = sc.nextInt();
        System.out.println("Escoja el indice del 2do soldado");
        int segundo = sc.nextInt();
        Soldado uno = ejercito.get(primero);
        Soldado dos = ejercito.get(segundo);
        if(comparar(uno, dos)){
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }
    }
    public static boolean comparar(Soldado uno, Soldado dos){
        return uno.getNombre().equals(dos.getNombre()) && uno.getAtaque() == dos.getAtaque() && uno.getDefensa() == dos.getDefensa() && uno.getVive() == dos.getVive() && uno.getVida() == dos.getVida();
    }
    public static void intercambiarSoldados(ArrayList<Soldado> ejercito){
        Scanner sc = new Scanner(System.in);
        System.out.println("Datos  de los soldados");
        imprimirDatosEjercito(ejercito);
        System.out.println("Escoja el indice del 1er soldado");
        int primero = sc.nextInt();
        System.out.println("Escoja el indice del 2do soldado");
        int segundo = sc.nextInt();
        Soldado original = ejercito.get(primero);
        Soldado copia = new Soldado(original.getNombre(), original.getAtaque(), original.getDefensa(), original.getVida(), original.getNivVidAct(), original.getVelocidad(), original.getActitud(), original.getVive(), original.getFila(), original.getColumna(), original.getFigura());
        ejercito.set(primero, ejercito.get(segundo));
        ejercito.set(segundo, copia);
        System.out.println("Intercambio realizado");
    }
    public static void verSoldado(ArrayList<Soldado> lista){
        Scanner sc = new Scanner(System.in);
        int indice;
        System.out.println("Ingrese el nombre que esta buscando");
        String nombre = sc.next();
        if(nombreUbicado(lista, nombre)){
            for(int i = 0; i < lista.size(); i++){
                if(lista.get(i).getNombre().equals(nombre)){
                    indice = i;
                    System.out.println(lista.get(i));
                    break;
                }
            }
        } else {
            System.out.println("No se encontro a nadie con ese nombre");
        }
    }
    public static void verEjercito(ArrayList<Soldado> ejercito){
        System.out.println("Datos  de los soldados");
        imprimirDatosEjercito(ejercito);
    }
    public static boolean nombreUbicado(ArrayList<Soldado> lista, String nombre){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<Soldado> elegido(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2){
        Scanner sc = new Scanner(System.in);
        System.out.println("Opcion 1 : Ejercito *\nOpcion 2: Ejercico $");
        int opcion = sc.nextInt();
        if(opcion == 1){
            return ejercito1; 
        } else {
            return ejercito2;
        }
    }
    public static void sumarNiveles(ArrayList<Soldado> ejercito){
        int valorVidaT = 0;
        int valorAtaqueT = 0;
        int valorDefensaT = 0;
        int valorVelocidadT = 0;
        for(Soldado m: ejercito){
            valorVidaT += m.getVida();
            valorAtaqueT += m.getAtaque();
            valorDefensaT += m.getDefensa();
            valorVelocidadT += m.getVelocidad();
        }
        System.out.println("El valor de vida total es : " + valorVidaT + "\nEl valor de ataque total es: " + valorAtaqueT +
        "\nEl valor de defensa total es: " + valorDefensaT + "\n El valor de velocidad total es: " + valorVelocidadT);
    }

    public static void juego(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2, char[][]tablero){
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
            System.out.println("El ganador de la batalla es el jugador actual");
            contrincante.remove(cont);
            usuario.get(us).setFila(fila_mov + 1);
            usuario.get(us).setCol(conversionBusqueda(columna_mov));
            tablero[fila_mov][columna_mov] = usuario.get(us).getFigura();
            tablero[fila_act][columna_act] = '-';
            usuario.get(us).addVida();           
        } else {
            System.out.println("El ganador de la batalla es el jugador enemigo");
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
        int nroSoldados = (int)(Math.random() * Soldado.CANTMAX + 1);
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
    public static void actTablero(ArrayList<Soldado> ejercito, char[][] tablero){
        int fila;
        char columna;
        for(int i = 0; i < ejercito.size(); i++){
            fila = ejercito.get(i).getFila();
            columna = ejercito.get(i).getColumna();
            while(tablero[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * Soldado.CANTMAX + 1);
                columna = Soldado.numCol();
            }
            ejercito.get(i).setFila(fila);
            ejercito.get(i).setCol(columna);
            tablero[fila - 1][nroColumna(columna)] = ejercito.get(i).getFigura();
        }
    }
    public static void imprimirDatosEjercito(ArrayList<Soldado> ejercito){
        for(int i = 0; i < ejercito.size(); i++){
            System.out.println((i + 1) + " " +ejercito.get(i));
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