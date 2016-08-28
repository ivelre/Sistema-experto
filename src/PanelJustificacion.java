
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


public class PanelJustificacion extends JPanel{
    //Etiquetas del panel
    JLabel etiquetaBanner = new JLabel("Justificación");
    JLabel etiquetaInstruccion = new JLabel("Seleccione un elemento para contruir su justificación.");
    JLabel etiquetaElementos = new JLabel("Elementos");
    JLabel etiquetaTituloTexto = new JLabel("Justificación para el elemento ");
    
    
    //Páneles que trabajo
    JPanel panelElementos = new JPanel();
    
    //Scrolls que controlarán los páneles
    JScrollBar scrollElementos = new JScrollBar();
    
    //Lista de elementos
    JLabel [] etiquetasElemenots = new JLabel[1];
    
    //Lista de elementos
    String [] listaElementos;
    
    //area de texto para la edición de la justificación
    JTextArea textAreaJustificaion = new JTextArea(16, 50);
    
    //Elemento que se está editando
    String elemento;
    
    // set textArea non-editable
    JScrollPane PanelTextoJustificacion;
    public PanelJustificacion(){
        //Propiedades del panel
        setLayout(null);
        
        //Agregamos las etiquetas
        etiquetaBanner.setBounds(325, 0, 350, 40);
        etiquetaBanner.setFont(new Font("Serif", Font.BOLD, 30));
        etiquetaBanner.setVisible(true);
        add(etiquetaBanner);
        
        etiquetaInstruccion.setBounds(200, 40, 350, 30);
        etiquetaInstruccion.setVisible(true);
        add(etiquetaInstruccion);
        
        etiquetaElementos.setBounds(10, 60, 80, 30);
        etiquetaElementos.setVisible(true);
        add(etiquetaElementos);
        
        etiquetaTituloTexto.setBounds(165, 60, 550, 30);
        etiquetaTituloTexto.setVisible(true);
        add(etiquetaTituloTexto);
        
        
        
        //Añadimos los páneles de trabajo
        panelElementos.setLayout(null);
        panelElementos.setBounds(10, 90, 125, 435);
        panelElementos.setBorder(BorderFactory.createLineBorder(Color.black));
        panelElementos.setVisible(true);
        add(panelElementos);
        
        
        //Agregamos los scrolls
        scrollElementos.setBounds(135, 90, 25, 435);
        scrollElementos.setVisible(true);
        scrollElementos.setMaximum(1);
        scrollElementos.setMinimum(0);
        add(scrollElementos);
        
        textAreaJustificaion.setBounds(0, 0, 605, 435);
        textAreaJustificaion.setVisible(true);
        textAreaJustificaion.addKeyListener(new guardarJustificacion());
        
        PanelTextoJustificacion = new JScrollPane(textAreaJustificaion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        PanelTextoJustificacion.setBounds(165, 90, 605, 435);
        PanelTextoJustificacion.setVisible(true);

        //agregamos el panel y el texto
        add(PanelTextoJustificacion);
    }
    
    public void actualizarListaElementos(String [] listaElementos){
        //Asignamos la nueva lista a la general
        this.listaElementos = listaElementos;
        //Eliminamos la lita anerior
        panelElementos.removeAll();
        //Cargamos una lista de elementos temporal
        JLabel []etiquetasElemenotsTemporal=new JLabel[listaElementos.length+1];
        
        //Justificación default
        etiquetasElemenotsTemporal[0] = new JLabel("default",SwingConstants.CENTER);
        etiquetasElemenotsTemporal[0].setBounds(0, 0, panelElementos.getWidth(), 20);
        etiquetasElemenotsTemporal[0].setBorder(BorderFactory.createLineBorder(Color.black));
        etiquetasElemenotsTemporal[0].addMouseListener(new editorJustificacion("default"));
        panelElementos.add(etiquetasElemenotsTemporal[0]);

        //Le asignamos la lista de elementos a las etiquetas
        for (int i = 1; i < listaElementos.length; i++) {
            etiquetasElemenotsTemporal[i] = new JLabel(listaElementos[i],SwingConstants.CENTER);
            //Asignamos los elementos al panel
            etiquetasElemenotsTemporal[i].setBounds(0, ((i)*20)-(i), panelElementos.getWidth(), 20);
            etiquetasElemenotsTemporal[i].setBorder(BorderFactory.createLineBorder(Color.black));
            etiquetasElemenotsTemporal[i].addMouseListener(new editorJustificacion(listaElementos[i]));
            panelElementos.add(etiquetasElemenotsTemporal[i]);
        }
        
        //Asigamos las etiquetas temporales a las generales
        etiquetasElemenots = etiquetasElemenotsTemporal;
        
        //Agregamos la instrucción para la justificación para el primere elemento
        try {
            etiquetaTituloTexto.setText("Justificación para el elemento " + listaElementos[1]);
            elemento = listaElementos[1];
            leerJustificacion();
        } catch (Exception e) {
        }
        
        panelElementos.repaint();        
    }
    
    //Intentamos leer la justufucación
    public void leerJustificacion(){
        textAreaJustificaion.setText("");
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(new File("Data/Justificación/"+Main.baseConocimiento+"/"+elemento+".txt")));
            String justificion = "", linea;
            boolean unaLinea = true;
            while((linea = archivo.readLine())!=null) {
                if(unaLinea){
                    textAreaJustificaion.setText(linea);
                    unaLinea = false;
                }else{
                    textAreaJustificaion.setText(textAreaJustificaion.getText() + "\n" + linea);
                }
            }
            archivo.close();
        } catch (Exception e) {
            
        }
    }

    class guardarJustificacion implements KeyListener {

        public guardarJustificacion() {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(textAreaJustificaion.getText().equals("")){
                // Si el campo de justificación está vacio, borra el archivo porque no hay necesidad de tener uno sin justificación
                new File("Data/Justificación/"+Main.baseConocimiento+"/"+elemento+".txt").delete();
            }else{
                try {
                    PrintWriter archivo = new PrintWriter(new FileWriter("Data/Justificación/"+Main.baseConocimiento+"/"+elemento+".txt"));
                    archivo.println(textAreaJustificaion.getText());
                    System.out.println(""+textAreaJustificaion.getText());
                    archivo.close();

                } catch (Exception ex) {
                    System.out.println(""+ex.getMessage());
                }
            }
        }
    }

    class editorJustificacion implements MouseListener{
        String Elemento;
        public editorJustificacion(String elemento) {
            this.Elemento = elemento;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            etiquetaTituloTexto.setText("Justificación para el elemento " + Elemento);
            elemento = Elemento;
            leerJustificacion();
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
