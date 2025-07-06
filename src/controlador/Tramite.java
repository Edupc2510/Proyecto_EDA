package controlador;

import TDA.Lista;

public class Tramite implements Comparable<Tramite>{
    private FechaHora inicio;
    private FechaHora fin;
    private Lista<String> documentos;
    private Expediente expediente;
    private String estado;
    

    public Tramite(Expediente expediente , FechaHora inicio) {
        this.inicio = inicio;
        this.documentos = new Lista<>();
        this.expediente = expediente;
        this.fin = null;
        this.estado = "En Proceso";
    }

    public void finalizarTramite(FechaHora fin, Lista<String> documentos) {
        this.fin = fin;
        this.documentos = documentos;
        this.estado = "Finalizado";
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trámite\n");
        sb.append("--------\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Fecha de inicio: ").append(inicio != null ? inicio.toString() : "No registrado").append("\n");
        sb.append("Fecha de finalización: ").append(fin != null ? fin.toString() : "En proceso").append("\n");
        sb.append("Documentos entregados:\n");

        if (documentos == null || documentos.longitud() == 0) {
            sb.append("- Ninguno\n");
        } else {
            for (int i = 1; i <= documentos.longitud(); i++) {
                sb.append("- ").append(documentos.iesimo(i)).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public int compareTo(Tramite o) {
        if (this.fin == null && o.fin == null) return 0;
        if (this.fin == null) return 1; // Los que no tienen fin van después
        if (o.fin == null) return -1;
        return this.fin.compareTo(o.fin); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
