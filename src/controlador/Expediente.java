package controlador;

import TDA.Lista;

public class Expediente {
    private String id;
    private int prioridad;
    private String asunto;
    private String docReferencia;
    private Interesado interesado;
    private Lista<Seguimiento> seguimientos;

    public Expediente(String id, int prioridad, Interesado interesado, String asunto, String docReferencia) {
        this.id = id;
        this.prioridad = prioridad;
        this.asunto = asunto;
        this.docReferencia = docReferencia;
        this.interesado = interesado;
        this.seguimientos = new Lista<>();
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

    public Lista<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(Lista<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }   

    
    public String mostrarSeguimientos() {
    if (seguimientos.longitud() == 0) {
        return "No hay seguimientos registrados.";
    }
    StringBuilder sb = new StringBuilder("Seguimientos del trámite:\n\n");
    for (int i = 1; i <= seguimientos.longitud(); i++) {
        Seguimiento s = seguimientos.iesimo(i);
        sb.append("De: ").append(s.getOrigen().getNombre()).append("\n");
        if (s.getDestino() != null){
        sb.append("A: ").append(s.getDestino().getNombre()).append("\n");
        }
        if (s.getFh() != null) {
            sb.append("Fecha y Hora: ").append(s.getFh().toString()).append("\n");
        }
        sb.append("Observaciones: ").append(s.getObservacion()).append("\n");
        sb.append("------------------------\n");
    }
    return sb.toString();
    }
     

}
