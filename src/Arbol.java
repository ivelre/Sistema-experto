public class Arbol {
    Nodo [] nodo = new Nodo[0];
    
    //Creamos el árbol
    public void crearArbol(String baseConocimeintos){
        //Optenemos las reglas del archivo
        String [][]reglas = new ManipulacionBaseConocimiento().obtenerReglas(baseConocimeintos);
        //creamos un arbol temporarl
        Nodo [] nodoTemporal = new Nodo[0];
        for (int i = 0; i < reglas.length; i++) {
            for (int j = 0; j < 6; j++) {
                if(!nodoExistente(nodoTemporal, reglas[i][j]) && !"".equals(reglas[i][j])){
                    nodoTemporal = nuevoNodo(nodoTemporal, reglas[i][j]);
                }
            }
            for (int j = 0; j < 5; j++) {
                if(!"".equals(reglas[i][j])){
                    nodoTemporal[posicionNodoArbol(nodoTemporal, reglas[i][j])].nuevaSalida(i);
                }
            }
            try {
                nodoTemporal[posicionNodoArbol(nodoTemporal, reglas[i][5])].nuevaLlegada(i);
            } catch (Exception e) {
            }
        }
        nodo = nodoTemporal;
                
    }
    
    //Aumentar un nodo al arbol
    public Nodo [] nuevoNodo(Nodo [] nodo, String nombre){
        Nodo [] nodoTemporal = new Nodo[nodo.length+1];
        for (int i = 0; i < nodo.length; i++) {
            nodoTemporal[i] = nodo[i];
        }
        nodoTemporal[nodoTemporal.length-1] = new Nodo();
        nodoTemporal[nodoTemporal.length-1].nombre=nombre;
        
        return nodoTemporal;
    }
    
    //Regrasa la posición en el arbol de un elemento
    public int posicionNodoArbol(Nodo [] nodo,String nombre){
        for (int i = 0; i < nodo.length; i++) {
            if(nodo[i].nombre.equals(nombre))
                return i;
        }
        return 0;
    }
    
    //Saber si un nodo ya existe en el arbol
    public boolean nodoExistente(Nodo [] nodo,String nombre){
        for (int i = 0; i < nodo.length; i++) {
            if(nodo[i].nombre.equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    //Regresa un arregle de nodos
    public Nodo [] getArbol(){
        return nodo;
    }
    
    //Regresa una lista de elementos no terminale
    public Nodo [] getNoTerminaler(){
        Nodo [] nodoTemporal = new Nodo[0];
        for (int i = 0; i < nodo.length; i++) {
            if(nodo[i].salidas.length!=0){
                nodoTemporal = nuevoNodo(nodoTemporal, nodo[i].nombre);
            }
        }
        return nodoTemporal;
    }
    
    //Regresa una lista de elementos no terminale
    public Nodo [] getTerminaler(){
        Nodo [] nodoTemporal = new Nodo[0];
        for (int i = 0; i < nodo.length; i++) {
            if(nodo[i].salidas.length==0){
                nodoTemporal = nuevoNodo(nodoTemporal, nodo[i].nombre);
            }
        }
        return nodoTemporal;
    }
    
    //Regresa los elementos de una regla en particular
    public String [] regla(int n){
        String [] regla = new String[6];
        
        //Inivialicamos los elementos de forma vacía
        for (int i = 0; i < regla.length; i++) {
            regla[i] = "";
        }
        
        //Buscamos la salidas
        int contadorRegla = 0;
        for (int i = 0; i < nodo.length; i++) {
            for (int j = 0; j < nodo[i].salidas.length; j++) {
                if(nodo[i].salidas[j]==n){
                   regla[contadorRegla] = nodo[i].nombre;
                   contadorRegla++;
                }
            }
        }
        
        for (int i = 0; i < nodo.length; i++) {
            for (int j = 0; j < nodo[i].llegadas.length; j++) {
                if(nodo[i].llegadas[j]==n){
                   regla[5] = nodo[i].nombre;
                }
            }
        }
        
        return regla;
    }
    //Regresa una lista de elementos no terminale
    public String [] getConsecuentes(){
        String [] consecuentes = new String[6];
        
        for (int i = 0; i < nodo.length; i++) {
            for (int j = 0; j < nodo[i].llegadas.length; j++) {
                consecuentes = nuevoConsecuente(consecuentes, ""+nodo[i].nombre);
            }
        }
        
        return consecuentes;
    }
    
    public String [] nuevoConsecuente(String [] consecuentes, String valor){
        boolean valorValido = true;
        for (int i = 0; i < consecuentes.length; i++) {
            if(valor.equals(consecuentes[i])){
                valorValido = false;
                break;
            }
        }
        if(valorValido){
            String [] consecuentesTemporal = new String[consecuentes.length+1];
            for (int i = 0; i < consecuentes.length; i++) {
                consecuentesTemporal[i] = consecuentes[i];
            }
            consecuentesTemporal[consecuentesTemporal.length-1] = valor;
            
            return consecuentesTemporal;
        }else{
            return consecuentes;
        }
    }
}
