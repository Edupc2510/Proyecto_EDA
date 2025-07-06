package TDANoLineal;

import TDA.Lista;
import controlador.Tramite;

public class ArbolBB<T extends Comparable> {
    // Atributos
    private T raiz;
    private ArbolBB<T> subArbolIzq;
    private ArbolBB<T> subArbolDer;

    public ArbolBB() {
        raiz = null;
        subArbolIzq = null;
        subArbolDer = null;
    }

    public ArbolBB(T raiz, 
            ArbolBB<T> subArbolIzq, 
            ArbolBB<T> subArbolDer) {
        this.raiz = raiz;
        this.subArbolIzq = subArbolIzq;
        this.subArbolDer = subArbolDer;
    }

    public T getRaiz() {
        return raiz;
    }

    public void setRaiz(T raiz) {
        this.raiz = raiz;
    }

    public ArbolBB<T> getSubArbolIzq() {
        return subArbolIzq;
    }

    public void setSubArbolIzq(ArbolBB<T> subArbolIzq) {
        this.subArbolIzq = subArbolIzq;
    }

    public ArbolBB<T> getSubArbolDer() {
        return subArbolDer;
    }

    public void setSubArbolDer(ArbolBB<T> subArbolDer) {
        this.subArbolDer = subArbolDer;
    }
    
    // Otros metodos
    public boolean esVacio(){
        return raiz ==null;
    }
    /*
    Agregar un nuevo item al arbol
    */
    public void agregar(T item){
        // Caso 1: arbol esta vacio
        if (esVacio()){
            raiz = item;
        }else{
            if (raiz.compareTo(item)>0){ // La raiz es mayor a item
                // el item debe estar en el subarbol izq.
                if (subArbolIzq==null){
                    subArbolIzq = new ArbolBB(item,null,null);
                }else{
                    subArbolIzq.agregar(item);
                }
            }else if (raiz.compareTo(item)<0){ //raiz es menor al item
                if (subArbolDer==null){
                    subArbolDer = new ArbolBB(item,null,null);
                }else{
                    subArbolDer.agregar(item);
                }                
            }            
        }
    }
    // Recorrido en Inorden del arbolBB
    public void inOrden(){
        if (!esVacio()){
            if (subArbolIzq!=null)
                subArbolIzq.inOrden();
            System.out.print(raiz+"\t");
            if (subArbolDer!=null)
                subArbolDer.inOrden();
        }
    }
    /*
    Min: recupera el valor minimo del arboBB
    */
    public T min(){
        if (esVacio()){
            throw new RuntimeException("Error: el arbol esta vacio!!");
        }else{
            if (subArbolIzq==null)
                return raiz;
            else{
                return subArbolIzq.min();
            }
        }
    }
    
    
    

    public Lista<T> obtenerInOrden() {
    Lista<T> lista = new Lista<>();
    recorrerInOrden(this, lista);
    return lista;
    }

private void recorrerInOrden(ArbolBB<T> nodo, Lista<T> lista) {
    if (nodo == null || nodo.raiz == null) return;
    
    if (nodo.subArbolIzq != null)
        recorrerInOrden(nodo.subArbolIzq, lista);

    lista.agregar(nodo.raiz);

    if (nodo.subArbolDer != null)
        recorrerInOrden(nodo.subArbolDer, lista);
    }

    
}
