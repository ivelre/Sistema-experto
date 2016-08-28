
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.RandomAccessFile;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelEntradas extends JPanel {
    
    //Base de conocimiento
    String baseConocimiento;
    
    JLabel etiquetaBanner = new JLabel("Entradas");
    JLabel etiquetaInstruccion = new JLabel("Ingrese las entradas antes de continuar con la inferencia");
    
    JCheckBox [] checkElementos = new JCheckBox [0];
    
    //Botón inferir
    JButton botonInferir = new JButton("Inferir");
    
    public PanelEntradas(){
        setLayout(null);
        
        //
        etiquetaBanner.setBounds(310, 10, 180, 50);
        etiquetaBanner.setFont(new Font("Serif", Font.BOLD, 30));
        etiquetaBanner.setVisible(true);
        add(etiquetaBanner);
        
        etiquetaInstruccion.setBounds(200, 60, 350, 30);
        etiquetaInstruccion.setVisible(true);
        add(etiquetaInstruccion);
        
        //Agremamos lo el botón de inferencia
        botonInferir.setBounds(10, 510, 770, 25);
        botonInferir.setVisible(true);
        botonInferir.addActionListener(new iniciarInferencia());
        add(botonInferir);
    }
    
    public void mostrarElementos(Nodo [] arbol){
        for (int i = 0; i < checkElementos.length; i++) {
            try {
                remove(checkElementos[i]);                
            } catch (Exception e) {
            }
        }
        System.out.println("------------------------------------->"+arbol.length);
        JCheckBox [] checkElementosTemporal = new JCheckBox [arbol.length];
        for (int i = 0; i < checkElementosTemporal.length; i++) {
            try {
                checkElementosTemporal[i] = new JCheckBox(arbol[i].nombre);
                checkElementosTemporal[i].setBounds(10, (i*25)+80, 250, 25);
                checkElementosTemporal[i].setVisible(true);
                checkElementosTemporal[i].addMouseListener(new actualizarBaseHechos());
                add(checkElementosTemporal[i]);
            } catch (Exception e) {
                //System.out.println(""+e.getMessage());
            }
        }
        checkElementos=checkElementosTemporal;
    }

    class iniciarInferencia implements ActionListener {

        public iniciarInferencia() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.motorInferencia.nuevaInferencia(Main.baseConocimiento);
        }
    }

    class actualizarBaseHechos implements MouseListener {

        public actualizarBaseHechos() {
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            try {
                File fichero = new File("Data/Base de hechos/"+Main.baseConocimiento+".lqs");
                fichero.delete();
                RandomAccessFile baseHechos=new RandomAccessFile("Data/Base de hechos/"+Main.baseConocimiento+".lqs", "rw");
                for (int i = 0; i < checkElementos.length; i++) {
                    if(checkElementos[i].isSelected()){
                        baseHechos.writeUTF(checkElementos[i].getText());
                        System.out.println("Guardó "+ checkElementos[i].getText());
                    }
                } 
                baseHechos.seek(0);
                while((baseHechos.getFilePointer())!=(baseHechos.length())){
                    System.out.println("Leyó del archivo "+baseHechos.readUTF());
                }
                baseHechos.close();
            } catch (Exception e) {
            }
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
    }
    
}
