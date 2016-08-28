
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;


public class MotorInferencia {
    Nodo [] arbol = new Nodo[0];
    int Longitud = 100;
    String [] hechos = new String[0];
    int [] conjuntoConficto = new int[0];
    int [] reglasEjecutadas = new int [0];
    
    //Asignación del árbol con el que se está trabajando
    public void setArbol(Nodo [] arbol){
        
        this.arbol = arbol;
    }
    
    //Comanzaños una nueva inferencia
    public void nuevaInferencia(String bc){
        reglasEjecutadas = new int [0];
        hechos = new String[0];
        conjuntoConficto = new int[0];
        inferir(bc);
    }
    
    //Función para inferir en el arbol
    public void inferir(String bc){
        System.out.println("Inició inferencia");
        //Vacear hechos
        hechos = new String[0];
        conjuntoConficto = new int[0];
        
        //Comencamos la inferencia
        busquedaHechos(bc);
        //Obtenemos el conjunto conficto
        conguntoConficto(Main.numeroReglas);
        //Comparamos para ver si podemos segir infiriendo
        if(conjuntoConficto.length==0){
            System.out.println("Ya no se pudo inferir");
            //revisamos si en los hecho está algún resultado
            buscarResulado();
        }else{
            //ejecutamos la regla y actualizamos la base de hechos
            actualizarBaseHechos(bc);
            inferir(bc);
        }
    }
    
    public void busquedaHechos(String bc){
        //Intentamos accesar a la base de conocimiento
        try {
            //Abrimos el archivo
            RandomAccessFile baseHechos=new RandomAccessFile("Data/Base de hechos/"+bc+".lqs", "rw");
            while((baseHechos.getFilePointer())!=(baseHechos.length())){
                hechos = añadirHecho(baseHechos.readUTF());
            }
            baseHechos.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a la base de conocimiento "+bc);
       }
    }
    
    public String [] añadirHecho(String hecho){
        //Creamos un arreglo de hechos temporal
        String [] hechosTemporal = new String[hechos.length+1];
        
        for (int i = 0; i < hechos.length; i++) {
            hechosTemporal[i] = hechos[i];
        }
        
        hechosTemporal[hechosTemporal.length-1] = hecho;
        
        return hechosTemporal;
    }
    
    public void conguntoConficto(int numeroReglas){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<reglsa");
        for (int i = 0; i < numeroReglas; i++) {
            System.out.print("Regla "+i+"=");
            String [] regla = Main.arbol.regla(i);
            int contadorElementosRegla = 0;
            for (int j = 0; j < regla.length-1; j++) {
                System.out.print(" -" + regla[j] + "- ");
                if(regla[j].equals("")){
                    contadorElementosRegla++;
                }else{
                    for (int k = 0; k < hechos.length; k++) {
                        if(hechos[k].equals(regla[j])){
                            contadorElementosRegla++;
                        }
                    }
                }
            }
            if(contadorElementosRegla==5){
                boolean reglaEjecutada = false;
                for (int j = 0; j < reglasEjecutadas.length; j++) {
                    if(reglasEjecutadas[j]==i){
                        reglaEjecutada = true;
                        break;
                    }
                }
                if(!reglaEjecutada){
                    System.out.println("nuevo conjunto");
                    conjuntoConficto = añadirValorConjuntoConficto(i);
                }
            }
            System.out.println("");
        }
    }
    
    public int [] añadirValorConjuntoConficto(int regla){
        //Creamos un arreglo de conjunto conficto temporal
        int [] conjuntoConfictoTemporal = new int[conjuntoConficto.length+1];
        
        for (int i = 0; i < conjuntoConficto.length; i++) {
            conjuntoConfictoTemporal[i] = conjuntoConficto[i];
        }
        System.out.println(""+regla);
        conjuntoConfictoTemporal[conjuntoConfictoTemporal.length-1] = regla;
        
        return conjuntoConfictoTemporal;
    }
    
    public void actualizarBaseHechos(String bc){
        //Intentamos accesar a la base de conocimiento
        try {
            //Abrimos el archivo
            RandomAccessFile baseHechos=new RandomAccessFile("Data/Base de hechos/"+bc+".lqs", "rw");
            boolean hechoEnBaseHechos = false;
            for (int i = 0; i < hechos.length; i++) {
                if(hechos[i].equals(Main.arbol.regla(conjuntoConficto[0])[5])){
                    hechoEnBaseHechos = true;
                    break;
                }
            }
            
            if(!hechoEnBaseHechos){
                baseHechos.seek(baseHechos.length());
                baseHechos.writeUTF(Main.arbol.regla(conjuntoConficto[0])[5]);
                hechos = añadirHecho(Main.arbol.regla(conjuntoConficto[0])[5]);
            }
            reglasEjecutadas = añadirReglaEjecutada(conjuntoConficto[0]);
            
            baseHechos.seek(0);
            System.out.println("--------");
            while((baseHechos.getFilePointer())!=(baseHechos.length())){
                System.out.println(baseHechos.readUTF());
            }
            System.out.println("-------");
            baseHechos.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a la base de conocimiento "+bc);
       }
    }
    
    //Añadimos una nueva regla ejecutada
    public int [] añadirReglaEjecutada(int regla){
        //Creamos un arreglo de conjunto conficto temporal
        int [] reglaTemporal = new int[reglasEjecutadas.length+1];
        
        for (int i = 0; i < reglasEjecutadas.length; i++) {
            reglaTemporal[i] = reglasEjecutadas[i];
        }
        System.out.println(""+regla);
        reglaTemporal[reglaTemporal.length-1] = regla;
        
        return reglaTemporal;
    }
    
    //buscamos el en la base de hechos
    public void buscarResulado(){
        Nodo [] terminales = Main.arbol.getTerminaler();
        for (int i = 0; i < terminales.length; i++) {
            System.out.println("---------------------------------------------->>>>>>>>>>>>>>>>"+terminales[i].nombre);
            
        }
        boolean hechoFinal = false;
        for (int i = 0; i < terminales.length; i++) {
            for (int j = 0; j < hechos.length; j++) {
                if(terminales[i].nombre.equals(hechos[j])){
                    inferenciaCompletaMostrarMensaje(hechos[j]);
                    hechoFinal = true;
                }
            }
        }
        if(!hechoFinal){
            infereciaCompletaMostrarMensajeSecundario();
        }
    }
    
    //Desplegamos el menje en caso de hacer encontrado algo
    public void inferenciaCompletaMostrarMensaje(String elementoFinal){
        try {
            BufferedReader archivo = new BufferedReader(new FileReader(new File("Data/Justificación/"+Main.baseConocimiento+"/"+elementoFinal+".txt")));
            String justificion = "", linea;
            while((linea = archivo.readLine())!=null) {
                justificion = justificion +linea;
            }
            archivo.close();
            JOptionPane.showMessageDialog(null, justificion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El resultado final es " + elementoFinal);
        }
    }
    
    public void infereciaCompletaMostrarMensajeSecundario(){
        boolean resultado = false;
        for (int i = 0; i < hechos.length; i++) {
            for (int j = 0; j < Main.arbol.getConsecuentes().length; j++) {
                System.out.println(hechos[i]+"-"+Main.arbol.getConsecuentes()[j]);
                if(hechos[i].equals(Main.arbol.getConsecuentes()[j])){
                    resultado = true;
                    try {
                        BufferedReader archivo = new BufferedReader(new FileReader(new File("Data/Justificación/"+Main.baseConocimiento+"/"+hechos[i]+".txt")));
                        String justificion = "", linea;
                        while((linea = archivo.readLine())!=null) {
                            justificion = justificion +linea;
                        }
                        archivo.close();
                        JOptionPane.showMessageDialog(null, justificion);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Uno de los resultados a tomar en cuenta es " + hechos[i]);
                    }
                }
            }
        }
        if(!resultado){
            try {
                BufferedReader archivo = new BufferedReader(new FileReader(new File("Data/Justificación/"+Main.baseConocimiento+"/default.txt")));
                String justificion = "", linea;
                while((linea = archivo.readLine())!=null) {
                    justificion = justificion +linea;
                }
                archivo.close();
                JOptionPane.showMessageDialog(null, justificion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Resultados no concluyentes");
            }
        }
    }
}
