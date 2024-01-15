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
        tablero.imprimirMapa();
        boolean validez = true;
        //Consola iterable
        while (validez) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Ingrese una opcion");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Mostrando tablero distinguiendo ejercitos");
                    Mapa.mapaSoldados(actual, enemigo);
                    break;
                case 2:
                    System.out.println("Mostrando datos de ambos ejercitos: ");
                    actual.imprimir();
                    System.out.println("-------------------------------------------------------");
                    enemigo.imprimir();
                    break;
                case 3:
                    System.out.println("Soldados con mayor vida");
                    actual.soldadosMayorVida();
                    System.out.println("------------------------------------");
                    enemigo.soldadosMayorVida();
                    break;
                case 4:
                    System.out.println("Promedio de vida");
                    actual.promedioVida();
                    System.out.println("------------------------------------");
                    enemigo.promedioVida();
                    break;
                case 5:
                    System.out.println("Ranking de vida");
                    actual.rankingSoldados();
                    System.out.println("-------------------------------------");
                    enemigo.rankingSoldados();
                    break;
                case 6:
                    System.out.println("Metrica Ganador ");
                    metricaGanador(actual, enemigo);
                    break;
                default:
                    System.out.println("Opcion invalida");

            }
            System.out.println("-------------------------------------------------------");
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
    //La metrica del ganador se decide por mayor acumulacion de puntos de vida
    public static void metricaGanador(Ejercito actual, Ejercito enemigo){
        int ejer1 = actual.cantidadVida();
        int ejer2 = enemigo.cantidadVida();
        if(ejer1 == ejer2){
            System.out.println("Hay un empate entre ambos reinos");
        } else if(ejer2 < ejer1){
            System.out.println("Sale victorioso el reino " + actual.getReino());
            actual.imprimir();
        } else{
            System.out.println("Sale victorioso el reino " + enemigo.getReino());
            enemigo.imprimir();
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
}