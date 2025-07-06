package TDANoLineal;

public class Nodo<T> {
    private T item;
    private Nodo<T> padre;
    private Nodo<T> hijoIzq;
    private Nodo<T> hijoDer;

    public Nodo(T item, Nodo<T> padre, Nodo<T> hijoIzq, Nodo<T> hijoDer) {
        this.item = item;
        this.padre = padre;
        this.hijoIzq = hijoIzq;
        this.hijoDer = hijoDer;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }

    public Nodo<T> getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(Nodo<T> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public Nodo<T> getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(Nodo<T> hijoDer) {
        this.hijoDer = hijoDer;
    }


}
