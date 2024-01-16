public class Soldado {
    private String nombre;
    private int nivelAtaque;
    private int nivelDefensa;
    private int nivelVida;
    private int vidaActual;
    private int velocidad;
    private String actitud;
    private boolean vive;
    private int posFila;
    private char posCol;
    private char figura;
    public Soldado(String nom, char fig, int vida){
        nombre = nom;
        nivelAtaque = (int)(Math.random() * 5 + 1);
        nivelDefensa = (int)(Math.random() * 5 + 1);
        nivelVida = vida;
        vidaActual = nivelVida;
        velocidad = 0;
        actitud = "Defensiva";
        vive = true;
        posFila = (int)(Math.random() * 10 + 1);
        posCol = numCol();
        figura = fig;
    }
    public Soldado(String nom, int nivAtaq, int nivDef, int vid, char fig){
        nombre = nom;
        nivelAtaque = nivAtaq;
        nivelDefensa = nivDef;
        nivelVida = vid;
        vidaActual = vid;
        velocidad = 0;
        actitud = "Defensiva";
        vive = true;
        posFila = (int)(Math.random() * 10 + 1);
        posCol = numCol();
        figura = fig;
    }
    public Soldado(String nom, int nivAtaq, int nivDef, int nivVida, int nivAct, int vel, String act, boolean vivir, int pFila, char pCol, char fig){
        nombre = nom;
        nivelAtaque = nivAtaq;
        nivelDefensa = nivDef;
        nivelVida = nivVida;
        vidaActual = nivAct;
        velocidad = vel;
        actitud = act;
        vive = vivir;
        posFila = pFila;
        posCol = pCol;
        figura = fig;
    }
    public Soldado(String nom, int nivelAtaque, int nivelDefensa,char fig){
        nombre = nom;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;
        nivelVida = (int)(Math.random() * 5 + 1);
        vidaActual = nivelVida;
        velocidad = 0;
        actitud = "Defensiva";
        vive = true;
        posFila = (int)(Math.random() * 10 + 1);
        posCol = numCol();
        figura = fig;
    }
    public String getNombre(){
        return nombre;
    }
    public int getAtaque(){
        return nivelAtaque;
    }
    public void setAtaque(int ataque){
        nivelAtaque = ataque;
    }
    public int getDefensa(){
        return nivelDefensa;
    }
    public void setDefensa(int defensa){
        nivelDefensa = defensa;
    }
    public void setNivelVida(int vida){
        nivelVida = vida;
        vidaActual = vida;
    }
    public int getNivVidAct(){
        return vidaActual;
    }
    public int getVida(){
        return nivelVida;
    }
    public int getVelocidad(){
        return velocidad;
    }
    public String getActitud(){
        return actitud;
    }
    public boolean getVive(){
        return vive;
    }
    public int getFila(){
        return posFila;
    }
    public char getColumna(){
        return posCol;
    }
    public char getFigura(){
        return figura;
    } 
    public void setFila(int n){
        posFila = n;
    }
    public void setCol(char n){
        posCol = n;
    }
    public void addVida(){
        vidaActual++;
    }
    public void atacar(){
        if(!getActitud().equals("Ofensiva"))
            actitud = "Ofensiva";
        avanzar();
    }
    public void defender(){ 
        if(!getActitud().equals("Defensiva"))
            actitud = "Defensiva";
        velocidad = 0;
    }
    public void serAtacado(){
        vidaActual--;
        if(this.vidaActual<=0){
            morir();
        }
    }
    public void huir(){
        actitud = "Fuga";
        velocidad += 2;
    }
    public void morir(){
        vive = false;
        velocidad = 0;
    }
    public void avanzar(){
        velocidad++;
    }
    public void agregarDefensa(){
        if(nivelDefensa < 5)
            nivelDefensa++;
    }
    public void retroceder(){
        if(this.velocidad > 0){
            velocidad = 0;
            actitud = "Defensiva";
        } else if(this.velocidad == 0){
            velocidad = -1;
        }
    }
    public static char numCol(){
        String a = "ABCDEFGHIJ";
        int n = (int)(Math.random() * a.length());
        char car = a.charAt(n);
        return car;
    }
    public String toString(){
        return "Nombre: " + nombre + " Vida:" + vidaActual + " Fila:" + posFila + " Ataque:" + nivelAtaque + " Defensa:" + nivelDefensa +  " Columna:" + posCol;
    }
}
