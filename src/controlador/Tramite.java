
package controlador;

import TDA.Lista;

public class Tramite {
    private FechaHora inicio;
    private FechaHora fin;
    private Lista<String> documentos;
    private Expediente expediente;
    
    // Constructor
    public Tramite(Expediente expediente) {
        this.expediente = expediente;
        this.documentos = new Lista<>();
        this.inicio = null;
        this.fin = null;
    }
    
    // Iniciar el trámite
    public void iniciarTramite(FechaHora inicio) {
        this.inicio = inicio;
    }

    // Finalizar el trámite y agregar un documento 
    public void finalizarTramite(FechaHora fin, String documento) {
        this.fin = fin;
        this.documentos.agregar(documento);
    }

    // Getters y Setters
    public FechaHora getInicio() {
        return inicio;
    }

    public void setInicio(FechaHora inicio) {
        this.inicio = inicio;
    }

    public FechaHora getFin() {
        return fin;
    }

    public void setFin(FechaHora fin) {
        this.fin = fin;
    }

    public Lista<String> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Lista<String> documentos) {
        this.documentos = documentos;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
        
}
