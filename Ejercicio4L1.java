//Laboratorio Nro 1: Ejercicio 4
//Autor: Cuno Cahuari Armando Steven
//Tiempo: 
import java.util.*;
public class Ejercicio4L1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] listN = new String[5];
        int[] listV = new int[5];
        for(int i = 0; i < listN.length; i++){
            System.out.println("Ingrese el nombre del soldado :");
            listN[i] = sc.next();
            System.out.println("Ingrese el nivel de vida :");
            listV[i] = sc.nextInt();
        }
        for(int j = 0; j < listN.length; j++){
            System.out.println("El soldado Nro " + (j + 1) + " se llama :" + listN[j] + " con nivel de vida "  + listV[j]);
        }
    }
}
