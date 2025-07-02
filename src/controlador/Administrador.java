package controlador;

import TDA.ListaDoble;

public class Administrador {
    private String nombreUsuario;
    private String contraseña;
    private ListaDoble<Expediente> expedientes;

    public Administrador(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.expedientes = new ListaDoble<>();
    }

    // Registrar un expediente
    public void registrarExpediente(Expediente expediente) {
        expedientes.agregar(expediente);
    }

    // Iniciar trámite
    public void registrarInicioTramite(String id, FechaHora inicio) {
        Expediente e = buscarExpediente(id);
        if (e != null) {
            e.iniciarTramite(inicio);
        }
    }

    // Finalizar trámite
    public void registrarFinalizacionTramite(String id, FechaHora fin, String documento) {
        Expediente e = buscarExpediente(id);
        if (e != null) {
            e.finalizarTramite(fin, documento);
        }
    }

    // Registrar movimiento
    public void registrarMovimiento(String id, FechaHora inicio, FechaHora fin, Dependencia origen, Dependencia destino, String observacion) {
        Expediente e = buscarExpediente(id);
        if (e != null) {
            e.registrarMovimiento(origen, destino, inicio, fin, observacion);
        }
    }

    // Mostrar seguimiento
    public String proporcionarSeguimiento(String id) {
        Expediente e = buscarExpediente(id);
        if (e == null) return "Expediente no encontrado.";

        return e.getTramite().getSeguimiento().mostrarMovimientos();
    }

    // Buscar expediente por ID
    private Expediente buscarExpediente(String id) {
        for (int i = 1; i <= expedientes.longitud(); i++) {
            Expediente e = expedientes.iesimo(i);
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Administrador: " + nombreUsuario;
    }
}
