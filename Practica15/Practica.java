import java.util.*;
public class Practica{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Creacion del ejercito");
        Ejercito ejercito = crear();
        int opcion;
        boolean validez = true;
        while(validez){
            System.out.println("******************************");
            System.out.println("Hay " + ejercito.getSoldados().size() + " soldados");
            System.out.println("Ingrese una opcion:");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingresar un nuevo soldado: ");
                    ingresarSoldado(ejercito);
                    break;
                case 2:
                    System.out.println("Modificar un soldado: ");
                    modifSoldado(ejercito);
                    break;
                case 3:
                    System.out.println("Eliminar un soldado: ");
                    elimSoldado(ejercito);
                    break;
                case 4:
                    System.out.println("Mayor nivel de ataque");
                    ejercito.soldadoMayorAtaque();
                    break;
                case 5:
                    System.out.println("Ranking de vida");
                    ejercito.rankingVida();
                    break;
                case 6:
                    System.out.println("Datos del ejercito");
                    ejercito.imprimirDatos();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    validez = false;
                    break;
            }
            System.out.println("**************************");
        }
    }
    public static void ingresarSoldado(Ejercito ejer){
        Scanner sc = new Scanner(System.in);
        if(ejer.getSoldados().size() <= 10 && ejer.getSoldados().size() >= 0){
            System.out.println("Desea ingresar un soldado autogenerado o personalizado\nAutogenerado - 1\nPersonalizado - 2");
            int opcion = sc.nextInt();
            if(opcion == 1){
                ejer.addSoldado(new Soldado(ejer.getReino(), '*'));
            } else {
                System.out.println("Ingrese el nivel de ataque");
                int ataque = sc.nextInt();
                System.out.println("Ingres el nivel de defensa");
                int defensa = sc.nextInt();
                ejer.addSoldado(new Soldado(ejer.getReino(), ataque, defensa, '*'));
            }
        } else {
            System.out.println("Sobrepasa la capacidad de limite de soldados");
        }
    }
    public static void modifSoldado(Ejercito ejer){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el indice del soldado a modificar");   
        int indice = sc.nextInt();
        Soldado ref = ejer.getSoldados().get(indice);
        System.out.println("Ingrese el nivel de ataque");
        int ataque = sc.nextInt();
        System.out.println("Ingrese el nivel de defensa");
        int defensa = sc.nextInt();
        ref.setAtaque(ataque);
        ref.setDefensa(defensa);
        ejer.modificarSoldado(indice, ref);
    }
    public static void elimSoldado(Ejercito ejer){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el indice del soldado a eliminar");
        int indice = sc.nextInt();
        System.out.println("Soldado eliminado: ");
        ejer.getSoldados().get(indice);
        ejer.eliminarSoldado(indice);
    }
    public static Ejercito crear(){
        Scanner sc = new Scanner(System.in);
        String nombre = ingresarNombre();
        return new Ejercito(nombre);
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
}