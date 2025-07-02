package controlador;

import TDA.Lista;

public class Expediente {
    private String id;
    private int prioridad;
    private String asunto;
    private String docReferencia;
    private Interesado interesado;
    private Tramite tramite;

    public Expediente(String id, int prioridad, Interesado interesado, String asunto, String docReferencia) {
        this.id = id;
        this.prioridad = prioridad;
        this.asunto = asunto;
        this.docReferencia = docReferencia;
        this.interesado = interesado;
        this.tramite = new Tramite(this);
    }

    public void iniciarTramite(FechaHora inicio) {
        tramite.iniciarTramite(inicio);
    }

    public void finalizarTramite(FechaHora fin, String documento) {
        tramite.finalizarTramite(fin, documento);
    }

    public void registrarMovimiento(Dependencia origen, Dependencia destino, FechaHora inicio, FechaHora fin, String observacion) {
        Movimiento m = new Movimiento(origen, destino, inicio, fin, observacion);
        tramite.getSeguimiento().agregarMovimiento(m);
    }

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

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    @Override
    public String toString() {
        return "Expediente ID: " + id + "\nPrioridad: " + prioridad +
               "\nAsunto: " + asunto + "\nInteresado: " + interesado.getNombre();
    }
}
