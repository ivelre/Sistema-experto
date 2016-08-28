
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ListaElementos {
    //Longitud de cada cadena
    int longitud=100;
    public void insertarElemento(String bc,String elemento){
        //Intentamos crear la lista de elementos que contiene la base de conocimientos
        try {
            //Abrimos el archivo
            RandomAccessFile listaElementos=new RandomAccessFile("Data/Elementos/"+bc+".lqs", "rw");
            //Vamos a la ultima posición del documento
            listaElementos.seek((int)(listaElementos.length()/longitud)*longitud+longitud);
            //Escribimos el elemento
            listaElementos.writeUTF(elemento);
            
            listaElementos.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No se ha podido acceder a "+bc);
       }
    }
    
    public String [] leerElemento(String bc){
        String [] elementos = new String[0];
        //Intentamos leer la lista de elementos que contiene la base de conocimientos
        try {
            //Abrimos el archivo
            RandomAccessFile listaElementos=new RandomAccessFile("Data/Elementos/"+bc+".lqs", "rw");
            
            //Generamos una lista de elemtos temporal en base a la cantidad de elemnetos
            String [] elementosTempral = new String[(int)(listaElementos.length()/longitud)+1];
            
            //Repetimos el ciclo de lectura
            for (int i = 0; i < (int)(listaElementos.length()/longitud)+1; i++) {
                //Nos posicionamos en el elemento a leer en el archivo
                listaElementos.seek(i*longitud);
                //Guardamos el elemento en el arreglo temporal
                elementosTempral[i]=listaElementos.readUTF();
            }
            //Igualamos el arreglo que regresarémos con el que obtubimos
            elementos=elementosTempral;
            
            //Cerramos el archivo
            listaElementos.close();
           
       } catch (IOException ex) {
           //Si no podemos realizar la acción mostramos un diáligo
            JOptionPane.showMessageDialog(null, "No hay elementos en "+bc);
       }
        //Reglersamos los elementos
        return elementos;
    }
}
