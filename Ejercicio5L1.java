//Laboratorio Nro 1: Ejercicio 5
//Autor: Cuno Cahuari Armando Steven
//Tiempo:
public class Ejercicio5L1 {
    public static void main(String[] args){
        int sN1 = (int)(Math.random()*5 + 1);
        int sN2 = (int)(Math.random()*5 + 1);
        String[] e1 = new String[sN1];
        String[] e2 = new String[sN2];
        nomE1(e1);
        System.out.println();
        nomE2(e2);
        if(sN1 == sN2)
            System.out.println("Existe un empate entre ambos ejercitos ");
        if(sN1 > sN2){
            System.out.println("El Ejercito 1 es el ganador");
            nomE1(e1);
        } else {
            System.out.println("El Ejercito 2 es el ganador");
            nomE2(e2);
        }
    }
    public static void nomE1(String[] j){
        System.out.println("--------Ejercito 1--------");
        for(int i = 0; i < j.length; i++){
            j[i] = "Soldado " + (i + 1);
            System.out.println(j[i]);
        }
    }  
    public static void nomE2(String[] m){
        System.out.println("--------Ejercito 2--------");
        for(int j = 0; j < m.length; j++){
            m[j] = "Soldado " + (j + 1);
            System.out.println(m[j]);
        }
    }
}
