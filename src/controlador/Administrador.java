package controlador;

import TDA.ListaDoble;

public class Administrador {
    private String nombreUsuario;
    private String contraseña;
    private ListaDoble <Expediente> expedientes;
    
    // Constructor
    public Administrador(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.expedientes = new ListaDoble<>();
    }
    
    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ListaDoble<Expediente> getExpediente() {
        return expedientes;
    }

    public void setExpediente(ListaDoble<Expediente> expediente) {
        this.expedientes = expediente;
    }

    // Registro de ingreso del expediente
    public void registrarExpediente(Expediente expediente) {
        expedientes.agregar(expediente);
    }
    
    // Registra el movimiento del expediente
    public void registrarMovimiento(String id, Seguimiento seguimiento) {
        Expediente expediente = buscarExpediente(id);
        if (expediente != null) {
            expediente.agregarSeguimiento(seguimiento);
        }
    }
    
    // Registra finalización del trámite del expediente
    public void registrarFinalizacionTramite(String id, Tramite tramite) {
        Expediente expediente = buscarExpediente(id);
        if (expediente != null) {
            expediente.agregarTramite(tramite);
        }
    }
    
    // Proporcionar al interesado el seguimiento de su trámite
    public String proporcionarSeguimiento(String identificador) {
        Expediente expediente = buscarExpediente(identificador);
        if (expediente != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Seguimiento del expediente ").append(identificador).append(":\n");
            for (int i = 1; i <= expediente.getSeguimientos().longitud(); i++) {
                sb.append(expediente.getSeguimientos().iesimo(i)).append("\n");
            }
            return sb.toString();
        }
        return "Expediente no encontrado.";
    }
    
    // Método buscar expediente
    public Expediente buscarExpediente(String id) {
        for (int i = 1; i <= expedientes.longitud(); i++) {
            Expediente exp = expedientes.iesimo(i);
            if (exp.getId().equals(id)) {
                return exp;
            }
        }
        return null;
    }
    
    
    @Override
    public String toString() {
        return "Administrador: " + nombreUsuario;
    }
}
