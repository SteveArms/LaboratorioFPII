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
        ArrayList<Soldado> ejercito_1 = new ArrayList<Soldado>();
        datosEjercito(1, ejercito_1, '%');       
        ArrayList<Soldado> ejercito_2 = new ArrayList<Soldado>();
        datosEjercito(2, ejercito_2, '*');
        actTablero(ejercito_1, tablero);
        actTablero(ejercito_2, tablero);
        ArrayList<Soldado> ejercito1_cop1 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito1_cop2 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2_cop1 = new ArrayList<Soldado>();
        ArrayList<Soldado> ejercito2_cop2 = new ArrayList<Soldado>();
        boolean continuar = true;
        System.out.println("Opcion 1: Imprimir Tablero \nOpcion 2: Soldado con mayor vida del ejercito1\n" +
                            "Opcion 3: Soldado con mayor vida del ejercito2\nOpcion 4: Promedio de vida del ejercito 1\n" +
                            "Opcion 5: Promedio de vida del ejercito 2\nOpcion 6: Mostrar datos del ejercito1\n" + 
                            "Opcion 7: Mostrar datos del ejercito2\n" + "Opcion 8: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 9: 2do metodo de orden Ejercito 1\n" + "Opcion 10: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 11: 2do metodo de orden Ejercito 2\n" + "Opcion 12: Ejercito ganador\n" +
                            "Opcion 13: Metodo serAtacado al 1er Ejercito\n" + "Opcion 14: Metodo serAtacado al 2do ejercito\n" +
                            "Opcion 15: Probabilidad de Rol por cada ejercito\n" +  
                            "Opcion 16: Salir");
        while(continuar){
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
                    mayorVida(ejercito_1, 1);
                    break;
                case 3:
                    mayorVida(ejercito_2, 2);
                    break;
                case 4:
                    promVida(ejercito_1, 1);
                    break;
                case 5:
                    promVida(ejercito_2, 2);
                    break;
                case 6:
                    System.out.println("Los datos del ejercito 1 son : ");
                    mostDatos(ejercito_1);
                    break;
                case 7:
                    System.out.println("Los datos del ejercito 2 son : ");
                    mostDatos(ejercito_2);
                    break;
                case 8:
                    System.out.println("Primer metodo de orden - Ejercito 1");
                    copiaDatos(ejercito_1, ejercito1_cop1);
                    ordenarBurbuja(ejercito1_cop1);
                    ejercito1_cop1.clear();
                    break;
                case 9:
                    System.out.println("2do metodo de orden - Ejercito 1");
                    copiaDatos(ejercito_1, ejercito1_cop2);
                    ordenarSeleccion(ejercito1_cop2);
                    ejercito1_cop2.clear();
                    break;
                case 10:
                    System.out.println("1er metodo de orden - Ejercito 2");
                    copiaDatos(ejercito_2, ejercito2_cop1);
                    ordenarBurbuja(ejercito2_cop1);
                    ejercito2_cop1.clear();
                    break;
                case 11:
                    System.out.println("2do metodo de orden - Ejercito 2");
                    copiaDatos(ejercito_2, ejercito2_cop2);
                    ordenarSeleccion(ejercito2_cop2);
                    ejercito2_cop2.clear();
                    break;
                case 12:
                    System.out.println("El ganador entre ambos ejercitos es :");
                    ganBatalla(ejercito_1, ejercito_2);
                    break;
                case 13:
                    System.out.println("Sufrieron ataques del ejercito 1:");
                    probAtacado(ejercito_1);
                    break;
                case 14:
                    System.out.println("Sufrieron ataques del ejercito 2:");
                    probAtacado(ejercito_2);
                    break;
                case 15:
                    System.out.println("Rol del ejercito 1:");
                    probRol(ejercito_1);                   
                    System.out.println("Rol del ejercito 2:");
                    probRol(ejercito_2);                  
                    break;
                case 16:
                    System.out.println("Saliendo del programa");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }       
    }
    public static void datosEjercito(int n, ArrayList<Soldado> ejercito, char fig){
        int nroSoldados = (int)(Math.random() * 10 + 1);
        System.out.println("El ejercito " + n + " tiene un total de " + nroSoldados + " soldados");
        for(int i = 0; i < nroSoldados; i++){
            String nombre = "Soldado " + ( i + 1) + "X" + n;
            ejercito.add(new Soldado(nombre, fig));
        }
        
    }
    public static void actTablero(ArrayList<Soldado> ejercito, char[][] tablero){
        for(int i = 0; i < ejercito.size(); i++){
            int fila = ejercito.get(i).getFila();
            char columna = ejercito.get(i).getColumna();
            if(tablero[fila - 1][nroColumna(columna)] != '-'){
                fila = (int)(Math.random() * 10 + 1);
                columna = Soldado.numCol();
            }
            ejercito.get(i).setFila(fila);
            ejercito.get(i).setCol(columna);
            tablero[fila - 1][nroColumna(columna)] = ejercito.get(i).getFigura();
        }
    }
    public static int nroColumna(char n){
        switch(n){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            default: return 0;
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
    public static void probAtacado(ArrayList<Soldado> ejercito){
        for(int i = 0; i < ejercito.size(); i++){
            int n = (int)(Math.random() * 2);
            if(n == 0){
                System.out.println("El soldado " + ejercito.get(i).getNombre() + " fue atacado");
                ejercito.get(i).serAtacado();
                System.out.println("Tiene de " + ejercito.get(i).getNivVidAct()  + " puntos de vida");
            }
        }
    }
    public static void probRol(ArrayList<Soldado> ejercito){
        for(int i = 0; i < ejercito.size(); i++){
            int n = (int)(Math.random() * 3);
            if(n == 0){
                System.out.println("El soldado " + ejercito.get(i).getNombre() + " atacara");
                ejercito.get(i).atacar();
            } else if( n == 1){
                System.out.println("El soldado " + ejercito.get(i).getNombre() + " defendera");
                ejercito.get(i).defender();
            } else{
                System.out.println("El soldado " + ejercito.get(i).getNombre() + " huida");
                ejercito.get(i).huir();
            }         
        }
    }
    public static void ganBatalla(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2){
        int suma1 = 0, suma2 = 0;
        for(int i = 0; i < ejercito1.size(); i++){
            suma1 += ejercito1.get(i).getVida();
        }
        for(int j = 0 ; j < ejercito2.size(); j++){
            suma2 += ejercito2.get(j).getVida();
        }
        if(suma1 > suma2){
            System.out.println("El ganador es el ejercito 1");
        } else if( suma2 > suma1){
            System.out.println("El ganador es el ejercito 2");
        } else {
            System.out.println("Es un empate");
        }
    }
}


