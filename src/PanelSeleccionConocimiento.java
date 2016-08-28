

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class PanelSeleccionConocimiento extends JPanel {
    //Etiquetas del panel
    JLabel etiquetaBanner = new JLabel("Bienvenidos");
    JLabel etiquetaInstruccion = new JLabel("Selecciona la base de conocimiento para "
            + "trabajar");
    
    //Radios con las bases de conocimiento
    JRadioButton [] radioBasesConocimiento;
    
    //Texto de la nueva base de conocimiento
    JTextField textoNuevaBaseConocimiento = new JTextField();
    
    //Botón para grear la buena base de conocimiento
    JButton botonNuevaBaseConocimiento = new JButton("Nueva base de conocimiento");
    
    public PanelSeleccionConocimiento(){
        //Propiedades del panel
        setLayout(null);
        
        //Agregamos el etiquetaBanner y la instrucción
        etiquetaBanner.setBounds(310, 10, 180, 50);
        etiquetaBanner.setFont(new Font("Serif", Font.BOLD, 30));
        etiquetaBanner.setVisible(true);
        add(etiquetaBanner);
        
        etiquetaInstruccion.setBounds(10, 60, 350, 30);
        etiquetaInstruccion.setVisible(true);
        add(etiquetaInstruccion);
        
        //Obtenemos los archivos disponible para trabajar
        File dir = new File("Data/Elementos/");
        String[] archivos = dir.list();
        
        //Generamos un arreglo de radios para obtener la base de conocimiento a trabajar
        JRadioButton [] radioBasesConocimientoTemporal = new JRadioButton[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            radioBasesConocimientoTemporal[i] =  new JRadioButton(archivos[i].substring(0, archivos[i].length() - 4));
            radioBasesConocimientoTemporal[i].setBounds(10, (i*25)+100, 200, 25);
            radioBasesConocimientoTemporal[i].addMouseListener(new seleccionBaseConocimiento(i));
            radioBasesConocimientoTemporal[i].setVisible(true);
            add(radioBasesConocimientoTemporal[i]);
            if(i==0){
                radioBasesConocimientoTemporal[0].setSelected(true);
            }
        }
        radioBasesConocimiento = radioBasesConocimientoTemporal;
        
        //Agregamos la sección de nueva base
        textoNuevaBaseConocimiento.setBounds(10, 510, 600, 25);
        textoNuevaBaseConocimiento.setVisible(true);
        add(textoNuevaBaseConocimiento);
        
        botonNuevaBaseConocimiento.setBounds(610, 510, 200, 25);
        botonNuevaBaseConocimiento.setVisible(true);
        botonNuevaBaseConocimiento.addActionListener(new nuevaBaseConocimiento());
        add(botonNuevaBaseConocimiento);
        
   }

    class nuevaBaseConocimiento implements ActionListener {

        public nuevaBaseConocimiento() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("nueva");
            if(!"".equals(textoNuevaBaseConocimiento.getText())){
                //Obtenemos los archivos disponible para trabajar
                File dir = new File("Data/Elementos/");
                String[] archivos = dir.list();
                //Radios temporales más el que le añariremos
                JRadioButton [] radioBasesConocimientoTemporal = new JRadioButton[archivos.length+1];
                for (int i = 0; i < archivos.length; i++) {
                    radioBasesConocimientoTemporal[i] =  new JRadioButton(archivos[i].substring(0, archivos[i].length() - 4));
                    radioBasesConocimientoTemporal[i].setBounds(10, (i*25)+100, 200, 25);
                    radioBasesConocimientoTemporal[i].addMouseListener(new seleccionBaseConocimiento(i));
                    radioBasesConocimientoTemporal[i].setVisible(true);
                    radioBasesConocimientoTemporal[i].setSelected(false);
                    add(radioBasesConocimientoTemporal[i]);
                }
                radioBasesConocimientoTemporal[radioBasesConocimientoTemporal.length-1] =  new JRadioButton(textoNuevaBaseConocimiento.getText());
                radioBasesConocimientoTemporal[radioBasesConocimientoTemporal.length-1].setBounds(10, ((radioBasesConocimientoTemporal.length-1)*25)+100, 200, 25);
                radioBasesConocimientoTemporal[radioBasesConocimientoTemporal.length-1].addMouseListener(new seleccionBaseConocimiento(radioBasesConocimientoTemporal.length-1));
                radioBasesConocimientoTemporal[radioBasesConocimientoTemporal.length-1].setVisible(true);
                add(radioBasesConocimientoTemporal[radioBasesConocimientoTemporal.length-1]);

                radioBasesConocimiento = radioBasesConocimientoTemporal;
                
                new File("Data/Justificación/"+textoNuevaBaseConocimiento.getText()+"").mkdir();
                System.out.println(textoNuevaBaseConocimiento.getText());
                
                textoNuevaBaseConocimiento.setText("");
                Main.win.setBounds(Main.win.getX(), Main.win.getY(), Main.win.getWidth()+1, Main.win.getHeight());
                Main.win.setBounds(Main.win.getX(), Main.win.getY(), Main.win.getWidth()-1, Main.win.getHeight());
            }
        }
    }

    class seleccionBaseConocimiento implements MouseListener {
        int radio;
        public seleccionBaseConocimiento(int i) {
            radio=i;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < radioBasesConocimiento.length; i++) {
                if(i==radio){
                   Main.panelReglas.setBaseConocimiento(radioBasesConocimiento[i].getText());
                   Main.panelJustificacion.actualizarListaElementos(new ListaElementos().leerElemento(radioBasesConocimiento[i].getText()));
                   Main.crearArbol(radioBasesConocimiento[i].getText());
                   radioBasesConocimiento[i].setSelected(true);
                }else{
                    radioBasesConocimiento[i].setSelected(false);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    }
}
