import java.util.*;
public class Videojuego5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[][] tablero ={{'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',},
                            {'-','-','-','-','-','-','-','-','-','-',}};
        HashMap<Integer, Soldado> ejer1 = new HashMap<Integer, Soldado>();
        HashMap<Integer, Soldado> ejer2 = new HashMap<Integer, Soldado>();
        int n1 = nroSoldados();
        System.out.println("La cantidad de soldado del 1er ejertico es " + n1);
        int n2 = nroSoldados();
        System.out.println("La cantidad de soldado del 2do ejertico es " + n2);
        datosSoldados(ejer1, n1, 1);
        datosSoldados(ejer2, n2, 2);
        llenarTablero(ejer1, tablero);
        llenarTablero(ejer2, tablero);
        HashMap<Integer, Soldado> copiaMetodoEjer1 = copia(ejer1);
        HashMap<Integer, Soldado> copiaMetodo2Ejer1 = copia(ejer1);;
        HashMap<Integer, Soldado> copiaMetodoEjer2 = copia(ejer2);
        HashMap<Integer, Soldado> copiaMetodo2Ejer2 = copia(ejer2);

        System.out.println("Opcion 1: Imprimir Tablero \nOpcion 2: Soldado con mayor vida del ejercito1\n" +
                            "Opcion 3: Soldado con mayor vida del ejercito2\nOpcion 4: Promedio de vida del ejercito 1\n" +
                            "Opcion 5: Promedio de vida del ejercito 2\nOpcion 6: Mostrar datos del ejercito1\n" + 
                            "Opcion 7: Mostrar datos del ejercito2\n" + "Opcion 8: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 9: 2do metodo de orden Ejercito 1\n" + "Opcion 10: 1er metodo de orden Ejercito 1\n" +
                            "Opcion 11: 2do metodo de orden Ejercito 2\n" + "Opcion 12: Ejercito ganador\n" + 
                            "Opcion 13: Salir");
        int opcion = sc.nextInt();
        while(opcion != 13){
            if(opcion == 1){
                System.out.println("El tablero es :");
                impTablero(tablero);
                System.out.println("********************************");
            } else if(opcion == 2){
                System.out.println("Mayor vida del ejercito 1 es : ");
                mayorVida(ejer1);
                System.out.println("********************************");
            } else if(opcion == 3){
                System.out.println("Mayor vida del ejercito 2 es : ");
                mayorVida(ejer2);
                System.out.println("********************************");
            } else if(opcion == 4){
                System.out.println("Promedio de vida del ejercito 1 es: ");
                promedioVida(ejer1, 1);
                System.out.println("********************************");
            } else if(opcion == 5){
                System.out.println("Promedio de vida del ejercito 2 es: ");
                promedioVida(ejer2, 2);
                System.out.println("********************************");
            } else if(opcion == 6){
                System.out.println("Los datos del Ejercito 1 es :");
                mostDatos(ejer1);
                System.out.println("********************************");
            } else if(opcion == 7){
                System.out.println("Los datos del Ejercito 2 es :");
                mostDatos(ejer2);
                System.out.println("********************************");
            } else if(opcion == 8){
                System.out.println("1er metodo de orden del Ejercito 1 :");
                metodoBurbuja(copiaMetodoEjer1);
                System.out.println("********************************");
            } else if(opcion == 9){
                System.out.println("2do metodo de orden del Ejercito 1 :");
                metodoSeleccion(copiaMetodo2Ejer1);
                System.out.println("********************************");
            } else if(opcion == 10){
                System.out.println("1er metodo de orden del Ejercito 2 :");
                metodoBurbuja(copiaMetodoEjer2);
                System.out.println("********************************");
            } else if(opcion == 11){
                System.out.println("2do metodo de orden del Ejercito 2 :");
                metodoSeleccion(copiaMetodo2Ejer2);
                System.out.println("********************************");
            } else if(opcion == 12){
                System.out.println("El ganador entre ambos ejercitos es : ");
                ejercitoGanador(ejer1, ejer2);
            }
            System.out.println("Ingrese otra opcion");
            opcion = sc.nextInt();
        }
    }
    public static void impTablero(char[][] n){
        for(int i = 0; i < n.length; i++){
            for(int j = 0; j < n[i].length; j++){
                System.out.print(n[i][j]);
            }
            System.out.println();
        }
    }
    public static void mostDatos(HashMap<Integer, Soldado> ejercito){
        for(Soldado i : ejercito.values()){
            System.out.println(i);
        }
    }

    public static void promedioVida(HashMap<Integer, Soldado> ejercito, int l){
        double n = 0;
        for(Soldado m: ejercito.values()){
            n += m.getVida();
        }
        n = n / ejercito.size();
        System.out.println("El promedio de vida del ejercito " + l + " es " + n);
    }

    public static void mayorVida(HashMap<Integer, Soldado> ejercito){
        int mayor = 0;
        for(Soldado i: ejercito.values()){
            if(i.getVida() > mayor){
                mayor = i.getVida();
            }
        }
        for(Soldado n: ejercito.values()){
            if(n.getVida() == mayor){
                System.out.println(n);
            }
        }
    }
    public static void metodoBurbuja(HashMap<Integer, Soldado> ejercito){
        for(int i = 0; i < ejercito.size() - 1; i++){
            for(int y = 0; y < ejercito.size() - 1; y++){
                Soldado m = ejercito.get(y);
                Soldado n = ejercito.get(y + 1);
                if(m.getVida() < n.getVida()){
                    Soldado menor = new Soldado(m.getNombre(), m.getPosF(), m.getPosC(), m.getVida(), m.getFigura());
                    Soldado mayor = new Soldado(n.getNombre(), n.getPosF(), n.getPosC(), n.getVida(), n.getFigura());
                    ejercito.put(y, mayor);
                    ejercito.put(y + 1, menor);
                }
            }
        }
        mostDatos(ejercito);
    }
    public static void metodoSeleccion(HashMap<Integer, Soldado> ejercito){
        Soldado[] nt = pasar(ejercito);
        for(int i = 0; i < nt.length - 1 ; i++){
            for(int j = i + 1; j < nt.length; j++){
                if(nt[i].getVida() < nt[j].getVida()){
                    Soldado menor = new Soldado(nt[i].getNombre(), nt[i].getPosF(), nt[i].getPosC(), nt[i].getVida(), nt[i].getFigura());
                    Soldado mayor = new Soldado(nt[j].getNombre(), nt[j].getPosF(), nt[j].getPosC(), nt[j].getVida(), nt[j].getFigura());
                    nt[i] = mayor;
                    nt[j] = menor;
                }
            }
        }
        ejercito.clear();
        for(int x = 0; x < nt.length; x++){
            ejercito.put(x, nt[x]);
        }
        mostDatos(ejercito);
    }
    public static Soldado[] pasar(HashMap<Integer, Soldado> ejercito){
        Soldado[] n = new Soldado[ejercito.size()];
        for(int i = 0; i < n.length; i++){
            n[i] = ejercito.get(i);
        }
        return n;
    }
    public static void ejercitoGanador(HashMap<Integer, Soldado> ejercito1, HashMap<Integer, Soldado> ejercito2){
        int ejer1 = 0, ejer2 = 0;
        for(Soldado m: ejercito1.values()){
            ejer1 += m.getVida();
        }
        for(Soldado n: ejercito2.values()){
            ejer2 += n.getVida();
        }
        if(ejer1 == ejer2){
            System.out.println("Existe un empate");
        } else if(ejer1 > ejer2){
            System.out.println("El ejercito 1 es el ganador con un total de " + ejer1 + " puntos");
        } else {
            System.out.println("El ejercito 2 es el ganador con un total de " + ejer2+ " puntos");
        }
    }
    public static HashMap<Integer, Soldado> copia(HashMap<Integer, Soldado> ejercito){
        HashMap<Integer, Soldado> copiaOriginal = new HashMap<Integer, Soldado>();
        for(int i = 0; i < ejercito.size(); i++){
            Soldado orig = ejercito.get(i);
            Soldado nuevo = new Soldado(orig.getNombre(), orig.getPosF(), orig.getPosC(), orig.getVida(), orig.getFigura());
            copiaOriginal.put(i,nuevo);
        }
        return copiaOriginal;
    }
    
    public static void datosSoldados(HashMap<Integer, Soldado> ejercito, int nro, int ej){
        for(int i = 0; i < nro; i++){
            String nombre = "Soldado" + (i + 1) + "X" + ej;
            Soldado p = new Soldado(nombre, nroFila(), nroColumna(), puntosVida(), figSoldado(ej));
            ejercito.put(i, p);
        }
    }
    public static void llenarTablero(HashMap<Integer, Soldado> ejercito, char[][] t){
        for(int i : ejercito.keySet()){
            Soldado n = ejercito.get(i);
            int fila = n.getPosF();
            char columna = n.getPosC();
            while(t[fila][posColumna(columna)] != '-'){
                fila = nroFila();
                columna = nroColumna();
            }
            t[fila][posColumna(columna)] = n.getFigura();
            n.setPosF(fila);
            n.setPosC(columna);
        }
    }
    public static char figSoldado(int n){
        if(n == 1)
            return '*';
        return '&';       
    }
    public static int nroSoldados(){
        return (int)(Math.random() * 9 + 1);
    }
    public static char nroColumna(){
        String n = "abcdefghij";
        int m = (int)(Math.random() * 9);
        return n.charAt(m);
    }
    public static int puntosVida(){
        return (int)(Math.random() * 4 + 1);
    }
    public static int nroFila(){
        return (int)(Math.random() * 9 + 1);
    }
    public static int posColumna(char n){
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
