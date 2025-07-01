/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import controlador.*;
import javax.swing.*;
import TDA.ListaDoble;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class AppGUI {
    private static final String USUARIO_ADMIN = "20232157";
    private static final String CLAVE_ADMIN = "1234";
    private static final String[] DEPENDENCIAS = {
        "Mesa de Partes", "Archivo Central", "Decanato", "Tesorería",
        "Biblioteca", "Bienestar Universitario", "Secretaría Académica", "Oficina de Graduados"
    };
    private static final String[] TIPOS = {"ULIMA", "EXTERNO"};
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.(com|edu\\.pe)$");

    public static void main(String[] args) {
            boolean loginExitoso = false;
        for (int intentos = 0; intentos < 3; intentos++) {
        String usuario = JOptionPane.showInputDialog("Usuario:");
        String clave = JOptionPane.showInputDialog("Contraseña:");

        if (USUARIO_ADMIN.equals(usuario) && CLAVE_ADMIN.equals(clave)) {
            loginExitoso = true;
            break;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intente nuevamente.");
        }
    }

    if (!loginExitoso) {
        JOptionPane.showMessageDialog(null, "Demasiados intentos. Saliendo del sistema.");
        System.exit(0);
    }
        Administrador admin = new Administrador(USUARIO_ADMIN, CLAVE_ADMIN);
        boolean salir = false;

        while (!salir) {
            String[] opcionesMenu = {
                "Registrar Expediente",
                "Registrar Movimiento",
                "Finalizar Trámite",
                "Consultar Seguimiento",
                "Listar Expedientes",
                "Salir"
            };
            int seleccionMenu = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Gestión Trámites ULima",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesMenu,
                opcionesMenu[0]
            );

            if (seleccionMenu == JOptionPane.CLOSED_OPTION || seleccionMenu == 5) {
                salir = true;
                break;
            }

            switch (seleccionMenu) {
                case 0:
                    registrarExpedienteGUI(admin);
                    mostrarAlertasExpedientesNoAtendidos(admin);
                    break;
                case 1:
                    registrarMovimientoGUI(admin);
                    mostrarAlertasExpedientesNoAtendidos(admin);
                    break;
                case 2:
                    finalizarTramiteGUI(admin);
                    mostrarAlertasExpedientesNoAtendidos(admin);
                    break;
                case 3:
                    consultarSeguimientoGUI(admin);
                    mostrarAlertasExpedientesNoAtendidos(admin);
                    break;
                case 4:
                    listarExpedientesGUI(admin);
                    mostrarAlertasExpedientesNoAtendidos(admin);
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema! ♥");
        System.exit(0);
    }

        private static void registrarExpedienteGUI(Administrador admin) {
    String id;
    while (true) {
        id = JOptionPane.showInputDialog("ID del Expediente:");
        if (id == null) return;

        // Verificar si ya existe un expediente con ese ID
        if (admin.buscarExpediente(id) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un expediente con ese ID. Intente con otro.");
            continue;
        }

    if (id.matches("^[A-Za-z0-9_-]+$")) break;
    JOptionPane.showMessageDialog(null, "Ingrese un ID válido.");
}
    String[] prioridades = {"1", "2", "3", "4", "5"};
    String prioSeleccionada = (String) JOptionPane.showInputDialog(
        null,
        "Seleccione la prioridad:",
        "Prioridad",
        JOptionPane.PLAIN_MESSAGE,
        null,
        prioridades,
        prioridades[0]
    );
    if (prioSeleccionada == null) return;
    int prio = Integer.parseInt(prioSeleccionada);

    String dni;
    while (true) {
        dni = JOptionPane.showInputDialog("DNI del Interesado:");
        if (dni == null) return;
        if (dni.matches("\\d{8}")) break;
        JOptionPane.showMessageDialog(null, "Ingrese un DNI válido (8 dígitos).");
    }

    String nom;
    while (true) {
        nom = JOptionPane.showInputDialog("Nombre del Interesado:");
        if (nom == null) return;
        if (nom.matches("^[^\\d]+$")) break;
        JOptionPane.showMessageDialog(null, "Ingrese un nombre válido.");
    }

    String tel;
    while (true) {
        tel = JOptionPane.showInputDialog("Teléfono:");
        if (tel == null) return;
        if (tel.matches("\\d{9}")) break;
        JOptionPane.showMessageDialog(null, "Ingrese un teléfono válido (9 dígitos).");
    }

    String em;
    while (true) {
        em = JOptionPane.showInputDialog("Email:");
        if (em == null) return;
        if (EMAIL_PATTERN.matcher(em).matches()) break;
        JOptionPane.showMessageDialog(null, "Ingrese un correo válido.");
    }

    String tipo = (String) JOptionPane.showInputDialog(
        null,
        "Seleccione tipo de interesado:",
        "Tipo de Interesado",
        JOptionPane.PLAIN_MESSAGE,
        null,
        TIPOS,
        TIPOS[0]
    );
    if (tipo == null) return;

    String asu;
    while (true) {
        asu = JOptionPane.showInputDialog("Asunto:");
        if (asu == null) return;
        if (asu.matches("^[^\\d]+$")) break;
        JOptionPane.showMessageDialog(null, "Ingrese un asunto válido.");
    }

    String doc;
    while (true) {
        doc = JOptionPane.showInputDialog("Documento de referencia:");
        if (doc == null) return;
        if (doc.matches("^[A-Za-z0-9._-]+$")) break;
        JOptionPane.showMessageDialog(null, "Ingrese un documento válido.");
    }

    Interesado i = new Interesado(dni, nom, tel, em, tipo);
    Expediente e = new Expediente(id, prio, i, asu, doc);
    admin.registrarExpediente(e);
    JOptionPane.showMessageDialog(null, "Expediente registrado con éxito.");
}


    private static void registrarMovimientoGUI(Administrador admin) {
        ListaDoble<Expediente> lista = admin.getExpediente();
        if (lista.longitud() == 0) {
            JOptionPane.showMessageDialog(null, "No hay expedientes registrados.");
            return;
        }
        String id = JOptionPane.showInputDialog("ID del Expediente:");
        Expediente exp = admin.buscarExpediente(id);
        if (exp == null) {
            JOptionPane.showMessageDialog(null, "Expediente no encontrado.");
            return;
        }
        String destino = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione dependencia destino:",
            "Destino",
            JOptionPane.PLAIN_MESSAGE,
            null,
            DEPENDENCIAS,
            DEPENDENCIAS[0]
        );
        if (destino == null) return;

        LocalDateTime ahora = LocalDateTime.now();
        FechaHora fh = new FechaHora(
            ahora.getYear(), ahora.getMonthValue(), ahora.getDayOfMonth(),
            ahora.getHour(), ahora.getMinute(), ahora.getSecond()
        );
        String origen = exp.getSeguimientos().longitud() > 0
            ? exp.getSeguimientos().iesimo(exp.getSeguimientos().longitud()).getDependenciaDestino()
            : DEPENDENCIAS[0];
        String obs = JOptionPane.showInputDialog("Observaciones:");
        Seguimiento s = new Seguimiento(origen, destino, fh, obs);
        admin.registrarMovimiento(id, s);
        JOptionPane.showMessageDialog(
            null,
            "Movimiento registrado a las " + ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    private static void finalizarTramiteGUI(Administrador admin) {
        String id = JOptionPane.showInputDialog("ID del Expediente:");
        Expediente exp = admin.buscarExpediente(id);
        if (exp == null) {
            JOptionPane.showMessageDialog(null, "Expediente no encontrado.");
            return;
        }
        LocalDateTime ahora = LocalDateTime.now();
        FechaHora inicio = new FechaHora(
            ahora.getYear(), ahora.getMonthValue(), ahora.getDayOfMonth(),
            ahora.getHour(), ahora.getMinute(), ahora.getSecond()
        );
        FechaHora fin = inicio;
        String docFin = JOptionPane.showInputDialog("Documento final generado:");
        Tramite t = new Tramite(exp);
        t.iniciarTramite(inicio);
        t.finalizarTramite(fin, docFin);
        admin.registrarFinalizacionTramite(id, t);
        JOptionPane.showMessageDialog(
            null,
            "Trámite finalizado a las " + ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    private static void consultarSeguimientoGUI(Administrador admin) {
        String id = JOptionPane.showInputDialog("ID del Expediente:");
        String seguimiento = admin.proporcionarSeguimiento(id);
        JOptionPane.showMessageDialog(null, seguimiento);
    }

    private static void listarExpedientesGUI(Administrador admin) {
        ListaDoble<Expediente> lista = admin.getExpediente();
        if (lista.longitud() == 0) {
            JOptionPane.showMessageDialog(null, "No hay expedientes registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lista.longitud(); i++) {
            sb.append(lista.iesimo(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
    private static void intercambiar(ListaDoble<Expediente> lista, int i, int j) {
    if (i == j) return;
    if (i > j) {
        int temp = i;
        i = j;
        j = temp;
    }

    Expediente expI = lista.iesimo(i);
    Expediente expJ = lista.iesimo(j);

    lista.eliminar(j); // siempre eliminar el mayor índice primero
    lista.eliminar(i);
    lista.insertar(expJ, i);
    lista.insertar(expI, j);
    }

    private static void mostrarAlertasExpedientesNoAtendidos(Administrador admin) {
    ListaDoble<Expediente> todos = admin.getExpediente();
    ListaDoble<Expediente> pendientes = new ListaDoble<>();

    // 1. Filtrar solo expedientes sin trámites
    for (int i = 1; i <= todos.longitud(); i++) {
        Expediente e = todos.iesimo(i);
        if (e.getTramites().longitud() == 0) {
            pendientes.agregar(e);
        }
    }

    // 2. Ordenar por prioridad ascendente (1 es más urgente) y luego por antigüedad (orden de registro)
    for (int i = 1; i < pendientes.longitud(); i++) {
        for (int j = i + 1; j <= pendientes.longitud(); j++) {
            Expediente ei = pendientes.iesimo(i);
            Expediente ej = pendientes.iesimo(j);

            if (ei.getPrioridad() > ej.getPrioridad()) {
                intercambiar(pendientes, i, j);
            }
        }
    }

    // 3. Mostrar resultado
    if (pendientes.longitud() == 0) {
        return;
    }

    StringBuilder sb = new StringBuilder("⚠ Expedientes pendientes:\n\n");
    for (int i = 1; i <= pendientes.longitud(); i++) {
        Expediente e = pendientes.iesimo(i);
        sb.append("ID: ").append(e.getId()).append("\n");
        sb.append("Prioridad: ").append(e.getPrioridad()).append("\n");
        sb.append("Interesado: ").append(e.getInteresado().getNombre()).append("\n");
        sb.append("Asunto: ").append(e.getAsunto()).append("\n");
        sb.append("---------------------------\n");
    }

    JOptionPane.showMessageDialog(null, sb.toString());
    }

    
}

