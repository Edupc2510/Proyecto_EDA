
package controlador;


public class Movimiento {
    private Dependencia origen;
    private Dependencia destino;
    private FechaHora inicio;
    private FechaHora fin;
    private String observacion;

    public Movimiento(Dependencia origen, Dependencia destino, FechaHora inicio, FechaHora fin, String observacion) {
        this.origen = origen;
        this.destino = destino;
        this.inicio = inicio;
        this.fin = fin;
        this.observacion = observacion;
    }

    public Dependencia getOrigen() {
        return origen;
    }

    public void setOrigen(Dependencia origen) {
        this.origen = origen;
    }

    public Dependencia getDestino() {
        return destino;
    }

    public void setDestino(Dependencia destino) {
        this.destino = destino;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "origen=" + origen + ", destino=" + destino + ", inicio=" + inicio + ", fin=" + fin + ", observacion=" + observacion + '}';
    }
    
    
    
}
