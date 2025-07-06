package controlador;

import TDA.Cola;
import TDA.Lista;
 
public class Dependencia {
    private String nombre;
    private Cola<Expediente> colaExpedientes;
    private static Lista<Dependencia> instancias;
    
    public static void setInstancias(Lista<Dependencia> deps) {
    instancias = deps;
}

public static Lista<Dependencia> getInstancias() {
    return instancias;
}
    
    public Dependencia(String nombre) {
        this.nombre = nombre;
        this.colaExpedientes = new Cola<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cola<Expediente> getColaExpedientes() {
        return colaExpedientes;
    }

    public void setColaExpedientes(Cola<Expediente> colaExpedientes) {
        this.colaExpedientes = colaExpedientes;
    }
    
    public void agregarExpediente(Expediente nuevo) {
    Cola<Expediente> aux = new Cola<>();
    boolean insertado = false;

    while (!colaExpedientes.esVacia()) {
        Expediente actual = colaExpedientes.desencolar();

        if (!insertado && nuevo.getPrioridad() < actual.getPrioridad()) {
            aux.encolar(nuevo);
            insertado = true;
        }

        aux.encolar(actual);
    }

    if (!insertado) {
        aux.encolar(nuevo); // Si tiene la menor prioridad, va al final
    }

    this.colaExpedientes = aux;
    }

    public boolean eliminarExpediente(String id) {
    Cola<Expediente> aux = new Cola<>();
    boolean eliminado = false;

    while (!colaExpedientes.esVacia()) {
        Expediente actual = colaExpedientes.desencolar();
        if (!eliminado && actual.getId().equals(id)) {
            eliminado = true; // No lo volvemos a encolar
        } else {
            aux.encolar(actual);
        }
    }

    this.colaExpedientes = aux;
    return eliminado;
    }
    
    public String mostrarExpedientes() {
    Cola<Expediente> aux = new Cola<>();
    StringBuilder sb = new StringBuilder("Cola de Expedientes:\n");

    while (!colaExpedientes.esVacia()) {
        Expediente e = colaExpedientes.desencolar();
        sb.append("- ").append(e.getId()).append(" (Prioridad ").append(e.getPrioridad()).append(")\n");
        aux.encolar(e);
    }

    this.colaExpedientes = aux; // restaurar
    return sb.toString();
    }

    public boolean estaVacia() {
    return colaExpedientes.esVacia();
}

    public Expediente buscarExpedienteEnCola(String id) {
        Cola<Expediente> aux = new Cola<>();
        Expediente resultado = null;

        while (!colaExpedientes.esVacia()) {
            Expediente e = colaExpedientes.desencolar();
            if (resultado == null && e.getId().equals(id)) {
                resultado = e;
            }
            aux.encolar(e);
        }

        this.colaExpedientes = aux; // Restaurar la cola original
        return resultado;
    }
}
