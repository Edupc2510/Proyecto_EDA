package controlador;

import TDA.*;
import controlador.*;

public class Interesado {
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private String tipo;
    private Pila<Tramite> tramites;
    
    // Constructor
    public Interesado(String dni, String nombre, String telefono, String email, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.tramites = new Pila<>();
    }
    
    // Getters y Setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pila<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(Pila<Tramite> tramites) {
        this.tramites = tramites;
    }
    
    // Buscar tramite
    public Tramite buscarTramite(String id) {
        Pila<Tramite> aux = new Pila<>();
        Tramite t = null;
        while(!tramites.esVacia()){
            Tramite temp = tramites.desapilar();
            if (temp.getExpediente().getId().equals(id)){
                t = temp;
            }
            aux.apilar(temp);
        }
        while(!aux.esVacia()){
            tramites.apilar(aux.desapilar());
        }
        return t;
    }
    
    @Override
    public String toString() {
        return "Interesado:\n" +
               "  DNI: " + dni + "\n" +
               "  Nombre: " + nombre + "\n" +
               "  Teléfono: " + telefono + "\n" +
               "  Email: " + email + "\n" +
               "  Tipo: " + tipo;
    }
    
}
