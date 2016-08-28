
public class Nodo {
    String nombre;
    int [] llegadas = new int[0];
    int [] salidas = new int[0];
    
    //Asignar una nueva llega
    public void nuevaLlegada(int llegada){
        //generamos un arreglo temporal de llegadas
        int [] llegadasTemporal = new int[llegadas.length+1];
        
        //Asignamos lo que tenía la anterior
        for (int i = 0; i < llegadas.length; i++) {
            llegadasTemporal[i] = llegadas[i];
        }
        
        //Asignamos la nueva entrada
        llegadasTemporal[llegadasTemporal.length-1] = llegada;
        
        //Igualamos la lsita temporal a la del nodo
        llegadas = llegadasTemporal;
    }
    
    //Asignar una nueva salida
    public void nuevaSalida(int salida){
        //generamos un arreglo temporal de llegadas
        int [] SalidasTemporal = new int[salidas.length+1];
        
        //Asignamos lo que tenía la anterior
        for (int i = 0; i < salidas.length; i++) {
            SalidasTemporal[i] = salidas[i];
        }
        
        //Asignamos la nueva entrada
        SalidasTemporal[SalidasTemporal.length-1] = salida;
        
        //Igualamos la lsita temporal a la del nodo
        salidas = SalidasTemporal;
    }
    
    public int [] getLlegadas(){
        return llegadas;
    }
    
    public int [] getSalidas(){
        return salidas;
    }
}
