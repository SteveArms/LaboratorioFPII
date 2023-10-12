public class Soldado {
    private String nombre;
    private int posf;
    private char posc;
    private int p_vida;
    private char fig;
    public Soldado(String n, int l, char m, int x, char w){
        nombre = n;
        posf = l;
        posc = m;
        p_vida = x;
        fig = w;
    }
    public void setPosF(int n){
        posf = n;
    }
    public void setPosC(char n){
        posc = n;
    }
    public String getNombre(){
        return nombre;
    }
    public int getPosF(){
        return posf;
    }
    public char getPosC(){
        return posc;
    }
    public int getVida(){
        return p_vida;
    }
    public char getFigura(){
        return fig;
    }
}
