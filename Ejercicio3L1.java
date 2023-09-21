//Laboratorio 1 : Ejercicio 3
//Autor: Cuno Cahuari Armando Steven
//Tiempo:
import java.util.*;
public class Ejercicio3L1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] list = new String[5];
        for(int i = 0; i < list.length; i++){
            System.out.println("Ingrese el nombre del soldado : ");
            list[i] = sc.next();
        }
        for(int i = 0; i < list.length; i++){
            System.out.println("El soldado Nro" + (i + 1) + " se llama :" + list[i]);
        }
    }
}
