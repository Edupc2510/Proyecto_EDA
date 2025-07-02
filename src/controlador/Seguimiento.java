package controlador;

import TDA.Pila;

public class Seguimiento {
    private Pila<Movimiento> pilaMovimientos;

    public Seguimiento() {
        this.pilaMovimientos = new Pila<>();
    }

    public Pila<Movimiento> getPilaMovimientos() {
        return pilaMovimientos;
    }

    public void setPilaMovimientos(Pila<Movimiento> pilaMovimientos) {
        this.pilaMovimientos = pilaMovimientos;
    }
    
    public void agregarMovimiento(Movimiento m) {
        pilaMovimientos.apilar(m);
    }

    public Movimiento verUltimoMovimiento() {
        if (pilaMovimientos.esVacia()) {
            return null;
        }
        Movimiento ultimo = pilaMovimientos.desapilar(); 
        pilaMovimientos.apilar(ultimo); 
        return ultimo;
    }
    
    public String mostrarMovimientos() {
        String resultado = "";
        Pila<Movimiento> auxiliar = new Pila<>();

        while (!pilaMovimientos.esVacia()) {
            Movimiento m = pilaMovimientos.desapilar();
            resultado += m + "\n";  // Usa toString() de Movimiento
            auxiliar.apilar(m);
        }

        while (!auxiliar.esVacia()) {
            pilaMovimientos.apilar(auxiliar.desapilar());
        }

        return resultado;
    }
}