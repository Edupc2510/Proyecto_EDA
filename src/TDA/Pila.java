package TDA;

import TDA.Nodo;

public class Pila<T> {
    // Atributos
    private Nodo<T> cima;
    
    // Constructores
    public Pila(){
        cima = null;
    }
    /*
    * Esvacia: determina si una pila es vacia
    */
    public boolean esVacia(){
        return cima==null;
    }
    /*
    apilar o push: agrega un nuevo item a la pila 
    en la cima de la misma
    */
    public void apilar(T item){
        Nodo<T> nuevoNodo = new Nodo<>(item,null);
        if (esVacia()){
            cima = nuevoNodo;
        }else{
            Nodo<T> aux = cima;
            nuevoNodo.setSgteNodo(aux);
            cima = nuevoNodo;
        }
    }
    /*
    desapilar: elimina un item de la cima de la pila.
    debe devolver el item que es eliminado. Elimina el elemento
    que apunta la cima
    */
    public T desapilar(){
        if (esVacia()){
            throw new RuntimeException("La pila esta vacia!!");
        }else{ // la pila tiene elementos
            T item = cima.getItem();
            cima = cima.getSgteNodo();
            return item;
        }    
    }
}
