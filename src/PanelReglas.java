
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;


public class PanelReglas extends JPanel {
    //Etiquetas del panel
    JLabel etiquetaBanner = new JLabel("Contrucción de las reglas");
    JLabel etiquetaElementos = new JLabel("Elementos");
    JLabel etiquetaAntecedente_1 = new JLabel("Antecedente 1");
    JLabel etiquetaAntecedente_2 = new JLabel("Antecedente 2");
    JLabel etiquetaAntecedente_3 = new JLabel("Antecedente 3");
    JLabel etiquetaAntecedente_4 = new JLabel("Antecedente 4");
    JLabel etiquetaAntecedente_5 = new JLabel("Antecedente 5");
    JLabel etiquetaConsecuente = new JLabel("Consecuente");
    
    //Botones a usar
    JButton botonNuevaRegla = new JButton("Nueva regla");
    
    //Páneles que trabajo
    JPanel panelElementos = new JPanel();
    JPanel panelReglas = new JPanel();
    
    //Scrolls que controlarán los páneles
    JScrollBar scrollElementos = new JScrollBar();
    JScrollBar scrollReglas = new JScrollBar();
    
    //Lista de elementos
    JLabel [] etiquetasElemenots = new JLabel[1];
    
    //Conjunto de antecedentes y consecuentes
    JComboBox [] combosAntecedentes_1 = new JComboBox[1];
    JComboBox [] combosAntecedentes_2 = new JComboBox[1];
    JComboBox [] combosAntecedentes_3 = new JComboBox[1];
    JComboBox [] combosAntecedentes_4 = new JComboBox[1];
    JComboBox [] combosAntecedentes_5 = new JComboBox[1];
    JComboBox [] combosConsecuentes = new JComboBox[1];
    
    //Lista de elementos
    String [] listaElementos;
    //Base de conocimiento
    String baseConocimiento;
    
    public PanelReglas(){
        //Propiedades del panel
        setLayout(null);
        
        //Agregamos las etiquetas
        etiquetaBanner.setBounds(225, 0, 350, 40);
        etiquetaBanner.setFont(new Font("Serif", Font.BOLD, 30));
        etiquetaBanner.setVisible(true);
        add(etiquetaBanner);
        
        etiquetaElementos.setBounds(10, 60, 80, 30);
        etiquetaElementos.setVisible(true);
        add(etiquetaElementos);
        
        etiquetaAntecedente_1.setBounds(165, 60, 100, 30);
        etiquetaAntecedente_1.setVisible(true);
        add(etiquetaAntecedente_1);
        
        etiquetaAntecedente_2.setBounds(265, 60, 100, 30);
        etiquetaAntecedente_2.setVisible(true);
        add(etiquetaAntecedente_2);
        
        etiquetaAntecedente_3.setBounds(365, 60, 100, 30);
        etiquetaAntecedente_3.setVisible(true);
        add(etiquetaAntecedente_3);
        
        etiquetaAntecedente_4.setBounds(465, 60, 100, 30);
        etiquetaAntecedente_4.setVisible(true);
        add(etiquetaAntecedente_4);
        
        etiquetaAntecedente_5.setBounds(565, 60, 100, 30);
        etiquetaAntecedente_5.setVisible(true);
        add(etiquetaAntecedente_5);
        
        etiquetaConsecuente.setBounds(665, 60, 100, 30);
        etiquetaConsecuente.setVisible(true);
        add(etiquetaConsecuente);
        
        //Agregamos los botos
        botonNuevaRegla.setBounds(165, 45, 120, 20);
        botonNuevaRegla.setVisible(true);
        botonNuevaRegla.addActionListener(new accionesReglas(1));
        add(botonNuevaRegla);
        
        //Añadimos los páneles de trabajo
        panelElementos.setLayout(null);
        panelElementos.setBounds(10, 90, 125, 435);
        panelElementos.setBorder(BorderFactory.createLineBorder(Color.black));
        panelElementos.setVisible(true);
        add(panelElementos);
        
        panelReglas.setLayout(null);
        panelReglas.setBounds(165, 90, 605, 435);
        panelReglas.setBorder(BorderFactory.createLineBorder(Color.black));
        panelReglas.setVisible(true);
        add(panelReglas);
        
        //Agregamos los scrolls
        scrollElementos.setBounds(135, 90, 25, 435);
        scrollElementos.setVisible(true);
        scrollElementos.setMaximum(1);
        scrollElementos.setMinimum(0);
        add(scrollElementos);
        
        scrollReglas.setBounds(770, 90, 25, 435);
        scrollReglas.setVisible(true);
        scrollReglas.setMaximum(1);
        scrollReglas.setMinimum(0);
        add(scrollReglas);
        
        //Agregamos la lista de lo elementos
        etiquetasElemenots[0] = new JLabel("Nueva regla",SwingConstants.CENTER);
        etiquetasElemenots[0].setBounds(0, 0, 125, 20);
        etiquetasElemenots[0].setBorder(BorderFactory.createLineBorder(Color.black));
        etiquetasElemenots[0].addMouseListener(new nuevoElemento());
        etiquetasElemenots[0].setVisible(true);
        panelElementos.add(etiquetasElemenots[0]);
        
        reglaInicial();
    }
    
    //Función para agregar regla inicial
    public void reglaInicial(){
        //Agregamos los combos de antecedentes y el del consecuente
        combosAntecedentes_1[0] = new JComboBox();
        combosAntecedentes_1[0].setBounds(0, 0, 100, 25);
        combosAntecedentes_1[0].setVisible(true);
        combosAntecedentes_1[0].addActionListener(new actualizarConocimiento(1,1));
        panelReglas.add(combosAntecedentes_1[0]);
        
        combosAntecedentes_2[0] = new JComboBox();
        combosAntecedentes_2[0].setBounds(100, 0, 100, 25);
        combosAntecedentes_2[0].setVisible(true);
        combosAntecedentes_2[0].addActionListener(new actualizarConocimiento(1,2));
        panelReglas.add(combosAntecedentes_2[0]);
        
        combosAntecedentes_3[0] = new JComboBox();
        combosAntecedentes_3[0].setBounds(200, 0, 100, 25);
        combosAntecedentes_3[0].setVisible(true);
        combosAntecedentes_3[0].addActionListener(new actualizarConocimiento(1,3));
        panelReglas.add(combosAntecedentes_3[0]);
        
        combosAntecedentes_4[0] = new JComboBox();
        combosAntecedentes_4[0].setBounds(300, 0, 100, 25);
        combosAntecedentes_4[0].setVisible(true);
        combosAntecedentes_4[0].addActionListener(new actualizarConocimiento(1,4));
        panelReglas.add(combosAntecedentes_4[0]);
        
        combosAntecedentes_5[0] = new JComboBox();
        combosAntecedentes_5[0].setBounds(400, 0, 100, 25);
        combosAntecedentes_5[0].setVisible(true);
        combosAntecedentes_5[0].addActionListener(new actualizarConocimiento(1,5));
        panelReglas.add(combosAntecedentes_5[0]);
        
        combosConsecuentes[0] = new JComboBox();
        combosConsecuentes[0].setBounds(500, 0, 100, 25);
        combosConsecuentes[0].setVisible(true);
        combosConsecuentes[0].addActionListener(new actualizarConocimiento(1,6));
        panelReglas.add(combosConsecuentes[0]);
    }
    
    public void resize(int x, int y){
        //Cambiamos el tamaño del panel
        setBounds(0,25,x, y-25);
        
        //Cambiamos posicion del banner
        etiquetaBanner.setBounds((x-350)/2, 10, 350, 40);
        
        //Cambiamos la posición el resto de las etiqueras
        etiquetaAntecedente_1.setBounds(((x*165)/800), 60, ((x*100)/800), 30);
        etiquetaAntecedente_2.setBounds(((x*265)/800), 60, ((x*100)/800), 30);
        etiquetaAntecedente_3.setBounds(((x*365)/800), 60, ((x*100)/800), 30);
        etiquetaAntecedente_4.setBounds(((x*465)/800), 60, ((x*100)/800), 30);
        etiquetaAntecedente_5.setBounds(((x*565)/800), 60, ((x*100)/800), 30);
        etiquetaConsecuente.setBounds(((x*665)/800), 60, ((x*100)/800), 30);
        
        //Ajustamos el tamaño de los botones
        botonNuevaRegla.setBounds(((x*125)/800)+35, 45, 120, 20);
        
        //Cambiamos el tamaño de los paneles
        panelElementos.setBounds(10, 90, ((x*125)/800), y-158);
        panelReglas.setBounds(((x*125)/800)+35, 90, ((x*605)/800), y-158);
        
        //Cambiamo la posición de los scrolls
        scrollElementos.setBounds(((x*125)/800)+10, 90, 25, y-158);
        scrollReglas.setBounds((((x*125)/800)+35)+((x*605)/800), 90, 25, y-158);
        
        //Cambiamos las dimenciones de la lista de elementos :D
        for (int i = 0; i < etiquetasElemenots.length-2; i++) {
            etiquetasElemenots[i].setBounds(etiquetasElemenots[i].getX(), 
                                            etiquetasElemenots[i].getY(), 
                                            panelElementos.getWidth(), 
                                            20);
        }
        
        //Cambiamos las dimenciones de los combos
        for (int i = 0; i < combosAntecedentes_1.length; i++) {
            combosAntecedentes_1[i].setBounds(combosAntecedentes_1[i].getX(), 
                                            combosAntecedentes_1[i].getY(), 
                                            ((x*100)/800), 
                                            25);
            combosAntecedentes_2[i].setBounds(combosAntecedentes_1[i].getX()+((x*100)/800), 
                                            combosAntecedentes_2[i].getY(), 
                                            ((x*100)/800), 
                                            25);
            combosAntecedentes_3[i].setBounds(combosAntecedentes_2[i].getX()+((x*100)/800), 
                                            combosAntecedentes_3[i].getY(), 
                                            ((x*100)/800), 
                                            25);
            combosAntecedentes_4[i].setBounds(combosAntecedentes_3[i].getX()+((x*100)/800), 
                                            combosAntecedentes_4[i].getY(), 
                                            ((x*100)/800), 
                                            25);
            combosAntecedentes_5[i].setBounds(combosAntecedentes_4[i].getX()+((x*100)/800), 
                                            combosAntecedentes_5[i].getY(), 
                                            ((x*100)/800), 
                                            25);
            combosConsecuentes[i].setBounds(combosAntecedentes_5[i].getX()+((x*100)/800), 
                                            combosConsecuentes[i].getY(), 
                                            ((x*100)/800), 
                                            25);
        }
    }
    
    public void setBaseConocimiento(String bc){
        baseConocimiento=bc;
        panelReglas.removeAll();
        int widht = combosAntecedentes_1[0].getWidth();
        int height = combosAntecedentes_1[0].getHeight();
        //Genermamos combos temporales para re inicilializar las reglas
        JComboBox [] comboAntecedentesTemporal_1 = new JComboBox[1];
        JComboBox [] comboAntecedentesTemporal_2 = new JComboBox[1];
        JComboBox [] comboAntecedentesTemporal_3 = new JComboBox[1];
        JComboBox [] comboAntecedentesTemporal_4 = new JComboBox[1];
        JComboBox [] comboAntecedentesTemporal_5 = new JComboBox[1];
        JComboBox [] comboConsecuentesTemporal = new JComboBox[1];
        
        comboAntecedentesTemporal_1[0] = new JComboBox();
        comboAntecedentesTemporal_2[0] = new JComboBox();
        comboAntecedentesTemporal_3[0] = new JComboBox();
        comboAntecedentesTemporal_4[0] = new JComboBox();
        comboAntecedentesTemporal_5[0] = new JComboBox();
        comboConsecuentesTemporal[0] = new JComboBox();
        
        combosAntecedentes_1 = comboAntecedentesTemporal_1;
        combosAntecedentes_2 = comboAntecedentesTemporal_2;
        combosAntecedentes_3 = comboAntecedentesTemporal_3;
        combosAntecedentes_4 = comboAntecedentesTemporal_4;
        combosAntecedentes_5 = comboAntecedentesTemporal_5;
        combosConsecuentes = comboConsecuentesTemporal;
        
        reglaInicial();
        
        try {
            actualizarListaElementos(new ListaElementos().leerElemento(baseConocimiento));
        } catch (Exception e) {
            if(listaElementos.length==0){
                etiquetasElemenots[0] = new JLabel("Nuevo",SwingConstants.CENTER);
                etiquetasElemenots[0].setBounds(0, 0, 125, 20);
                etiquetasElemenots[0].setBorder(BorderFactory.createLineBorder(Color.black));
                etiquetasElemenots[0].addMouseListener(new nuevoElemento());
                etiquetasElemenots[0].setVisible(true);
                panelElementos.add(etiquetasElemenots[0]);
            }
        }
        
        
        String [][]Reglas = new ManipulacionBaseConocimiento().obtenerReglas(baseConocimiento);
        combosAntecedentes_1[0].setSelectedItem(Reglas[0][0]);
        combosAntecedentes_2[0].setSelectedItem(Reglas[0][1]);
        combosAntecedentes_3[0].setSelectedItem(Reglas[0][2]);
        combosAntecedentes_4[0].setSelectedItem(Reglas[0][3]);
        combosAntecedentes_5[0].setSelectedItem(Reglas[0][4]);
        combosConsecuentes[0].setSelectedItem(Reglas[0][5]);
        
        for (int i = 1; i < Reglas.length; i++) {
            if(i>=combosAntecedentes_1.length)
                nuevaRegla();
            combosAntecedentes_1[i].setSelectedItem(Reglas[i][0]);
            combosAntecedentes_2[i].setSelectedItem(Reglas[i][1]);
            combosAntecedentes_3[i].setSelectedItem(Reglas[i][2]);
            combosAntecedentes_4[i].setSelectedItem(Reglas[i][3]);
            combosAntecedentes_5[i].setSelectedItem(Reglas[i][4]);
            combosConsecuentes[i].setSelectedItem(Reglas[i][5]);
        }
        
        for (int i = 1; i < Reglas.length; i++) {
            if(i>=combosAntecedentes_1.length)
                nuevaRegla();
            combosAntecedentes_1[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_1[i].setVisible(true);
            combosAntecedentes_1[i].addActionListener(new actualizarConocimiento(i+1,1));
            panelReglas.add(combosAntecedentes_1[i]);
            combosAntecedentes_2[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_2[i].setVisible(true);
            combosAntecedentes_2[i].addActionListener(new actualizarConocimiento(i+1,2));
            panelReglas.add(combosAntecedentes_2[i]);
            combosAntecedentes_3[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_3[i].setVisible(true);
            combosAntecedentes_3[i].addActionListener(new actualizarConocimiento(i+1,3));
            panelReglas.add(combosAntecedentes_3[i]);
            combosAntecedentes_4[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_4[i].setVisible(true);
            combosAntecedentes_4[i].addActionListener(new actualizarConocimiento(i+1,4));
            panelReglas.add(combosAntecedentes_4[i]);
            combosAntecedentes_5[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_5[i].setVisible(true);
            combosAntecedentes_5[i].addActionListener(new actualizarConocimiento(i+1,5));
            panelReglas.add(combosAntecedentes_5[i]);
            combosConsecuentes[i].setBounds(0, i*25, widht, height);
            combosConsecuentes[i].setVisible(true);
            combosConsecuentes[i].addActionListener(new actualizarConocimiento(i+1,6));
            panelReglas.add(combosConsecuentes[i]);
        }
        
    }
    
    public void actualizarListaElementos(String [] listaElementos){
        //Asignamos la nueva lista a la general
        this.listaElementos = listaElementos;
        //Eliminamos la lita anerior
        panelElementos.removeAll();
        //Cargamos una lista de elementos temporal
        JLabel []etiquetasElemenotsTemporal=new JLabel[listaElementos.length+2];
        
        //Le asignamos la lista de elementos a las etiquetas
        for (int i = 1; i < listaElementos.length; i++) {
            etiquetasElemenotsTemporal[i-1] = new JLabel(listaElementos[i],SwingConstants.CENTER);
            //Asignamos los elementos al panel
            etiquetasElemenotsTemporal[i-1].setBounds(0, ((i-1)*20)-(i-1), panelElementos.getWidth(), 20);
            etiquetasElemenotsTemporal[i-1].setBorder(BorderFactory.createLineBorder(Color.black));
            panelElementos.add(etiquetasElemenotsTemporal[i-1]);
        }
        
        etiquetasElemenotsTemporal[listaElementos.length-1] = new JLabel("Nuevo",SwingConstants.CENTER);
        etiquetasElemenotsTemporal[listaElementos.length-1].setBounds(0, ((listaElementos.length-1)*20)-(listaElementos.length-1), panelElementos.getWidth(), 20);
        etiquetasElemenotsTemporal[listaElementos.length-1].setBorder(BorderFactory.createLineBorder(Color.black));
        etiquetasElemenotsTemporal[listaElementos.length-1].addMouseListener(new nuevoElemento());
        panelElementos.add(etiquetasElemenotsTemporal[listaElementos.length-1]);
        
        //Asigamos las etiquetas temporales a las generales
        etiquetasElemenots = etiquetasElemenotsTemporal;
        
        panelElementos.repaint();
        
        //Actualizamos el combo con los elementos
        for (int i = 0; i < combosAntecedentes_1.length; i++) {
            //Obtenemos el valor que tenía asignado antes de agregar el nuevo model
            String [] valoresComboEntecedentes =  new String[5];
            valoresComboEntecedentes[0] = (String) combosAntecedentes_1[i].getSelectedItem();
            valoresComboEntecedentes[1] = (String) combosAntecedentes_2[i].getSelectedItem();
            valoresComboEntecedentes[2] = (String) combosAntecedentes_3[i].getSelectedItem();
            valoresComboEntecedentes[3] = (String) combosAntecedentes_4[i].getSelectedItem();
            valoresComboEntecedentes[4] = (String) combosAntecedentes_5[i].getSelectedItem();
            String consecuente = (String) combosConsecuentes[i].getSelectedItem();
            
            //Asignamos el nuevo modelo
            combosAntecedentes_1[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            combosAntecedentes_2[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            combosAntecedentes_3[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            combosAntecedentes_4[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            combosAntecedentes_5[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            combosConsecuentes[i].setModel(new javax.swing.DefaultComboBoxModel(listaElementos));
            
            //Reasignamos el los antiguos elementos al nuevo modelo
            combosAntecedentes_1[i].setSelectedItem(valoresComboEntecedentes[0]);
            combosAntecedentes_2[i].setSelectedItem(valoresComboEntecedentes[1]);
            combosAntecedentes_3[i].setSelectedItem(valoresComboEntecedentes[2]);
            combosAntecedentes_4[i].setSelectedItem(valoresComboEntecedentes[3]);
            combosAntecedentes_5[i].setSelectedItem(valoresComboEntecedentes[4]);
            combosConsecuentes[i].setSelectedItem(consecuente);
        }
        
    }
    
    public void nuevaRegla(){
        System.out.println("nueva regla");
        //Creamos combos temporales para ampliarlos
        JComboBox [] comboAntecedentesTemporal_1 = new JComboBox[combosAntecedentes_1.length+1];
        JComboBox [] comboAntecedentesTemporal_2 = new JComboBox[combosAntecedentes_1.length+1];
        JComboBox [] comboAntecedentesTemporal_3 = new JComboBox[combosAntecedentes_1.length+1];
        JComboBox [] comboAntecedentesTemporal_4 = new JComboBox[combosAntecedentes_1.length+1];
        JComboBox [] comboAntecedentesTemporal_5 = new JComboBox[combosAntecedentes_1.length+1];
        JComboBox [] comboConsecuentesTemporal = new JComboBox[combosAntecedentes_1.length+1];
        
        for (int i = 0; i < comboAntecedentesTemporal_1.length-1; i++) {
            
                //Asignamos como se encontraban los combos anteriores
                comboAntecedentesTemporal_1[i]=combosAntecedentes_1[i];
                comboAntecedentesTemporal_2[i]=combosAntecedentes_2[i];
                comboAntecedentesTemporal_3[i]=combosAntecedentes_3[i];
                comboAntecedentesTemporal_4[i]=combosAntecedentes_4[i];
                comboAntecedentesTemporal_5[i]=combosAntecedentes_5[i];
                comboConsecuentesTemporal[i]=combosConsecuentes[i];
        }
        //Asignamos el modelo a los nuevos combos
        comboAntecedentesTemporal_1[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
        comboAntecedentesTemporal_2[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
        comboAntecedentesTemporal_3[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
        comboAntecedentesTemporal_4[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
        comboAntecedentesTemporal_5[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
        comboConsecuentesTemporal[comboAntecedentesTemporal_1.length-1] = new JComboBox(new javax.swing.DefaultComboBoxModel(listaElementos));
                
        
        //Igualamos los combos con los que acabamos de optener
        combosAntecedentes_1=comboAntecedentesTemporal_1;
        combosAntecedentes_2=comboAntecedentesTemporal_2;
        combosAntecedentes_3=comboAntecedentesTemporal_3;
        combosAntecedentes_4=comboAntecedentesTemporal_4;
        combosAntecedentes_5=comboAntecedentesTemporal_5;
        combosConsecuentes=comboConsecuentesTemporal;
        
        int widht = combosAntecedentes_1[0].getWidth();
        int height = combosAntecedentes_1[0].getHeight();
        //Agremamos las nuevas reglas
        for (int i = 0; i < combosAntecedentes_1.length; i++) {
            combosAntecedentes_1[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_1[i].setVisible(true);
            combosAntecedentes_1[i].addActionListener(new actualizarConocimiento(i+1,1));
            panelReglas.add(combosAntecedentes_1[i]);
            combosAntecedentes_2[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_2[i].setVisible(true);
            combosAntecedentes_2[i].addActionListener(new actualizarConocimiento(i+1,2));
            panelReglas.add(combosAntecedentes_2[i]);
            combosAntecedentes_3[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_3[i].setVisible(true);
            combosAntecedentes_3[i].addActionListener(new actualizarConocimiento(i+1,3));
            panelReglas.add(combosAntecedentes_3[i]);
            combosAntecedentes_4[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_4[i].setVisible(true);
            combosAntecedentes_4[i].addActionListener(new actualizarConocimiento(i+1,4));
            panelReglas.add(combosAntecedentes_4[i]);
            combosAntecedentes_5[i].setBounds(0, i*25, widht, height);
            combosAntecedentes_5[i].setVisible(true);
            combosAntecedentes_5[i].addActionListener(new actualizarConocimiento(i+1,5));
            panelReglas.add(combosAntecedentes_5[i]);
            combosConsecuentes[i].setBounds(0, i*25, widht, height);
            combosConsecuentes[i].setVisible(true);
            combosConsecuentes[i].addActionListener(new actualizarConocimiento(i+1,6));
            panelReglas.add(combosConsecuentes[i]);
        }
        Main.win.setBounds(Main.win.getX(), Main.win.getY(), Main.win.getWidth()+1, Main.win.getHeight());
        Main.win.setBounds(Main.win.getX(), Main.win.getY(), Main.win.getWidth()-1, Main.win.getHeight());
        
        
    }

    class actualizarConocimiento implements ActionListener {
        int regla, numeroElemento;
        public actualizarConocimiento(int regla, int numeroElemento) {
            this.regla = regla;
            this.numeroElemento = numeroElemento;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch(numeroElemento){
                    case 1:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosAntecedentes_1[regla-1].getSelectedItem());
                        break;
                    case 2:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosAntecedentes_2[regla-1].getSelectedItem());
                        break;
                    case 3:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosAntecedentes_3[regla-1].getSelectedItem());
                        break;
                    case 4:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosAntecedentes_4[regla-1].getSelectedItem());
                        break;
                    case 5:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosAntecedentes_5[regla-1].getSelectedItem());
                        break;
                    case 6:
                        new ManipulacionBaseConocimiento().actualizarConocimiento(baseConocimiento, regla, numeroElemento,(String) combosConsecuentes[regla-1].getSelectedItem());
                        break;
                }
                System.out.println("Se actualizó la regla "+regla+" en el elemento "+numeroElemento);
                Main.crearArbol(baseConocimiento);
             } catch (Exception ex) {
                 System.out.println("No se pudo realizar una acctualización");
            }
        }

        
    }

    class accionesReglas implements ActionListener {
        int accion;
        public accionesReglas(int accion) {
            this.accion = accion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(accion){
                case 1: //Acción de agregar una nueva regla
                    nuevaRegla();
                    break;
            }
        }
    }

    class nuevoElemento implements MouseListener {

        public nuevoElemento() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //Le mandamos el nombre del nuevo elemento
            new ListaElementos().insertarElemento(baseConocimiento, 
                    JOptionPane.showInputDialog("Nombre del nuevo elemento"));
            actualizarListaElementos(new ListaElementos().leerElemento(baseConocimiento));
            Main.panelJustificacion.actualizarListaElementos(new ListaElementos().leerElemento(baseConocimiento));
            
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
