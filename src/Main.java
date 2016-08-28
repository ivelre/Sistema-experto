
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main{
    //Frame principal
    public static JFrame win = new JFrame();
    //Páneles de interface
    PanelSeleccionConocimiento panelSeleccionConocimiento = new PanelSeleccionConocimiento();
    public static PanelEntradas panelEntradas = new PanelEntradas();
    public static PanelReglas panelReglas =  new PanelReglas();
    PanelAcercaDe panelAcercaDe = new PanelAcercaDe();
    public static PanelJustificacion panelJustificacion = new PanelJustificacion();
    
    //Botones de movilidad
    JButton botonPanelSeleccionConocimiento = new JButton("Seleccionar Base de conocimiento.");
    JButton botonPanelEntradas = new JButton("Entradas");
    JButton botonPanelReglas = new JButton("Reglas");
    JButton botonPanelAcercaDe = new JButton("Acerca de");
    JButton botonPanelJustificacion = new JButton("Justificación");
    
    //Árbol a usar
    public static Arbol arbol = new Arbol();
    
    //Número Reglas
    public static int numeroReglas = 0;
    
    //Cargamos el motor de inferencia
    public static MotorInferencia motorInferencia = new MotorInferencia();
    //Base de conocimiento
    public static String baseConocimiento="";
    
    public Main(){
        //Tomamos la primero base de conocimiento en la lsita
        File dir = new File("Data/Elementos/");
        String[] archivos = dir.list();
        baseConocimiento=archivos[0].substring(0, archivos[0].length() - 4);
        //Propiedades del frame
        win.setLayout(null);
        win.setTitle("Los que siguen");
        
        //Añadimos los botones de movilidad al frame
        botonPanelSeleccionConocimiento.setBounds(0, 0, 250, 25);
        botonPanelSeleccionConocimiento.setVisible(true);
        botonPanelSeleccionConocimiento.addActionListener(new clicMenu(1));
        win.add(botonPanelSeleccionConocimiento);
        
        botonPanelEntradas.setBounds(250, 0, 100, 25);
        botonPanelEntradas.setVisible(true);
        botonPanelEntradas.addActionListener(new clicMenu(2));
        win.add(botonPanelEntradas);
        
        botonPanelReglas.setBounds(350, 0, 100, 25);
        botonPanelReglas.setVisible(true);
        botonPanelReglas.addActionListener(new clicMenu(3));
        win.add(botonPanelReglas);
        
        botonPanelJustificacion.setBounds(450, 0, 150, 25);
        botonPanelJustificacion.setVisible(true);
        botonPanelJustificacion.addActionListener(new clicMenu(5));
        win.add(botonPanelJustificacion);
        
        botonPanelAcercaDe.setBounds(600, 0, 100, 25);
        botonPanelAcercaDe.setVisible(true);
        botonPanelAcercaDe.addActionListener(new clicMenu(4));
        win.add(botonPanelAcercaDe);
        
        //Añadimos los páneles
        panelSeleccionConocimiento.setBounds(0, 25, 800, 575);
        panelSeleccionConocimiento.setVisible(true);
        win.add(panelSeleccionConocimiento);
        
        panelEntradas.setBounds(0, 25, 800, 575);
        panelEntradas.setVisible(false);
        win.add(panelEntradas);
        
        panelReglas.setBounds(0, 25, 800, 575);
        panelReglas.setVisible(false);
        panelReglas.setBaseConocimiento(baseConocimiento);
        win.add(panelReglas);
        
        panelJustificacion.setBounds(0, 25, 800, 575);
        panelJustificacion.setVisible(false);
        panelJustificacion.actualizarListaElementos(new ListaElementos().leerElemento(baseConocimiento));
        win.add(panelJustificacion);
        
        panelAcercaDe.setBounds(0, 25, 800, 575);
        panelAcercaDe.setVisible(false);
        win.add(panelAcercaDe);
        
        //propiedades del frame
        win.setBounds(0, 0, 810, 600);
        win.setVisible(true);
        win.setDefaultCloseOperation(win.EXIT_ON_CLOSE);
        win.addComponentListener(new resize());
        
        crearArbol(baseConocimiento);
        
        win.addWindowListener(new eventosVentana());
        
        //Icono de la aplicación
        Image icono = Toolkit.getDefaultToolkit().getImage("Data/Imágenes/Icon.png");
        win.setIconImage(icono); 
        
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public static void crearArbol(String bc){
        baseConocimiento = bc;
        arbol.crearArbol(bc);
        
        Nodo [] arbolito = arbol.getArbol();
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < arbolito.length; i++) {
            System.out.print("El nodo "+arbolito[i].nombre+" tiene las reglas de salida ");
            for (int j = 0; j < arbolito[i].salidas.length; j++) {
                System.out.print((arbolito[i].salidas[j]+1)+",");
            }
            System.out.print(" y las reglas de llegada ");
            for (int j = 0; j < arbolito[i].llegadas.length; j++) {
                System.out.print((arbolito[i].llegadas[j]+1)+",");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------------------------------");
        
        panelEntradas.mostrarElementos(arbol.getNoTerminaler());
        motorInferencia.setArbol(arbol.getArbol());
        
    }

    class eventosVentana implements WindowListener {

        public eventosVentana() {
        }

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            //Borramos la base de hechos
            File fichero = new File("Data/Base de hechos/"+Main.baseConocimiento+".lqs");
            fichero.delete();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
    }
    

    class resize implements ComponentListener {

        public resize() {
        }

        @Override
        public void componentResized(ComponentEvent e) {
            panelReglas.resize(win.getWidth(),win.getHeight());
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            
        }

        @Override
        public void componentShown(ComponentEvent e) {
            
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            
        }
    }

    class clicMenu implements ActionListener {
        int numeroBoton=0;
        public clicMenu(int numeroBoton) {
            this.numeroBoton=numeroBoton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Indicamos la acción que realizará cada boton
            switch(numeroBoton){
                case 1://Hacemos visible el panel de selecección de conocimiento y ocultamos el resto
                    panelSeleccionConocimiento.setVisible(true);
                    panelEntradas.setVisible(false);
                    panelReglas.setVisible(false);
                    panelAcercaDe.setVisible(false);
                    panelJustificacion.setVisible(false);
                    break;
                case 2://Hacemos visible el panel de entradas y ocultamos el resto
                    panelSeleccionConocimiento.setVisible(false);
                    panelEntradas.setVisible(true);
                    panelReglas.setVisible(false);
                    panelAcercaDe.setVisible(false);
                    panelJustificacion.setVisible(false);
                    break;
                case 3://Hacemos visible el panel de reglas y ocultamos el resto
                    panelSeleccionConocimiento.setVisible(false);
                    panelEntradas.setVisible(false);
                    panelReglas.setVisible(true);
                    panelAcercaDe.setVisible(false);
                    panelJustificacion.setVisible(false);
                    break;
                case 4://Hacemos visible el panel de acerca de y ocultamos el resto
                    panelSeleccionConocimiento.setVisible(false);
                    panelEntradas.setVisible(false);
                    panelReglas.setVisible(false);
                    panelAcercaDe.setVisible(true);
                    panelJustificacion.setVisible(false);
                    break;    
                case 5://Hacemos visible el panel de acerca de y ocultamos el resto
                    panelSeleccionConocimiento.setVisible(false);
                    panelEntradas.setVisible(false);
                    panelReglas.setVisible(false);
                    panelAcercaDe.setVisible(false);
                    panelJustificacion.setVisible(true);
                    break;    
            }
        }
    }
}
