import java.util.*;

public class Ejercito {
    private ArrayList<Soldado> misSoldados;
    private String reino;
    public Ejercito(String nombre){
        reino = nombre;
        misSoldados = new ArrayList<Soldado>();
    }
    public String getReino(){
        return reino;
    }
    public ArrayList<Soldado> getSoldados(){
        return misSoldados;
    } 
    public String toString(){
        String informacion = "Reino " + reino + "\n";
        for(int i = 0; i < misSoldados.size(); i++){
            informacion = informacion + "Soldado Nro " + (i + 1) + " || " +misSoldados.get(i) + "\n";
        }
        return informacion;
    }
    public void soldadoMayorAtaque(){
        int mayor = 0;
        for(int i = 1; i < misSoldados.size(); i++){
            if(misSoldados.get(mayor).getAtaque() < misSoldados.get(i).getAtaque()){
                mayor = i;
            }
        }
        System.out.println("Los soldados con mayor ataque son: ");
        for(Soldado may: misSoldados){
            if(may.getAtaque() == misSoldados.get(mayor).getAtaque()){
                System.out.println(may);
            }
        }
    }
    public void rankingVida(){
        ArrayList<Soldado> copia = new ArrayList<Soldado>();
        pasarDatos(misSoldados, copia);
        for(int i = 0; i < copia.size() - 1; i++){
            for(int j = i + 1; j < copia.size(); j++){
                Soldado may = copia.get(i);
                Soldado men = copia.get(j);
                if(men.getVida() > may.getVida()){
                    Soldado mayor = new Soldado(men.getNombre(), men.getAtaque(), men.getDefensa(), men.getVida(), men.getNivVidAct(), men.getVelocidad(), men.getActitud(), men.getVive(), men.getFila(), men.getColumna(), men.getFigura());
                    Soldado menor = new Soldado(may.getNombre(), may.getAtaque(), may.getDefensa(), may.getVida(), may.getNivVidAct(), may.getVelocidad(), may.getActitud(), may.getVive(), may.getFila(), may.getColumna(), may.getFigura());
                    copia.set(i, mayor);
                    copia.set(j, menor);
                }
            }
        }
        System.out.println("Reino " + reino);
        for(Soldado m: copia){
            System.out.println("\n" + m);
        }
    }
    public void imprimirDatos(){
        System.out.println("Reino " + reino);
        for(Soldado m: misSoldados){
            System.out.println("\n" + m);
        }
    }
    public static void pasarDatos(ArrayList<Soldado> original, ArrayList<Soldado> copia){
        for(int i = 0; i < original.size(); i++){
            Soldado pos = original.get(i);
            Soldado aux = new Soldado(pos.getNombre(), pos.getAtaque(), pos.getDefensa(), pos.getVida(), pos.getNivVidAct(), pos.getVelocidad(), pos.getActitud(), pos.getVive(), pos.getFila(), pos.getColumna(), pos.getFigura());
            copia.add(aux);
        }
    }
    public void addSoldado(Soldado nuevo){
        misSoldados.add(nuevo);
    }
    public void modificarSoldado(int indice, Soldado nuevo){
        misSoldados.set(indice, nuevo);
    }
    public void eliminarSoldado(int indice){
        misSoldados.remove(indice);
    }
}
