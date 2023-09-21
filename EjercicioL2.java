//Laboratorio 2 - Ejercicio 1
//Autor: Cuno Cahuari Armando Steven
//Tiempo:
import java.util.*;
public class EjercicioL2 {
    public static void main(String[] args){
        String ahor1 = " +---+ \n"+
                        " | | \n" +
                        " | \n" +
                        " | \n" +
                        " | \n" +
                        " | \n" +
                        "========= ";
        String ahor2 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " | \n"+
                        " | \n"+
                        " | \n"+
                        "=========";
        String ahor3 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " | | \n"+
                        " | \n"+
                        " | \n"+
                        "=========";
   
        String ahor4 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " /| | \n"+
                        " | \n"+
                        " | \n"+
                        "=========";
   
        String ahor5 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " /|\\ | \n"+
                        " | \n"+
                        " | \n"+
                        "=========";
   
        String ahor6 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " /|\\ | \n"+
                        " / | \n"+
                        " | \n"+
                        "=========";
   
        String ahor7 = " +---+ \n"+
                        " | | \n"+
                        " O | \n"+
                        " /|\\ | \n"+
                        " / \\ | \n"+
                        " | \n"+
                        "=========";
        String [] figuras = {ahor1, ahor2, ahor3,ahor4,ahor5,ahor6,ahor7};
        int contador = 1;
        String letra;
        String [] palabras = {"programacion", "java", "identacion", "clases",
                                "objetos", "desarrollador", "pruebas"};
        String palSecreta = getPalabraSecreta(palabras);
        char[] pal = new char[palSecreta.length()];
        for(int i = 0; i < pal.length; i++)
            pal[i] = '-';
        String palabraRef = palabraComp(pal);
        System.out.println(figuras[0]);
        mostrarBlancos(palSecreta);
        System.out.println("\n");


        while(contador <= 6){          
            letra = ingreseLetra();
            if (letraEnPalabraSecreta(letra, palSecreta)){
                mostrarBlancosActualizados(letra, palSecreta, pal);
                impBlancosActualizados(pal);
                palabraRef = palabraComp(pal);
                if(palabraRef.equals(palSecreta))
                    break;              
            } else {
                System.out.println(figuras[contador]);
                contador = contador +1;
            }            
        }
        if(contador == 6){
            System.out.println("Tu perdiste");
        } else {
            System.out.println("Tu ganaste perdiendo solo " + contador + " vidas");
        }
         System.out.println("\n");
    }


    public static String palabraComp(char[] n){
        String m = "";
        for(int i = 0; i < n.length; i++)
            m += n[i];
        return m;
    }


    public static String getPalabraSecreta(String [] lasPalabras){
        String palSecreta;
        int ind;
        int indiceMayor = lasPalabras.length -1;
        int indiceMenor =0;
        ind = (int) ((Math.random() * (indiceMayor - indiceMenor + 1) + indiceMenor));
        return lasPalabras[ind];
    }


    public static void mostrarBlancos(String palabra){
        for(int i=0; i< palabra.length(); i++)
        System.out.print("_ " );
    }


    public static String ingreseLetra(){
        String laLetra;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese letra: ");
        laLetra = sc.next();
        while(laLetra.length()!= 1){
			System.out.println("Caracter invalido");
            System.out.println("Ingrese letra: ");
            laLetra = sc.next();
        }
        return laLetra;
    }


    public static boolean letraEnPalabraSecreta(String letra, String palSecreta ){
        int cont = 0;
        for(int i = 0; i < palSecreta.length(); i++){
            if(letra.charAt(0) == palSecreta.charAt(i))
                cont++;
        }
        if(cont != 0)
            return true;
        return false;
    }
   
    public static void mostrarBlancosActualizados(String letra, String pSecreta, char[] n){
        System.out.println("PROCESANDO.....");
        for(int i = 0; i < pSecreta.length(); i++){
            if(pSecreta.charAt(i) == letra.charAt(0)){
                n[i] = letra.charAt(0);
            }
        }
    }
    public static void impBlancosActualizados(char[] c){
        for(int i = 0; i < c.length; i++)
            System.out.print(c[i]);
        System.out.println();
    }
}
