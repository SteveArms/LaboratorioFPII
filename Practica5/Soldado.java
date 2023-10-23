//Laboratorio 5: Arreglo Bidimensional
//Autor: Cuno Cahuari Armando Steven
//Tiempo: 2 horas
public class Soldado{
    private String nombre;
    private int puntos;
    private int fila;
    private char columna;
    public void setNombre(String n){
        nombre = n;
    }   
    public void setPuntos(int p){
        puntos = p;
    }
    public void setFila(int n){
        fila = n;
    }
    public void setColumna(char c){
        columna = c;
    }
    public String getNombre(){
        return nombre;
    }
    public int getPuntos(){
        return puntos;
    }
    public int getFila(){
        return fila;
    }
    public char getColumna(){
        return columna;
    }
}
