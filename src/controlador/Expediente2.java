package controlador;

import TDA.Lista;

public class Expediente {
    private String id;
    private int prioridad;
    private String asunto;
    private String docReferencia;
    private Interesado interesado;
    private Lista <Tramite> tramites; 
    private Lista <Seguimiento> seguimientos; 
    
    // Constructor
    public Expediente(String id, int prioridad, Interesado interesado, String asunto, String docReferencia) {
        this.id = id;
        this.prioridad = prioridad;
        this.asunto = asunto;
        this.docReferencia = docReferencia;
        this.interesado = interesado;
        this.tramites = new Lista<>();
        this.seguimientos = new Lista<>();
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDocReferencia() {
        return docReferencia;
    }

    public void setDocReferencia(String docReferencia) {
        this.docReferencia = docReferencia;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Lista<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(Lista<Tramite> tramites) {
        this.tramites = tramites;
    }

    public Lista<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(Lista<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }
    
    // Método para agregar un trámite al expediente
    public void agregarTramite(Tramite tramite) {
        tramites.agregar(tramite);
    }
    
    // Método para agregar un evento de seguimiento
    public void agregarSeguimiento(Seguimiento seguimiento) {
        seguimientos.agregar(seguimiento);
    }
    
    // Método para mostrar el tramite
    @Override
    public String toString() {
        return "Expediente:\n" +
               "  Identificador: " + id + "\n" +
               "  Prioridad: " + prioridad + "\n" +
               "  Interesado: " + interesado.getNombre() + " (DNI: " + interesado.getDni() + ")\n" +
               "  Asunto: " + asunto + "\n" +
               "  Documento de referencia: " + docReferencia + "\n" +
               "  Cantidad de trámites: " + tramites.longitud() + "\n" +
               "  Cantidad de eventos de seguimiento: " + seguimientos.longitud();
    }
}
