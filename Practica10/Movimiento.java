public class Movimiento {
    public static int movFila(int fila, int mov){
        switch(mov){
            case 1: return fila -= 1;
            case 2: return fila -= 1;
            case 3: return fila -= 1;
            case 5: return fila += 1;
            case 6: return fila += 1;
            case 7: return fila += 1;
            default: return fila;
        }
    }
    public static int restFila(int fila, int mov){
        switch(mov){
            case 1: return fila += 1;
            case 2: return fila += 1;
            case 3: return fila += 1;
            case 5: return fila -= 1;
            case 6: return fila -= 1;
            case 7: return fila -= 1;
            default: return fila;
        }
    }
    public static int movColumna(int columna, int mov){
        switch(mov){
            case 3: return columna += 1;
            case 4: return columna += 1;
            case 5: return columna += 1;
            case 1: return columna -= 1;
            case 7: return columna -= 1;
            case 8: return columna -= 1;
            default: return columna;
        }
    }
    public static int restColumna(int columna, int mov){
        switch(mov){
            case 3: return columna -= 1;
            case 4: return columna -= 1;
            case 5: return columna -= 1;
            case 1: return columna += 1;
            case 7: return columna += 1;
            case 8: return columna += 1;
            default: return columna;
        }
    }
}
