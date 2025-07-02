/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;
import controlador.*;
import TDA.*;
/**
 *
 * @author Usuario
 */
public class admin {
    public static void main(String[] args) {
        // Crear administrador de prueba
        Administrador admin = new Administrador("adminULima", "P@ssw0rd");

        // Crear algunos interesados
        Interesado i1 = new Interesado("12345678", "Juan Perez", "987654321", "juan@ulima.edu.pe", "ULima");
        Interesado i2 = new Interesado("87654321", "Maria Lopez", "912345678", "maria@externo.com", "Externo");

        // Registrar expedientes
        Expediente e1 = new Expediente("EXP001", 1, i1, "Certificado de Estudios", "DOC-001.pdf");
        Expediente e2 = new Expediente("EXP002", 2, i2, "Constancia Laboral", "DOC-002.pdf");
        admin.registrarExpediente(e1);
        admin.registrarExpediente(e2);

        // Registrar movimientos (seguimientos)
        Seguimiento s1 = new Seguimiento("Mesa de Partes", "Archivo Central", new FechaHora(2025,7,1,9,30,0), "Recibido y archivado");
        admin.registrarMovimiento("EXP001", s1);
        Seguimiento s2 = new Seguimiento("Archivo Central", "Decanato", new FechaHora(2025,7,1,10,15,0), "En revisión");
        admin.registrarMovimiento("EXP001", s2);

        // Finalizar trámite
        Seguimiento t1 = new Seguimiento(e1);
        t1.iniciarTramite(new FechaHora(2025,7,1,9,30,0));
        t1.finalizarTramite(new FechaHora(2025,7,1,11,0,0), "CertEstudios-EXP001.pdf");
        admin.registrarFinalizacionTramite("EXP001", t1);

        // Probar consulta de seguimiento
        System.out.println(admin.proporcionarSeguimiento("EXP001"));

        // Mostrar expedientes registrados
        System.out.println("\n--- Lista de Expedientes ---");
        ListaDoble<Expediente> lista = admin.getExpediente();
        for (int i = 1; i <= lista.longitud(); i++) {
            System.out.println(lista.iesimo(i));
            System.out.println();
        }
    }
}