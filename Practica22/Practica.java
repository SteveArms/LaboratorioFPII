import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Practica{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ejercito actual, enemigo;
        String nombre;
        System.out.println("Ingrese el nombre del 1er Reino");
        nombre = ingresarNombre();
        actual = new Ejercito(nombre, '*', 1);
        System.out.println("Ingrese el nombre del 2do reino");
        nombre = ingresarNombre();
        enemigo = new Ejercito(nombre, '%', 2);
        Mapa tablero = new Mapa(aleatorioMapa());
        tablero.comprobarValores(actual.getSoldados());
        tablero.comprobarValores(enemigo.getSoldados());

        //Implementamos los objetos que pueden ser usados junto al JFrame tanto como botones y label
        JFrame ventanaBienvenida = new JFrame("Bienvenido al Juego");
        ventanaBienvenida.setSize(300, 150);
        ventanaBienvenida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Instanciamos todos los objetos que complementaran al JFrame
        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        JLabel mensajeLabel = new JLabel("--------------- Menu de Opciones --------------- ");
        JButton boton1ra = new JButton(" 1ra Opcion ");
        JButton boton2da = new JButton(" 2da Opcion");
        JButton boton3ro = new JButton(" 3ra Opcion ");
        JButton boton4to = new JButton(" 4ta Opcion ");
        JButton boton5to = new JButton(" 5ta Opcion ");
        JButton botonFinalizar = new JButton(" Finalizar ");
        //Uso de metodos de instancia en los botones
        boton1ra.setBackground(Color.GREEN);
        boton2da.setBackground(Color.RED);
        boton3ro.setBackground(Color.GRAY);
        boton4to.setBackground(Color.MAGENTA);
        boton5to.setBackground(Color.CYAN);
        botonFinalizar.setBackground(Color.BLUE);

        panelPrincipal.add(mensajeLabel);
        panelPrincipal.add(boton1ra);
        panelPrincipal.add(boton2da);
        panelPrincipal.add(boton3ro);
        panelPrincipal.add(boton4to);
        panelPrincipal.add(boton5to);
        panelPrincipal.add(botonFinalizar);
        boton1ra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {             
                tablero.mapaSoldados(actual.getSoldados(), enemigo.getSoldados());
            }
        });
        boton2da.addActionListener(new ActionListener() {              
            public void actionPerformed(ActionEvent e) {
                System.out.println("***********************************");
                actual.promedioVida();
                enemigo.promedioVida();
                System.out.println("***********************************");
            }
        });
        boton3ro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("***********************************");
                actual.soldadosMayorVida();
                enemigo.soldadosMayorVida();
                System.out.println("***********************************");
            }
        });
        boton4to.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("***********************************");
                actual.imprimir();
                System.out.println("***********************************");
                enemigo.imprimir();
                System.out.println("***********************************");
            }
        });
        boton5to.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, resumen(actual, enemigo));
            }
        });
        botonFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ventanaBienvenida.dispose();
                JOptionPane.showMessageDialog(null, "Programa Finalizado");
            }
        });
        ventanaBienvenida.add(panelPrincipal);
        ventanaBienvenida.setLocationRelativeTo(null); 
        ventanaBienvenida.setVisible(true);      
    }
    public static String ingresarNombre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del ejercito solo validos :");
        System.out.println("Inglaterra - Francia - Castilla o Aragon - Sacro - Moros");
        String nombre = sc.next().toUpperCase();
        while(!validarNombre(nombre)){
            System.out.println("Reino incorrecto ingrese otro");
            nombre = sc.next().toUpperCase();
        }
        return nombre;
    }
    public static boolean validarNombre(String nombre){
        return nombre.equals("INGLATERRA") || nombre.equals("FRANCIA") || nombre.equals("CASTILLA") || nombre.equals("ARAGON") || nombre.equals("SACRO") || nombre.equals("MOROS");
    }
    public static void imprimirTablero(char[][] tablero){
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero[0].length; j++){
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Resumen que cuenta con indicacion del reino, cantidad de unidades, distribucion por unidades, nivel de vida total y porcentajes mas ganador.
    public static String resumen(Ejercito actual, Ejercito enemigo){
        return "Ejercito 1: " + actual.getReino() + "\n" + 
        "Cantidad de Soldados creados: " + actual.cantidadSoldados() + "\n"  + 
        "Espadachines : " + actual.getEspadachin() + "\n"  +
        "Arqueros : " + actual.getArquero() + "\n"  +
        "Caballeros : " + actual.getCaballero() + "\n" + 
        "Lanceros : " + actual.getLancero() + "\n\n" +
        "Ejercito 2: " + enemigo.getReino() + "\n" + 
        "Cantidad de Soldados creados: " + enemigo.cantidadSoldados() + "\n"  + 
        "Espadachines : " + enemigo.getEspadachin() + "\n"  +
        "Arqueros : " + enemigo.getArquero() + "\n"  +
        "Caballeros : " + enemigo.getCaballero() + "\n" + 
        "Lanceros : " + enemigo.getLancero() + "\n\n" ;
    }
    //Metodo para definir el entorno del territorio
    public static String aleatorioMapa(){
        int rand = (int)(Math.random() * 5 + 1);
        switch(rand){
            case 1:
                return "BOSQUE";
            case 2:
                return "CAMPO ABIERTO";
            case 3:
                return "MONTANIA";
            case 4:
                return "DESIERTO";
            case 5:
                return "PLAYA";
            default:
                return " ";
        }
    }
    public static void datosSoldado(Ejercito actual, char figura){
        String nombre = ingresarNombre();

    }
    public static void impTablero(char[][] tablero){
        System.out.println("El tablero es :");
        for(int x = 0; x < tablero.length; x++){
            for(int y = 0; y < tablero[x].length; y++){
                System.out.print(tablero[x][y]);
            }
            System.out.println();
        }
    }
}