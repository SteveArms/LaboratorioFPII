//Autor: Cuno Cahuari Armando Steven
//Laboratorio: 7
//Tiempo: 3 horas
import java.util.*;
public class Videojuego4 {
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
        int n1 = (int)(Math.random() * 9 + 1);
        System.out.println("La cantidad de soldados del ejercito 1 es : " + n1);
        int n2 = (int)(Math.random() * 9 + 1);
        System.out.println("La cantidad de soldados del ejercito 2 es : " + n2);
        Soldado[] ejer1 = new Soldado[n1];
        Soldado[] ejer2 = new Soldado[n2];
        datSoldados(ejer1, '*', 1);
        llenadoTablero(ejer1, tablero);
        datSoldados(ejer2, '$', 2);
        llenadoTablero(ejer2, tablero);
        ArrayList<Soldado> Soldados = ambosEjercitos(ejer1, ejer2);
        ArrayList<Soldado> orden1 = Soldados;
        ArrayList<Soldado> orden2 = Soldados;
        System.out.println("Opcion 1: Imprimir Tablero \nOpcion 2: Soldado con mayor vida del ejercito1\n" +
                            "Opcion 3: Soldado con mayor vida del ejercito2\nOpcion 4: Promedio de puntos del ejercito 1\n" +
                            "Opcion 5: Promedio de puntos del ejercito 2\nOpcion 6: Mostrar datos de todos los soldados\n" + 
                            "Opcion 7: Ranking de poder de ambos ejercito - Metodo 1\n" +
                            "Opcion 8: Ranking de poder de ambos ejercitos - Metodo 2\nOpcion 9: Ejercito ganador\n" + 
                            "Opcion 10: Salir");
        System.out.println("Ingrese una opcion :");
        int v = sc.nextInt();
        while(v != 10){
            if(v == 1){
                impTablero(tablero);
                System.out.println("************************************");
            }
            if(v == 2){
                System.out.println("Mayor vida del ejercito 1"); 
                mayVida(ejer1);
                System.out.println("************************************");
            }
            if(v == 3){
                System.out.println("Mayor vida del ejercito 2");
                mayVida(ejer2);
                System.out.println("************************************");
            } 
            if(v == 4){
                System.out.println("Promedio de puntos del ejercito 1");
                promPuntos(ejer1);
                System.out.println("************************************");
            }
            if(v == 5){
                System.out.println("Promedio de puntos del ejercito 2");
                promPuntos(ejer2);
                System.out.println("************************************");
            }
            if(v == 6){
                impSoldados(Soldados);
                System.out.println("************************************");
            }
            if(v == 7){
                System.out.println("RANKING 1er metodo: ***********************");
                rankSoldados1(orden1);
                System.out.println("************************************");
            }
            if(v == 8){
                System.out.println("RANKING 2do metodo: ***********************");
                rankSoldados2(orden2);
                System.out.println("************************************");
            }
            if(v == 9){
                ejercitoGanador(ejer1, ejer2);
                System.out.println("************************************");
            }
            System.out.println("Ingrese una opcion :");
            v = sc.nextInt();
        }              
        
    }

    public static void datSoldados(Soldado[] m, char n, int nEjer){
        for(int i = 0; i < m.length; i++){
            String nombre = "Soldado" + (i + 1)  + "X" + nEjer;
            int vida = vidAl();
            int fila = posfAl();
            char columna = poscAl();
            m[i] = new Soldado(nombre, fila, columna, vida, n);
        }
    }

    public static char[][] llenadoTablero(Soldado[] n, char[][] t){
        for(int i = 0; i < n.length; i++){
            int f = n[i].getPosF();
            char c = n[i].getPosC();
            while(t[f][posCol(c)] != '-'){
                f = posfAl();
                c = poscAl();
            }
            n[i].setPosF(f);
            n[i].setPosC(c);
            t[n[i].getPosF()][posCol(n[i].getPosC())] = n[i].getFigura();
        }
        return t;      
         
    }

    public static void mayVida(Soldado[] n){
        int mayor = 0;
        for(int i = 1; i < n.length; i++){
            if(n[i].getVida() > n[mayor].getVida()){
                mayor = i;
            }
        }
        for(int j = 0; j < n.length; j++){
            if(n[j].getVida() == n[mayor].getVida()){
                System.out.println(n[j].getNombre() + " " + n[j].getVida() + " " + (n[j].getPosF() + 1) + " " + n[j].getPosC() + " " + n[j].getFigura());
            }
        }
    }
    
    public static void promPuntos(Soldado[] n){
        int sum = 0;
        for(int i = 0; i < n.length; i++){
            sum += n[i].getVida();
        }
        System.out.println("El promedio de puntos es " + (sum / n.length));
    }

    public static void rankSoldados1(ArrayList<Soldado> n){
        for(int i = 0; i < n.size() - 1; i++){
            for(int j = i + 1; j < n.size(); j++){
                if(n.get(i).getVida() < n.get(j).getVida()){
                    Soldado mayor = new Soldado(n.get(j).getNombre() ,n.get(j).getPosF(), n.get(j).getPosC(), n.get(j).getVida(), n.get(j).getFigura());
                    Soldado menor = new Soldado(n.get(i).getNombre() ,n.get(i).getPosF(), n.get(i).getPosC(), n.get(i).getVida(), n.get(i).getFigura());
                    n.set(i, mayor);
                    n.set(j, menor);
                }
            }
        }
        impSoldados(n);
    }
    public static void rankSoldados2(ArrayList<Soldado> n){
        for(int i = 0; i < n.size() - 1; i ++){
            for(int j = 0; j < n.size() - 1; j++){
                if(n.get(j).getVida() < n.get(j + 1).getVida()){
                    Soldado mayor = new Soldado(n.get(j + 1).getNombre(), n.get(j + 1).getPosF(), n.get(j + 1).getPosC(), n.get(j + 1).getVida(), n.get(j + 1).getFigura());
                    Soldado menor = new Soldado(n.get(j).getNombre(), n.get(j).getPosF(), n.get(j).getPosC(), n.get(j).getVida(), n.get(j).getFigura());
                    n.set(j, mayor);
                    n.set(j + 1, menor);
                }
            }
        }
        impSoldados(n);
    }

    public static void ejercitoGanador(Soldado[] n, Soldado[] m){
        int sum1 = 0, sum2 = 0;
        for(int i = 0; i < n.length; i++){
            sum1 += n[i].getVida();
        }
        for(int j = 0; j < m.length; j++){
            sum2 += m[j].getVida();
        }
        System.out.println("Total de puntos del ejercito 1 " + sum1);
        System.out.println("Toral de puntos del ejercito 2 " + sum2);
        if(sum1 == sum2){
            System.out.println("Hay un empate");
        } else if(sum1 > sum2){
            System.out.println("El ganador es el ejercito 1");
        } else{
            System.out.println("El ganador es el ejercito 2");
        }
    }

    public static void impSoldados(ArrayList<Soldado> n){
        for(int i = 0; i < n.size(); i++){
            System.out.println("Soldado Nro" + (i + 1) + " " + n.get(i).getNombre() + " " + n.get(i).getVida() + " " + n.get(i).getPosF() + " " + n.get(i).getPosC() + " " + n.get(i).getFigura());
        }
    }

    public static void impTablero(char[][] tablero){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[i].length; j++){
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }

    public static ArrayList<Soldado> ambosEjercitos(Soldado[] n1, Soldado[] n2){
        ArrayList<Soldado> m = new ArrayList<Soldado>();
        for(int i = 0; i < n1.length; i++){
            m.add(n1[i]);
        }
        for(int j = 0; j < n2.length; j++){
            m.add(n2[j]);
        }
        return m;
    }

    public static int vidAl(){
        return (int)(Math.random()* 4 + 1);
    }

    public static int posfAl(){
        return (int)(Math.random()* 10);
    }

    public static char poscAl(){
        int n = (int)(Math.random()* 10);
        String a = "abcdefghij";
        return a.charAt(n);
    }

    public static int posCol(char n){
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
}
