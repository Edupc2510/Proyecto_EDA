package controlador;

import TDA.Lista;

public class Tramite {
    private FechaHora inicio;
    private FechaHora fin;
    private Lista<String> documentos;
    private Expediente expediente;
    private Seguimiento seguimiento;

    public Tramite(Expediente expediente) {
        this.expediente = expediente;
        this.documentos = new Lista<>();
        this.seguimiento = new Seguimiento();
        this.inicio = null;
        this.fin = null;
    }

    public void iniciarTramite(FechaHora inicio) {
        this.inicio = inicio;
    }

    public void finalizarTramite(FechaHora fin, String documento) {
        this.fin = fin;
        this.documentos.agregar(documento);
    }

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

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }
    
    
}
