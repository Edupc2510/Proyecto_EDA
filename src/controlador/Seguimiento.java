
package controlador;


public class Seguimiento {
    private Dependencia origen;
    private Dependencia destino;
    private FechaHora fh;
    private String observacion;

    public Seguimiento(Dependencia origen, Dependencia destino, FechaHora fh, String observacion) {
        this.origen = origen;
        this.destino = destino;
        this.fh = fh;
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

    public FechaHora getFh() {
        return fh;
    }

    public void setInicio(FechaHora fh) {
        this.fh = fh;
    }


    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
    return "De: " + (origen != null ? origen.getNombre() : "N/A") +
           ", A: " + (destino != null ? destino.getNombre() : "N/A") +
           ", Fecha y Hora: " + fh +
           ", Observación: " + observacion;
}

    
    
    
}
