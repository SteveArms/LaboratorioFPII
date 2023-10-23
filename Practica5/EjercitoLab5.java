//Laboratorio 5: Ejercito - Videojuego2
//Autor: Armando Steven Cuno Cahuari
//Tiempo:
public class EjercitoLab5 {
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
        int n = (int) (Math.random() * 10 + 1);
        System.out.println(n);
        SoldadosLab5[] ejercito = new SoldadosLab5[n];
        datosEjercito(ejercito);
        actTablero(tablero, ejercito);
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
    public static void actTablero(char[][] n,SoldadosLab5[] m){
        for(int i = 0; i < m.length; i++){
            int posf = m[i].getFila() - 1;
            int posC = posCol(m[i].getColumna());
            n[posf][posC] = '*';
        }
    }
    public static void impTablero(char[][] n){
        for(int i = 0; i < n.length; i++){
            for(int y = 0; y < n[i].length; y++){
                System.out.print(n[i][y]);
            }
            System.out.println();
        }
    }
    public static void datosEjercito(SoldadosLab5[] n){
        String columna = "abcdefghij";
        for(int i = 0; i < n.length; i++){
            n[i] = new SoldadosLab5();
            int c = i;
            String nombre = "Soldado Nro " + (i + 1);
            int p = (int) (Math.random() * 5 + 1);
            char col = columna.charAt((int)(Math.random() * columna.length()));
            int fila = (int)(Math.random() * 10 + 1);
            n[i].setFila(fila);
            n[i].setColumna(col);
            n[i].setNombre(nombre);
            n[i].setPuntos(p);
            while(0 < c){
                if(n[i].getFila() == n[c - 1].getFila() && n[i].getColumna() == n[c - 1].getColumna()){
                    col = columna.charAt((int)(Math.random() * columna.length()));
                    fila = (int)(Math.random() * 10);
                    n[i].setFila(fila);
                    n[i].setColumna(col);
                }
                c--;
            }
        }
    }
    public static int posCol(char n){
        switch(n){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 5;
            case 'f': return 6;
            case 'g': return 7;
            case 'h': return 8;
            case 'i': return 9;
        }
        return 0;
    }
    public static void soldadoMayorVida(SoldadosLab5[] n){
        int may = 0;
        for(int i = 1; i < n.length; i++){
            if(n[may].getPuntos() < n[i].getPuntos()){
                may = i;
            }
        }
        System.out.println("El soldado con mayor vida son : " + n[may].getNombre() + " con unos puntos de vida: "
                            + n[may].getPuntos());
    }
    public static void promedioVidaSoldados(SoldadosLab5[] n){
        double sum = 0;
        for(int i = 0; i < n.length; i++){
            sum += n[i].getPuntos();
        }
        sum /= n.length;
        System.out.println("El promedio de vida entre todo el ejercito es : " + sum);
    }
    public static void nivelVidaEjercito(SoldadosLab5[] n){
        int sum = 0;
        for(int i = 0; i < n.length; i++){
            sum += n[i].getPuntos();
        }
        System.out.println("El nivel de vida del ejercito es : " + sum);
    }
    public static void datosSoldados(SoldadosLab5[] n){
        for(int i = 0; i < n.length; i++){
            System.out.println("Nombre : " + n[i].getNombre() + " Vida: " + n[i].getPuntos() +  " Fila : " + n[i].getFila() 
                                + " Columna : " + n[i].getColumna());
        }   
    }
    public static void burbujaRank(SoldadosLab5[] n){
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
    public static void seleccionRank(SoldadosLab5[] n){
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
