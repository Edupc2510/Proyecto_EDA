/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import controlador.*;
import javax.swing.*;
import TDA.*;
import java.time.LocalDateTime;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;




public class AppGUI {
    private static JTable tablaAlertas;
    private static DefaultTableModel modeloAlertas;
    private static final String USUARIO_ADMIN = "20232157";
    private static final String CLAVE_ADMIN = "1234";
    private static int contadorExpediente = 1;
    private static final String[] DEPENDENCIAS = {
        "Mesa de Partes", "Archivo Central", "Decanato", "Tesorería",
        "Biblioteca", "Bienestar Universitario", "Secretaría Académica", "Oficina de Graduados"
    };
    public static void main(String[] args) {
    if (!autenticar()) return;

    Administrador admin = new Administrador(USUARIO_ADMIN, CLAVE_ADMIN);
    Dependencia[] deps = new Dependencia[DEPENDENCIAS.length];
    for (int i = 0; i < DEPENDENCIAS.length; i++) {
        deps[i] = new Dependencia(DEPENDENCIAS[i]);
    }
    // Inicializar ventana de alertas
    JFrame ventanaAlertas = new JFrame("📋 Alertas de Expedientes");
    ventanaAlertas.setSize(800, 300);
    ventanaAlertas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaAlertas.setLocationRelativeTo(null);

    // Tabla de alertas
    String[] columnas = {"ID", "Asunto", "Prioridad", "Fecha", "Dependencia Actual"};
    modeloAlertas = new DefaultTableModel(columnas, 0);
    tablaAlertas = new JTable(modeloAlertas);
    tablaAlertas.setEnabled(false);

    JScrollPane scrollPane = new JScrollPane(tablaAlertas);
    scrollPane.setPreferredSize(new Dimension(780, 200));

    ventanaAlertas.add(scrollPane, BorderLayout.CENTER);
    ventanaAlertas.setVisible(true);

    // Cargar alertas al inicio
    actualizarTablaAlertas(admin, deps);
    

    while (true) {
        String[] opciones = {
            "Registrar Trámite", "Registrar Movimiento", "Finalizar Trámite",
            "Consultar Seguimiento", "Salir"
        };
        int op = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Trámites",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (op == JOptionPane.CLOSED_OPTION || op == 4) break;

        switch (op) {
            case 0 -> registrarTramite(admin, deps);
            case 1 -> registrarMovimiento(admin, deps);
            case 2 -> finalizarTramite(admin, deps);
            case 3 -> consultarSeguimiento(admin);
        }
    }

    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. ♡");
}

private static boolean autenticar() {
    for (int i = 0; i < 3; i++) {
        String usuario = JOptionPane.showInputDialog("Usuario:");
        String clave = JOptionPane.showInputDialog("Contraseña:");
        if (USUARIO_ADMIN.equals(usuario) && CLAVE_ADMIN.equals(clave)) return true;
        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
    }
    JOptionPane.showMessageDialog(null, "Demasiados intentos.");
    return false;
}

private static String generarIdExpediente() {
    return String.valueOf(contadorExpediente++);
}

private static void registrarTramite(Administrador admin, Dependencia[] deps) {
    
    String dni = leerCampo("Ingrese DNI del interesado:", "\\d{8}", "DNI inválido.");
    if (dni == null) return;

    Interesado interesado = admin.buscarInteresado(dni);
    String nombre, telefono, correo, tipo;

    if (interesado != null) {
        nombre = interesado.getNombre();
        tipo = interesado.getTipo();
        JOptionPane.showMessageDialog(null, "Nombre: " + nombre + "\nTipo: " + tipo);
        int mantenerTelefono = JOptionPane.showConfirmDialog(
                null,
                "¿Desea mantener el teléfono registrado: " + interesado.getTelefono() + "?",
                "Teléfono",
                JOptionPane.YES_NO_OPTION
        );
        if (mantenerTelefono == JOptionPane.YES_OPTION) {
            telefono = interesado.getTelefono();
        } else {
            telefono = leerCampo("Nuevo teléfono:", "\\d{9}", "Teléfono inválido.");
            if (telefono == null) return;
        }

        int mantenerCorreo = JOptionPane.showConfirmDialog(
                null,
                "¿Desea mantener el correo registrado: " + interesado.getEmail() + "?",
                "Correo",
                JOptionPane.YES_NO_OPTION
        );
        if (mantenerCorreo == JOptionPane.YES_OPTION) {
            correo = interesado.getEmail();
        } else {
            correo = leerCampo("Nuevo correo:", ".+@.+\\..+", "Correo inválido.");
            if (correo == null) return;
        }
    } else {
        nombre = JOptionPane.showInputDialog("Nombre del interesado:");
        if (nombre == null) return;

        String[] tipos = {"Ulima", "Externo"};
        String seleccion = (String) JOptionPane.showInputDialog(null, "Tipo de interesado:", "Tipo", JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
        if (seleccion == null) return;
        tipo = seleccion;

        telefono = leerCampo("Teléfono:", "\\d{9}", "Teléfono inválido.");
        if (telefono == null) return;

        correo = leerCampo("Correo:", ".+@.+\\..+", "Correo inválido.");
        if (correo == null) return;

        interesado = new Interesado(dni, nombre, telefono, correo, tipo);
    }

    String asunto = JOptionPane.showInputDialog("Asunto del expediente:");
    if (asunto == null) return;

    String docReferencia = JOptionPane.showInputDialog("Documento de referencia (opcional):");
    if (docReferencia == null) docReferencia = "";

    String prioridadStr = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione prioridad del expediente:",
            "Prioridad",
            JOptionPane.PLAIN_MESSAGE,
            null,
            new String[]{"1", "2", "3", "4", "5"},
            "3"
    );
    if (prioridadStr == null) return;
    int prioridad = Integer.parseInt(prioridadStr);

    String dependenciaOrigen = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione dependencia de origen:",
            "Dependencia",
            JOptionPane.PLAIN_MESSAGE,
            null,
            DEPENDENCIAS,
            DEPENDENCIAS[0]
    );
    if (dependenciaOrigen == null) return;

    Dependencia origen = buscarDependencia(dependenciaOrigen, deps);
    if (origen == null) {
        JOptionPane.showMessageDialog(null, "Dependencia no encontrada.");
        return;
    }

    FechaHora fecha = fechaActual();

    String id = generarIdExpediente(); // Método que debes tener para crear IDs únicos, puedes ajustar

    admin.registrarTramite(id, prioridad, interesado, asunto, docReferencia, fecha, origen);

    JOptionPane.showMessageDialog(null, "Expediente registrado correctamente.");
    actualizarTablaAlertas(admin, deps);
}


private static void registrarMovimiento(Administrador admin, Dependencia[] deps) {
    String[] modos = {"Por Dependencia", "Por Interesado"};
    String modo = (String) JOptionPane.showInputDialog(null, "Buscar expediente:", "Modo de búsqueda",
            JOptionPane.PLAIN_MESSAGE, null, modos, modos[0]);
    if (modo == null) return;

    Dependencia origen = null;
    Expediente expedienteSeleccionado = null;
    String id = null;
    String dni = null;

    if (modo.equals("Por Dependencia")) {
        String origenNombre = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione dependencia de origen:",
                "Origen",
                JOptionPane.PLAIN_MESSAGE,
                null,
                DEPENDENCIAS,
                DEPENDENCIAS[0]
        );
        if (origenNombre == null) return;

        origen = buscarDependencia(origenNombre, deps);
        if (origen == null) {
            JOptionPane.showMessageDialog(null, "Dependencia no encontrada.");
            return;
        }

        Cola<Expediente> colaOriginal = origen.getColaExpedientes();
        Cola<Expediente> colaTemporal = new Cola<>();
        Lista<Expediente> listaExpedientes = new Lista<>();

        while (!colaOriginal.esVacia()) {
            Expediente e = colaOriginal.desencolar();
            listaExpedientes.agregar(e);
            colaTemporal.encolar(e);
        }
        while (!colaTemporal.esVacia()) colaOriginal.encolar(colaTemporal.desencolar());

        if (listaExpedientes.esVacia()) {
            JOptionPane.showMessageDialog(null, "No hay expedientes en esta dependencia.");
            return;
        }

        String[] opcionesExp = new String[listaExpedientes.longitud()];
        for (int i = 1; i <= listaExpedientes.longitud(); i++) {
            Expediente e = listaExpedientes.iesimo(i);
            opcionesExp[i-1] = e.getId() + " - " + e.getAsunto();
        }

        String seleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione expediente:",
                "Cola de Expedientes",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesExp,
                opcionesExp[0]
        );
        if (seleccionado == null) return;

        id = seleccionado.split(" - ")[0];
        for (int i = 1; i <= listaExpedientes.longitud(); i++) {
            Expediente e = listaExpedientes.iesimo(i);
            if (e.getId().equals(id)) {
                expedienteSeleccionado = e;
                dni = e.getInteresado().getDni();
                break;
            }
        }
        // Obtener el trámite asociado al expediente
        Interesado interesado = admin.buscarInteresado(dni);
        if (interesado != null) {
            Tramite tramite = interesado.buscarTramite(expedienteSeleccionado.getId());
            if (tramite != null && tramite.getEstado().equalsIgnoreCase("Finalizado")) {
                JOptionPane.showMessageDialog(null, "Este trámite ya ha sido finalizado. No se puede mover.");
                return;
            }
        }


    } else { // Por Interesado
        dni = leerCampo("DNI del interesado:", "\\d{8}", "DNI inválido.");
        if (dni == null) return;

        Lista<Expediente> encontrados = new Lista<>();
        for (Dependencia dep : deps) {
            Cola<Expediente> original = dep.getColaExpedientes();
            Cola<Expediente> aux = new Cola<>();
            while (!original.esVacia()) {
                Expediente e = original.desencolar();
                if (e.getInteresado().getDni().equals(dni)) {
                    encontrados.agregar(e);
                }
                aux.encolar(e);
            }
            while (!aux.esVacia()) original.encolar(aux.desencolar());
        }

        if (encontrados.esVacia()) {
            JOptionPane.showMessageDialog(null, "No se encontraron expedientes para ese DNI.");
            return;
        }

        String[] opcionesExp = new String[encontrados.longitud()];
        for (int i = 1; i <= encontrados.longitud(); i++) {
            Expediente e = encontrados.iesimo(i);
            opcionesExp[i-1] = e.getId() + " - " + e.getAsunto();
        }

        String seleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione expediente:",
                "Expedientes encontrados",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesExp,
                opcionesExp[0]
        );
        if (seleccionado == null) return;

        id = seleccionado.split(" - ")[0];
        for (int i = 1; i <= encontrados.longitud(); i++) {
            Expediente e = encontrados.iesimo(i);
            if (e.getId().equals(id)) {
                expedienteSeleccionado = e;
                break;
            }
        }
        // Obtener el trámite asociado al expediente
        Interesado interesado = admin.buscarInteresado(dni);
        if (interesado != null) {
            Tramite tramite = interesado.buscarTramite(expedienteSeleccionado.getId());
            if (tramite != null && tramite.getEstado().equalsIgnoreCase("Finalizado")) {
                JOptionPane.showMessageDialog(null, "Este trámite ya ha sido finalizado. No se puede mover.");
                return;
            }
        }

        for (Dependencia dep : deps) {
            Cola<Expediente> cola = dep.getColaExpedientes();
            Cola<Expediente> aux = new Cola<>();
            while (!cola.esVacia()) {
                Expediente e = cola.desencolar();
                aux.encolar(e);
                if (e.getId().equals(id)) {
                    origen = dep;
                }
            }
            while (!aux.esVacia()) cola.encolar(aux.desencolar());
            if (origen != null) break;
        }

        if (origen == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la dependencia origen del expediente.");
            return;
        }
    }

    // Elegir dependencia de destino
    String destinoNombre = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione dependencia de destino:",
            "Destino",
            JOptionPane.PLAIN_MESSAGE,
            null,
            DEPENDENCIAS,
            DEPENDENCIAS[0]
    );
    if (destinoNombre == null) return;

    if (origen.getNombre().equals(destinoNombre)) {
        JOptionPane.showMessageDialog(null, "La dependencia de origen y destino no pueden ser iguales.");
        return;
    }

    Dependencia destino = buscarDependencia(destinoNombre, deps);
    if (destino == null) {
        JOptionPane.showMessageDialog(null, "Dependencia de destino no encontrada.");
        return;
    }

    String observacion = JOptionPane.showInputDialog("Observaciones:");
    if (observacion == null) return;

    FechaHora fh = fechaActual();

    String error = admin.registrarMovimiento(dni, id, origen, destino, fh, observacion);

    if (error != null) {
        JOptionPane.showMessageDialog(null, error);
    } else {
        JOptionPane.showMessageDialog(null, "Movimiento registrado con éxito.");
        actualizarTablaAlertas(admin, deps);
    }
}







private static void finalizarTramite(Administrador admin, Dependencia[] deps) {
    String dni = leerCampo("DNI del interesado:", "\\d{8}", "DNI inválido.");
    if (dni == null) return;

    Interesado interesado = admin.buscarInteresado(dni);
    if (interesado == null) {
        JOptionPane.showMessageDialog(null, "No se encontró un interesado con ese DNI.");
        return;
    }

    if (interesado.getTramites().esVacia()) {
        JOptionPane.showMessageDialog(null, "Este interesado no tiene trámites registrados.");
        return;
    }

    // Convertimos la pila a una lista para mostrar opciones
    Lista<Tramite> listaTramites = new Lista<>();
    Pila<Tramite> pilaOriginal = interesado.getTramites();
    Pila<Tramite> aux = new Pila<>();

    while (!pilaOriginal.esVacia()) {
        Tramite t = pilaOriginal.desapilar();
        listaTramites.agregar(t);
        aux.apilar(t);
    }
    while (!aux.esVacia()) pilaOriginal.apilar(aux.desapilar());

    // Mostrar opciones
    String[] opciones = new String[listaTramites.longitud()];
    for (int i = 1; i <= listaTramites.longitud(); i++) {
        Tramite t = listaTramites.iesimo(i);
        opciones[i - 1] = t.getExpediente().getId() + " - " + t.getExpediente().getAsunto();
    }

    String seleccionado = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione trámite a finalizar:",
            "Trámites del interesado",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
    );

    if (seleccionado == null) return;

    String id = seleccionado.split(" - ")[0];

    // Pedir documentos generados
    String doc = JOptionPane.showInputDialog("Documentos finales generados (separados por comas):");
    if (doc == null) return;

    Lista<String> documentos = new Lista<>();
    for (String d : doc.split(",")) documentos.agregar(d.trim());

    FechaHora ahora = fechaActual();
    admin.registrarFinalizacionTramite(dni, id, ahora, documentos, deps);

    JOptionPane.showMessageDialog(null, "Trámite finalizado.");
    actualizarTablaAlertas(admin, deps);
}


private static void consultarSeguimiento(Administrador admin) {
    String dni = leerCampo("DNI del interesado:", "\\d{8}", "DNI inválido.");
    if (dni == null) return;

    Interesado interesado = admin.buscarInteresado(dni);
    if (interesado == null) {
        JOptionPane.showMessageDialog(null, "No se encontró un interesado con ese DNI.");
        return;
    }

    if (interesado.getTramites().esVacia()) {
        JOptionPane.showMessageDialog(null, "Este interesado no tiene trámites registrados.");
        return;
    }

    // Obtener trámites en una lista temporal
    Lista<Tramite> listaTramites = new Lista<>();
    Pila<Tramite> pilaOriginal = interesado.getTramites();
    Pila<Tramite> aux = new Pila<>();

    while (!pilaOriginal.esVacia()) {
        Tramite t = pilaOriginal.desapilar();
        listaTramites.agregar(t);
        aux.apilar(t);
    }
    while (!aux.esVacia()) pilaOriginal.apilar(aux.desapilar());

    // Mostrar trámites como opciones
    String[] opciones = new String[listaTramites.longitud()];
    for (int i = 1; i <= listaTramites.longitud(); i++) {
        Tramite t = listaTramites.iesimo(i);
        opciones[i - 1] = t.getExpediente().getId() + " - " + t.getExpediente().getAsunto();
    }

    String seleccionado = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el trámite:",
            "Trámites del interesado",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
    );

    if (seleccionado == null) return;
    String id = seleccionado.split(" - ")[0];

    // Obtener el trámite y expediente seleccionados
    Tramite tramiteSeleccionado = null;
    for (int i = 1; i <= listaTramites.longitud(); i++) {
        Tramite t = listaTramites.iesimo(i);
        if (t.getExpediente().getId().equals(id)) {
            tramiteSeleccionado = t;
            break;
        }
    }

    if (tramiteSeleccionado == null) {
        JOptionPane.showMessageDialog(null, "No se encontró el trámite.");
        return;
    }

    Expediente expediente = tramiteSeleccionado.getExpediente();

    // Estado desde el trámite
    String estado = tramiteSeleccionado.getEstado().equalsIgnoreCase("Finalizado") ? "Finalizado" : "En proceso";
    String inicio = tramiteSeleccionado.getInicio().toString();
    String fin = "...";
    if(tramiteSeleccionado.getFin()!=null){
        fin = tramiteSeleccionado.getFin().toString();
    }
    // Crear tabla de seguimiento
    String[] columnas = {"Origen", "Destino", "Inicio", "Observación"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    JTable tabla = new JTable(modelo);

    Lista<Seguimiento> seguimientos = expediente.getSeguimientos();
    for (int i = 1; i <= seguimientos.longitud(); i++) {
        Seguimiento s = seguimientos.iesimo(i);
        String origen = s.getOrigen() != null ? s.getOrigen().getNombre() : "-";
        String destino = s.getDestino() != null ? s.getDestino().getNombre() : "-";
        String fh = s.getFh().toString();
        String obs = s.getObservacion();
        modelo.addRow(new Object[]{origen, destino, fh, obs});
    }

    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setPreferredSize(new Dimension(600, 200));

    JPanel panelInfo = new JPanel();
    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
    panelInfo.add(new JLabel("Estado del trámite: " + estado));
    panelInfo.add(new JLabel("Fecha Inicio: " + inicio));
    panelInfo.add(new JLabel("Fecha Fin: " + fin));

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(panelInfo, BorderLayout.NORTH);
    panel.add(scroll, BorderLayout.CENTER);

    JOptionPane.showMessageDialog(null, panel, "Seguimiento del Trámite", JOptionPane.INFORMATION_MESSAGE);
}

private static String leerCampo(String mensaje, String regex, String error) {
    while (true) {
        String input = JOptionPane.showInputDialog(mensaje);
        if (input == null) return null;
        if (input.matches(regex)) return input;
        JOptionPane.showMessageDialog(null, error);
    }
}

private static FechaHora fechaActual() {
    LocalDateTime ahora = LocalDateTime.now();
    return new FechaHora(
        ahora.getYear(), ahora.getMonthValue(), ahora.getDayOfMonth(),
        ahora.getHour(), ahora.getMinute(), ahora.getSecond()
    );
}

private static Dependencia buscarDependencia(String nombre, Dependencia[] deps) {
    for (Dependencia d : deps) {
        if (d.getNombre().equals(nombre)) return d;
    }
    return null;
}

private static void actualizarTablaAlertas(Administrador admin, Dependencia[] deps) {
    modeloAlertas.setRowCount(0); // Limpiar tabla
    Lista<Dependencia> deps2 = new Lista<>();
    for(Dependencia d:deps){
        deps2.agregar(d);
    }
    Lista<Expediente> todos = admin.obtenerExpedientesOrdenados(deps2); // ya vienen ordenados

    for (int i = 1; i <= todos.longitud(); i++) {
        Expediente e = todos.iesimo(i);
        // Verificamos el estado del trámite antes de agregar a la tabla
        Interesado interesado = admin.buscarInteresado(e.getInteresado().getDni());
        if (interesado != null) {
            Tramite tramite = interesado.buscarTramite(e.getId());
            if (tramite != null && tramite.getEstado().equalsIgnoreCase("Finalizado")) {
                continue; // Si está finalizado, lo saltamos
            }
        }
        String id = e.getId();
        String asunto = e.getAsunto();
        int prioridad = e.getPrioridad();
        String fecha = obtenerFechaUltimoMovimiento(e);
        String dependencia = obtenerDependenciaActual(e, deps);

        modeloAlertas.addRow(new Object[]{id, asunto, prioridad, fecha, dependencia});
    }
}

private static String obtenerFechaUltimoMovimiento(Expediente e) {
    if (e.getSeguimientos().esVacia()) return "Sin fecha";
    FechaHora fh = e.getSeguimientos().iesimo(e.getSeguimientos().longitud()).getFh();
    return fh.toString(); // Suponiendo que tu clase FechaHora tiene un toString() adecuado
}

private static String obtenerDependenciaActual(Expediente e, Dependencia[] deps) {
    for (Dependencia d : deps) {
        Cola<Expediente> cola = d.getColaExpedientes();
        Cola<Expediente> aux = new Cola<>();
        boolean encontrado = false;

        while (!cola.esVacia()) {
            Expediente exp = cola.desencolar();
            aux.encolar(exp);
            if (exp.getId().equals(e.getId())) {
                encontrado = true;
            }
        }

        while (!aux.esVacia()) cola.encolar(aux.desencolar());

        if (encontrado) return d.getNombre();
    }
    return "Desconocida";
    }   
}

