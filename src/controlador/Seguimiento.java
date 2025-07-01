package controlador;

public class Seguimiento {
    private String dependenciaOrigen;    
    private String dependenciaDestino;   
    private FechaHora fechaHora;         
    private String observaciones; 
    
    // Constructor
    public Seguimiento(String dependenciaOrigen, String dependenciaDestino, FechaHora fechaHora, String observaciones) {
        this.dependenciaOrigen = dependenciaOrigen;
        this.dependenciaDestino = dependenciaDestino;
        this.fechaHora = fechaHora;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public String getDependenciaOrigen() {
        return dependenciaOrigen;
    }

    public void setDependenciaOrigen(String dependenciaOrigen) {
        this.dependenciaOrigen = dependenciaOrigen;
    }

    public String getDependenciaDestino() {
        return dependenciaDestino;
    }

    public void setDependenciaDestino(String dependenciaDestino) {
        this.dependenciaDestino = dependenciaDestino;
    }

    public FechaHora getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(FechaHora fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    // Método toString para mostrar el seguimiento 
    @Override
    public String toString() {
        return "Seguimiento:\n" +
               "  Dependencia origen: " + dependenciaOrigen + "\n" +
               "  Dependencia destino: " + dependenciaDestino + "\n" +
               "  Fecha y hora: " + fechaHora + "\n" +
               "  Observaciones: " + observaciones;
    }
}
