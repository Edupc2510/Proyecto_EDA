package controlador;

import TDA.Cola;
 
public class Dependencia {
    private String nombre;
    private Cola<Expediente> colaExpedientes;
    
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
    
    
    
    
    
}
