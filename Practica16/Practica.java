import java.util.ArrayList;
import java.util.Scanner;
public class Practica{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Ejercito> actual = new ArrayList<Ejercito>();
        ArrayList<Ejercito> enemigo = new ArrayList<Ejercito>();
        boolean validez = true;
        Mapa tablero = new Mapa();
        datosEjercitos(actual, '*');
        System.out.println("_____________________________________");
        datosEjercitos(enemigo, '%');
        tablero.comprobarValores(actual);
        tablero.comprobarValores(enemigo);
        tablero.actualizarMapa(actual);
        tablero.actualizarMapa(enemigo);
        tablero.imprimirMapa();
        System.out.println("Tienen vida adicional los que tienen ventaja en entorno " + tablero.getTerritorio());
        tablero.vidaAdicional(actual);
        tablero.vidaAdicional(enemigo);
        while(validez){
            System.out.println("_____________________________________");
            System.out.println("Ingrese una opcion");
            int opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Imprimir datos del 1er ejercito");
                    imprimirDatos(actual);
                    break;
                case 2:
                    System.out.println("Imprimir datos del 2do ejercito");
                    imprimirDatos(enemigo);
                    break;
                case 3:
                    System.out.println("1er metodo de ejercito ganador");
                    ganadorMetodo1(actual, enemigo);
                    break;
                case 4:
                    System.out.println("2do metodo de ejercito ganador");
                    ganadorMetodo2(actual, enemigo);
                    break;
                case 5:
                    System.out.println("3er metodo de ejercito ganador");
                    ganadorMetodo3(actual, enemigo);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    validez = false;
                    break;
            }
        }      
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
    public static void ganadorMetodo1(ArrayList<Ejercito> uno, ArrayList<Ejercito> dos){
        int suma1 = 0, suma2 = 0;
        for(int i = 0; i < uno.size(); i++){
            suma1 += uno.get(i).cantidadVida();
        }
        for(int j = 0; j < dos.size(); j++){
            suma2 += dos.get(j).cantidadVida();
        }
        System.out.println("El ejercito nro 1 posee un total de vida de : " + suma1);
        System.out.println("El ejercito nro 2 posee un total de vida de : " + suma2);
        if(suma1 == suma2){
            System.out.println("Existe un empate");
        } else if(suma1 > suma2){
            System.out.println("Sale victorioso el 1er ejercito");
            for(Ejercito m: uno){
                System.out.println(m);
            }
        } else {
            System.out.println("Sale victorioso el 2do ejercito");
            for(Ejercito m: dos){
                System.out.println(m);
            }
        }
    }
    public static void ganadorMetodo2(ArrayList<Ejercito> uno, ArrayList<Ejercito> dos){
        double suma1 = 0.0, suma2 = 0.0;
        int cant1 = 0, cant2 = 0;
        for(int i = 0; i < uno.size(); i++){
            suma1 += uno.get(i).cantidadVida();
            cant1 += uno.get(i).cantidadSoldados();
        }
        for(int j = 0; j < dos.size(); j++){
            suma2 += dos.get(j).cantidadVida();
            cant2 += dos.get(j).cantidadSoldados();
        }
        suma1 = Math.round((suma1 / cant1) * 100.0) / 100.0;
        suma2 = Math.round((suma2 / cant2) * 100.0) / 100.0;
        System.out.println("El ejercito nro 1 posee  : " + suma1);
        System.out.println("El ejercito nro 2 posee  : " + suma2);
        if(suma1 == suma2){
            System.out.println("Existe un empate");
        } else if(suma1 > suma2){
            System.out.println("Sale victorioso el 1er ejercito");
            for(Ejercito m: uno){
                System.out.println(m);
            }
        } else {
            System.out.println("Sale victorioso el 2do ejercito");
            for(Ejercito m: dos){
                System.out.println(m);
            }
        }
    }
    public static void ganadorMetodo3(ArrayList<Ejercito>uno, ArrayList<Ejercito> dos){
        int cant1 = 0, cant2 = 0;
        int random = (int)(Math.random() * 100 + 1);
        for(int i = 0; i < uno.size(); i++){
            cant1 += uno.get(i).cantidadSoldados();
        }
        for(int j = 0; j < dos.size(); j++){
            cant2 += dos.get(j).cantidadSoldados();
        }
        double prob1 = (100 * cant1) / (cant1 + cant2);
        prob1 = Math.round(prob1 * 100.0 ) / 100.0;
        System.out.println("El 1er ejercito tiene un rango de 0 - " + prob1);
        System.out.println("El 2do ejercito tiene un rango de " + (prob1 + 1) + " - 100");
        System.out.println("El numero aleatorio fue de : "+ random);
        if(cant1 >= 0 && cant1<= prob1){
            System.out.println("Sale victorioso el 1er ejercito");
            for(Ejercito m: uno){
                System.out.println(m);
            }
        } else {
            System.out.println("Sale victorioso el 2do ejercito");
            for(Ejercito m: dos){
                System.out.println(m);
            }
        }

    }
}