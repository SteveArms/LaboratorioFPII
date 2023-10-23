//Laboratorio 5: Arreglo Bidimensional
//Autor: Cuno Cahuari Armando Steven
//Tiempo: 2 horas
public class EjercitoSoldados {
    public static void main(String[] args){
        char[][] tablero = {{'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',},
                            {'_','_','_','_','_','_','_','_','_','_',}};
        int n = (int) (Math.random() * 9 + 1);
        System.out.println(n);
        Soldado[] ejercito = new Soldado[n];
        datosEjercito(ejercito);
        tablero = actTablero(ejercito, tablero);
        impTablero(tablero);
        soldadoMayorVida(ejercito);
        promedioVidaSoldados(ejercito);
        nivelVidaEjercito(ejercito);
        System.out.println("*************************************");
        datosSoldados(ejercito);
        System.out.println("*************************************");
        burbujaRank(ejercito);
        datosSoldados(ejercito);
        System.out.println("*************************************");
        seleccionRank(ejercito);
        datosSoldados(ejercito);   
    }
    public static char[][] actTablero(Soldado[] n, char[][] t){
        for(int i = 0; i < n.length; i++){
            int fila = n[i].getFila();
            char columna = n[i].getColumna();
            while(t[fila][posCol(columna)] != '_'){
                fila = nroFila();
                columna = nroColumna();
            }   
            t[fila][posCol(columna)] = '*';
            n[i].setFila(fila);
            n[i].setColumna(columna);
        }
        return t;   
    }
    public static void impTablero(char[][] n){
        System.out.println("Tablero posiciones :");
        for(int i = 0; i < n.length; i++){
            for(int y = 0; y < n[i].length; y++){
                System.out.print(n[i][y]);
            }
            System.out.println();
        }
    }
    public static void datosEjercito(Soldado[] n){
        System.out.println("Datos del ejercito");
        for(int i = 0; i < n.length; i++){
            n[i] = new Soldado();
            String nombre = "Soldado Nro " + (i + 1);
            int p = (int) (Math.random() * 5 + 1);
            char col = nroColumna();
            int fila = nroFila();
            n[i].setFila(fila);
            n[i].setColumna(col);
            n[i].setNombre(nombre);
            n[i].setPuntos(p);
        }
    }
    public static int nroFila(){
        return (int)(Math.random()* 10);
    }
    public static char nroColumna(){
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
    public static void soldadoMayorVida(Soldado[] n){
        int may = 0;
        for(int i = 1; i < n.length; i++){
            if(n[may].getPuntos() < n[i].getPuntos()){
                may = i;
            }
        }
        for(int j = 0; j < n.length; j++){
            if(n[j].getPuntos()== n[may].getPuntos()){
                System.out.println("El soldado con mayor vida son : " + n[j].getNombre() + " con unos puntos de vida: "
                            + n[j].getPuntos());
            }
        }
    }
    public static void promedioVidaSoldados(Soldado[] n){
        double sum = 0;
        for(int i = 0; i < n.length; i++){
            sum += n[i].getPuntos();
        }
        sum /= n.length;
        System.out.println("El promedio de vida entre todo el ejercito es : " + sum);
    }
    public static void nivelVidaEjercito(Soldado[] n){
        int sum = 0;
        for(int i = 0; i < n.length; i++){
            sum += n[i].getPuntos();
        }
        System.out.println("El nivel de vida del ejercito es : " + sum);
    }
    public static void datosSoldados(Soldado[] n){
        for(int i = 0; i < n.length; i++){
            System.out.println("Nombre : " + n[i].getNombre() + " Vida: " + n[i].getPuntos() +  " Fila : " + (n[i].getFila() + 1) 
                                + " Columna : " + n[i].getColumna());
        }   
    }
    public static void burbujaRank(Soldado[] n){
        for(int i = 0; i < n.length - 1; i++){
            for(int j = 0; j < n.length - 1; j++){
                if(n[j].getPuntos() < n[j + 1].getPuntos()){
                    String a_nom = n[j].getNombre();
                    int a_pun = n[j].getPuntos();
                    char a_col = n[j].getColumna();
                    int a_fil = n[j].getFila();
                    n[j].setNombre(n[j + 1].getNombre());
                    n[j].setPuntos(n[j + 1].getPuntos());
                    n[j].setFila(n[j + 1].getFila());
                    n[j].setColumna(n[j + 1].getColumna());
                    n[j + 1].setNombre(a_nom);
                    n[j + 1].setFila(a_fil);
                    n[j + 1].setColumna(a_col);
                    n[j + 1].setPuntos(a_pun);
                }
            }
        }
    }
    public static void seleccionRank(Soldado[] n){
        for(int i = 0; i < n.length - 1; i++){
            for(int j = i + 1; j < n.length ; j++){
                if(n[i].getPuntos() < n[j].getPuntos()){
                    String a_nom = n[i].getNombre();
                    int a_pun = n[i].getPuntos();
                    char a_col = n[i].getColumna();
                    int a_fil = n[i].getFila();
                    n[i].setNombre(n[j].getNombre());
                    n[i].setPuntos(n[j].getPuntos());
                    n[i].setFila(n[j].getFila());
                    n[i].setColumna(n[j].getColumna());
                    n[j].setNombre(a_nom);
                    n[j].setFila(a_fil);
                    n[j].setColumna(a_col);
                    n[j].setPuntos(a_pun);
                }
            }
        }
    }
}