
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import javax.swing.JOptionPane;


public class ManipulacionBaseConocimiento {
    int longitudLlave = 4;
    int longitudElemento = 100;
    int longitudRegla = longitudLlave+(longitudElemento*6);
    
    /*Funcián para actualizar la base de conocimiento
        nesecita el nombre de la base de conocimiento
        Regla que se va a modificar 
        El elemento que va a modificar
        Y el valor que va a modificar*/
    public void actualizarConocimiento(String bc, int regla, int numeroElemento,String elemento){
        //Intentamos accesar a la base de conocimiento
        try {
            //Abrimos el archivo
            RandomAccessFile baseConocimiento=new RandomAccessFile("Data/Bases de conocimiento/Cerebro/"+bc+".lqs", "rw");
            RandomAccessFile indice=new RandomAccessFile("Data/Bases de conocimiento/Índice/"+bc+".lqs", "rw");
            //Vamos a la ultima posición del documento
            baseConocimiento.seek((((regla-1)*longitudRegla)+longitudLlave)+((numeroElemento-1)*longitudElemento));
            indice.seek((((regla-1)*longitudElemento)+longitudLlave));
            //Escribimos el elemento
            baseConocimiento.writeUTF(elemento);
            if(numeroElemento==6){
                indice.writeUTF(elemento);
            }
            //Mandamos al main el total de reglas usadas
            Main.numeroReglas = (int) baseConocimiento.length()/longitudRegla + 1;
            //Cerramos el archivo
            baseConocimiento.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a la base de conocimiento "+bc);
       }
    }
    
    //Regresamos las reglas del archivo
    public String [][] obtenerReglas(String bc){
        String [] [] reglas = new String[0][0];
        //Intentamos accesar a la base de conocimiento
        try {
            //Abrimos el archivo
            RandomAccessFile baseConocimiento=new RandomAccessFile("Data/Bases de conocimiento/Cerebro/"+bc+".lqs", "rw");
            
            int numeroReglas = (int)(baseConocimiento.length()/longitudRegla)+1;
            String [] [] reglasTemporal = new String[numeroReglas][6];
            
            //Repetimos el ciclo de lectura
            for (int i = 0; i < numeroReglas; i++) {
                for (int j = 0; j < 6; j++) {
                    baseConocimiento.seek((((i)*longitudRegla)+longitudLlave)+((j)*longitudElemento));
                    try {
                        reglasTemporal[i][j] = baseConocimiento.readUTF();
                    } catch (Exception e) {
                        reglasTemporal[i][j] = "";
                    }
                }
            }
            //Igualamos el arreglo que regresarémos con el que obtubimos
            reglas=reglasTemporal;
            //Cerramos el archivo
            baseConocimiento.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a la base de conocimiento "+bc);
       }
        return reglas;
    }
    
}
