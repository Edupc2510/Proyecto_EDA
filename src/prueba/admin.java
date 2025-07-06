/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import TDA.*;
import controlador.*;

public class admin {
    public static void main(String[] args) {
        // Crear dependencias
        Dependencia mesaPartes = new Dependencia("Mesa de Partes");
        Dependencia secretaria = new Dependencia("Secretaría");
        Dependencia direccion = new Dependencia("Dirección");

        // Crear administrador
        Administrador admin = new Administrador("admin", "123");

        // Crear interesados
        Interesado edu = new Interesado("12345678", "Eduardo Pantoja", "999999999", "edu@correo.com", "Estudiante");
        Interesado luis = new Interesado("87654321", "Luis Vargas", "988888888", "luis@correo.com", "Externo");

        // Contador automático para IDs
        int idCounter = 1;

        // Crear fecha de inicio
        FechaHora fh1 = new FechaHora(2025, 7, 2, 10, 0, 0);
        FechaHora fh2 = new FechaHora(2025, 7, 2, 10, 30, 0);

        // Registrar trámites
        admin.registrarTramite("T" + idCounter++, 2, edu, "Solicitud de constancia", "DOC001", fh1, mesaPartes);
        admin.registrarTramite("T" + idCounter++, 1, luis, "Reclamo de pago", "DOC002", fh2, mesaPartes);

        // Mostrar expedientes en Mesa de Partes
        System.out.println("EXPEDIENTES EN MESA DE PARTES:");
        System.out.println(mesaPartes.mostrarExpedientes());

        // Registrar movimiento de Eduardo de Mesa de Partes → Secretaría
        FechaHora mov1Inicio = new FechaHora(2025, 7, 2, 11, 0, 0);
        admin.registrarMovimiento("12345678", "T1", mesaPartes, secretaria, mov1Inicio, "Derivado a Secretaría para firma");

        // Mostrar expedientes en cada dependencia
        System.out.println("EXPEDIENTES EN MESA DE PARTES (después del movimiento):");
        System.out.println(mesaPartes.mostrarExpedientes());

        System.out.println("EXPEDIENTES EN SECRETARÍA:");
        System.out.println(secretaria.mostrarExpedientes());

        // Mostrar seguimiento del trámite de Eduardo
        System.out.println("SEGUIMIENTO DEL TRÁMITE T1:");
        System.out.println(admin.proporcionarSeguimiento("12345678", "T1"));

        // Finalizar el trámite de Luis
        FechaHora finLuis = new FechaHora(2025, 7, 3, 9, 0, 0);
        Lista<String> docsLuis = new Lista<>();
        docsLuis.agregar("DOC-FINAL-001");
        Dependencia[] deps = new Dependencia[3];
        deps[0]= mesaPartes;
        deps[1]= secretaria;
        deps[2]= direccion;

        admin.registrarFinalizacionTramite("87654321", "T2", finLuis, docsLuis, deps);

        // Verificar estado final
        Tramite tramiteLuis = luis.buscarTramite("T2");
        System.out.println("ESTADO FINAL DEL TRÁMITE DE LUIS:");
        System.out.println(tramiteLuis);
    }

    public static Lista<Tramite> obtenerTramitesOrdenados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Tramite buscarTramitePorID(String idExpediente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
