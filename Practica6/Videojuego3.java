//Autor: Cuno Cahuari Armando Steven
//Laboratorio: 6
//Tiempo: 4 horas
import java.util.*;

public class Videojuego3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Character>> tablero = new ArrayList<ArrayList<Character>>();
        ArrayList<ArrayList<Soldado>> ejercito = new ArrayList<ArrayList<Soldado>>();
        ArrayList<Soldado> list = new ArrayList<Soldado>();
        datosSoldados(ejercito);
        creacionTablero(tablero);
        posicionamientoFiguras(ejercito, tablero);
        datosListaUno(ejercito, list);
        System.out.println("Opcion 1: Imprimir Tablero \nOpcion 2: Soldado con mayor vida del ejercito1\n" +
                            "Opcion 3: Soldado con mayor vida del ejercito2\nOpcion 4: Promedio de puntos del ejercito 1\n" +
                            "Opcion 5: Promedio de puntos del ejercito 2\nOpcion 6: Mostrar datos de todos los soldados\n" + 
                            "Opcion 7: Ranking de poder de ambos ejercito - Metodo 1\n" +
                            "Opcion 8: Ranking de poder de ambos ejercitos - Metodo 2\nOpcion 9: Ejercito ganador\n" + 
                            "Opcion 10: Salir");
        System.out.println("Ingrese una opcion :");
        int c = sc.nextInt();
        while(c != 10){
            if(c == 1){
                impTablero(tablero);
            } 
            if(c == 2){
                System.out.println("Mayor vida del ejercito 1"); 
                mayVida(ejercito, 0);
                System.out.println("************************************");
            }
            if(c == 3){
                System.out.println("Mayor vida del ejercito 2"); 
                mayVida(ejercito, 1);
                System.out.println("************************************");
            }
            if(c == 4){
                System.out.println("Promedio de vida del ejercito 1");
                promVida(ejercito, 0);
                System.out.println("************************************");
            }
            if(c == 5){
                System.out.println("Promedio de vida del ejercito 2");
                promVida(ejercito, 1);
                System.out.println("************************************");
            }
            if(c == 6){
                impDatos(list);
                System.out.println("************************************");
            }
            if(c == 7){
                System.out.println("Orden 1er metodo");
                rankSoldados1(list);
                System.out.println("************************************");
            }
            if(c == 8){
                System.out.println("Orden 2do metodo");
                rankSoldados2(list);
                System.out.println("************************************");
            }
            if(c == 9){
                ejerGanador(ejercito);
                System.out.println("************************************");
            }
            System.out.println("Ingrese una opcion :");
            c = sc.nextInt();
        }
    }
    public static void creacionTablero(ArrayList<ArrayList<Character>> t){
        for(int i = 0; i < 10; i++){
            t.add(new ArrayList<Character>());
            for(int j = 0; j < 10; j++){
                t.get(i).add('-');
            }
        }
    }

    public static void posicionamientoFiguras(ArrayList<ArrayList<Soldado>> n, ArrayList<ArrayList<Character>> t){
        for(int i = 0; i < n.size(); i++){
            for(int j = 0; j < n.get(i).size(); j++){
                int posF = n.get(i).get(j).getPosF();
                char posC = n.get(i).get(j).getPosC();
                while(t.get(posF).get(recCaracter(posC)) != '-'){
                    posF = (int)(Math.random() * 10);
                    posC = caracAleatColumna();
                }
                n.get(i).get(j).setPosF(posF);
                n.get(i).get(j).setPosC(posC);
                t.get(posF).set(recCaracter(posC),  n.get(i).get(j).getFigura());
            }
        }
    }

    public static void impTablero(ArrayList<ArrayList<Character>> t){
        for(int i = 0; i < t.size(); i++){
            for(int j = 0; j < t.get(i).size(); j++){
                System.out.print(t.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void promVida(ArrayList<ArrayList<Soldado>> n, int m){
        int sum = 0;
        for(int i = 0; i < n.get(m).size(); i++){
            sum += n.get(m).get(i).getVida();
        }
        System.out.println("El promedio del ejercito es : " + (sum / n.get(m).size()) );
    }
    public static void mayVida(ArrayList<ArrayList<Soldado>> n, int m){
        int vidaMayor  = 0;
        for(int i = 1; i < n.get(m).size(); i++){
            if(n.get(m).get(i).getVida() > n.get(m).get(vidaMayor).getVida()){
                vidaMayor = i;
            }
        }
        for(int j = 0; j < n.get(m).size(); j++){
            if(n.get(m).get(j).getVida() == n.get(m).get(vidaMayor).getVida()){
                System.out.println(n.get(m).get(j).getNombre() + " " + n.get(m).get(j).getFigura() + " " + n.get(m).get(j).getPosF() 
                + " " + n.get(m).get(j).getPosC() + " " + n.get(m).get(j).getVida());
            }
        }
    } 
     
    public static void datosSoldados(ArrayList<ArrayList<Soldado>> n){
        for(int i = 0; i < 2; i++){
            n.add(new ArrayList<Soldado>());
            int x = nroSoldadosEjercito();
            System.out.println("Ejercito Nro" + (i + 1) + " : " + x);
            for(int j = 0; j < x; j++){
                String nombre = "Soldado " + (j + 1) +"X" + (i + 1);
                int posFila = (int)(Math.random() * 10);
                int vida = (int)(Math.random() * 4 + 1);
                char fig = figuraSoldado(i);
                Soldado sold = new Soldado(nombre, posFila, caracAleatColumna(), vida, fig);
                n.get(i).add(sold);
            }
        }
    }

    public static void rankSoldados1(ArrayList<Soldado> lista){
        ArrayList<Soldado> lista1 = new ArrayList<Soldado>();
        lista1 = lista;
        for(int i = 0; i < lista1.size() - 1; i++){
            for(int j = i + 1; j < lista1.size(); j++){
                if(lista1.get(i).getVida() < lista1.get(j).getVida()){
                    Soldado mayor = new Soldado(lista1.get(j).getNombre(), lista1.get(j).getPosF(), lista1.get(j).getPosC(), lista1.get(j).getVida(), lista1.get(j).getFigura());
                    Soldado menor = new Soldado(lista1.get(i).getNombre(), lista1.get(i).getPosF(), lista1.get(i).getPosC(), lista1.get(i).getVida(), lista1.get(i).getFigura());
                    lista1.set(i, mayor);
                    lista1.set(j, menor);
                }
            }
        }
        impDatos(lista1);
    }

    public static void rankSoldados2(ArrayList<Soldado> lista){
        ArrayList<Soldado> lista2 = new ArrayList<Soldado>();
        lista2 = lista;
        for(int i = 0; i < lista2.size() - 1; i++){
            for(int j = 0; j < lista2.size() - 1; j++){
                if(lista2.get(j).getVida() < lista2.get(j + 1).getVida()){
                    Soldado mayor = new Soldado(lista2.get(j + 1).getNombre(), lista2.get(j + 1).getPosF(), lista2.get(j + 1).getPosC(), lista2.get(j + 1).getVida(), lista2.get(j + 1).getFigura());
                    Soldado menor = new Soldado(lista2.get(j).getNombre(), lista2.get(j).getPosF(), lista2.get(j).getPosC(), lista2.get(j).getVida(), lista2.get(j).getFigura());
                    lista2.set(j, mayor);
                    lista2.set(j + 1, menor);
                }
            }
        }
        impDatos(lista2);
    }

    public static void ejerGanador(ArrayList<ArrayList<Soldado>> ejer){
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i < ejer.get(0).size(); i++){
            sum1 += ejer.get(0).get(i).getVida();
        }
        for(int i = 0; i < ejer.get(1).size(); i++){
            sum2 += ejer.get(1).get(i).getVida();
        }
        if(sum1 == sum2){
            System.out.println("Hay un empate");
        } else if(sum1 > sum2){
            System.out.println("Gano el ejercito 1");
        } else {
            System.out.println("Gano el ejercito 2");
        }
    }

    public static int nroSoldadosEjercito(){
        return (int)(Math.random() * 10);
    }
    public static char caracAleatColumna(){
        String n = "abcdefghij";
        return n.charAt((int)(Math.random() * n.length()));
    }
    public static char figuraSoldado(int n){
        if(n == 0){
            return '*';
        }
        return '&';
    }
    public static int recCaracter(char n){
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
            default : return 0;
        }
    }

    public static void impDatos(ArrayList<Soldado> lista ){
        for(int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i).getNombre() + " " + lista.get(i).getFigura() + " " + lista.get(i).getPosF() + " " + lista.get(i).getPosC() + " " + lista.get(i).getVida());
        }
    }

    public static void datosListaUno (ArrayList<ArrayList<Soldado>> ejer, ArrayList<Soldado> n){
        for(int i = 0; i < ejer.size(); i++){
            for(int j = 0; j < ejer.get(i).size(); j++){
                Soldado c = new Soldado(ejer.get(i).get(j).getNombre(), ejer.get(i).get(j).getPosF(), ejer.get(i).get(j).getPosC(), ejer.get(i).get(j).getVida(), ejer.get(i).get(j).getFigura());
                n.add(c);
            }
        }
    }

}
