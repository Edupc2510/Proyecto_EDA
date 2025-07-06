package controlador;

import TDA.*;
import TDANoLineal.ArbolBB;

public class Administrador {
    private String nombreUsuario;
    private String contraseña;
    private ListaDoble<Interesado> interesados;
    private static Administrador instance;
    private static ArbolBB<Tramite> Todos ;
    private static Lista<Tramite> TodosLista;
    
    public static Administrador getInstancia() {
    return instance;
}

public static void setInstancia(String usuario, String clave) {
    if (instance == null) {
        instance = new Administrador(usuario, clave);
    }
}

    public Administrador(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.interesados = new ListaDoble<>();
        TodosLista = new Lista<>();
        Todos = new ArbolBB<>();    
    }
    
    // Registrar un expediente
    public void registrarTramite(String id, int prioridad, Interesado interesado, String asunto, String docReferencia, FechaHora fh, Dependencia destino) {
    Interesado existente = buscarInteresado(interesado.getDni());
    if (existente != null) {
        interesado = existente;
    } else {
        interesados.agregar(interesado);
    }

    Expediente e = new Expediente(id, prioridad, interesado, asunto, docReferencia);
    Seguimiento s = new Seguimiento(null, destino, fh, "Inicio de tramite");
    e.getSeguimientos().agregar(s);
    Tramite t = new Tramite(e, fh);
    interesado.getTramites().apilar(t);

    destino.agregarExpediente(e);
    TodosLista.agregar(t);
    
    System.out.println("interesados en lista antes: " + interesados.longitud());

}

    
    public Interesado buscarInteresado(String dni) {
        for (int i = 1; i <= interesados.longitud(); i++) {
            Interesado in = interesados.iesimo(i);
            System.out.println("Interesado registrado: " + in.getDni());
            if(in.getDni().equals(dni)) return in;
        }
        
        return null;  
    }    
    
    

    
    // Finalizar trámite
public void registrarFinalizacionTramite(String dni, String id, FechaHora fin, Lista<String> documentos, Dependencia[] deps) {
    Interesado in = buscarInteresado(dni);
    if (in != null) {
        Tramite t = in.buscarTramite(id);
        if (t != null){
            t.setFin(fin);
            t.setDocumentos(documentos);
            t.setEstado("Finalizado");
            
            // Eliminar el expediente de la cola donde esté
            Expediente exp = t.getExpediente();
            Dependencia destino = exp.getSeguimientos().iesimo(exp.getSeguimientos().longitud()).getDestino();
            Seguimiento s = new Seguimiento(destino, null, fin, "Finalización de tramite");
            exp.getSeguimientos().agregar(s);    
            destino.eliminarExpediente(id);
        }
    }
}


    // Registrar movimiento
    public String registrarMovimiento(String dni, String id, Dependencia origen, Dependencia destino, FechaHora fh, String observacion) {
        Interesado in = buscarInteresado(dni);
        if (in == null) {
            return "El interesado con DNI " + dni + " no cuenta con trámites pendientes.";
        }

        Tramite t = in.buscarTramite(id);
        if (t == null) {
            return "El trámite con ID " + id + " no existe para este interesado.";
        }

        Seguimiento s = new Seguimiento(origen, destino, fh, observacion);
        t.getExpediente().getSeguimientos().agregar(s);
        origen.eliminarExpediente(id);
        destino.agregarExpediente(t.getExpediente());

        return null; // Todo OK
    }


    // Mostrar seguimiento
    public String proporcionarSeguimiento(String dni, String id) {
        Interesado in = buscarInteresado(dni);
        if (in == null){
            return "El interesado no tiene ningún trámite en curso.";
        }
        Tramite t = in.buscarTramite(id);
        if (t == null){
            return "No existe dicho trámite.";
        }
        return t.getExpediente().mostrarSeguimientos();
    }

    public Lista<Expediente> obtenerExpedientesOrdenados(Lista<Dependencia> deps) {
    Lista<Expediente> todos = new Lista<>();

    for (int i = 1; i <= deps.longitud(); i++) {
        Dependencia dep = deps.iesimo(i);
        Cola<Expediente> cola = dep.getColaExpedientes();
        Cola<Expediente> aux = new Cola<>();

        while (!cola.esVacia()) {
            Expediente e = cola.desencolar();
            todos.agregar(e);
            aux.encolar(e);
        }

        while (!aux.esVacia()) cola.encolar(aux.desencolar());
    }

    ordenarPorPrioridadYFecha(todos);
    return todos;
    }

private void ordenarPorPrioridadYFecha(Lista<Expediente> lista) {
    int n = lista.longitud();
    for (int i = 1; i <= n - 1; i++) {
        for (int j = 1; j <= n - i; j++) {
            Expediente e1 = lista.iesimo(j);
            Expediente e2 = lista.iesimo(j + 1);

            boolean cambiar = false;

            if (e1.getPrioridad() > e2.getPrioridad()) {
                cambiar = true;
            } else if (e1.getPrioridad() == e2.getPrioridad()) {
                FechaHora f1 = obtenerFechaUltimoMovimiento(e1);
                FechaHora f2 = obtenerFechaUltimoMovimiento(e2);
                if (f1 != null && f2 != null && !f1.esAnteriorA(f2)) {
                    cambiar = true;
                }
            }

            if (cambiar) {
                lista.eliminar(j);
                lista.insertar(e2, j);
                lista.eliminar(j + 1);
                lista.insertar(e1, j + 1);
            }
        }
    }
}

    private FechaHora obtenerFechaUltimoMovimiento(Expediente e) {
    Lista<Seguimiento> seguimientos = e.getSeguimientos();
    if (seguimientos.esVacia()) return null;
    return seguimientos.iesimo(seguimientos.longitud()).getFh();
    }   


    @Override
    public String toString() {
        return "Administrador: " + nombreUsuario;
    }
    
    
    
    public void AgregarTramite(Tramite tramite){
        Todos.agregar(tramite);
    }
    
    public ArbolBB GetArbol(){
        return Todos;
    }
    
    
    public Lista<Tramite> obtenerTramitesOrdenados() {
        Lista<Tramite> lista  = TodosLista;
        
        int n = lista.longitud();
        boolean flag;


        for (int i = 1; i < n; i++) {
            flag = false;
            for (int j = 1; j <= n - i; j++) {
                Tramite t1 = lista.iesimo(j);
                Tramite t2 = lista.iesimo(j + 1);

                int prioridad1 = t1.getExpediente().getPrioridad();
                int prioridad2 = t2.getExpediente().getPrioridad();

                if (prioridad1 > prioridad2) {

                    lista.insertar(t2, j);
                    lista.insertar(t1, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
        

     return lista;
    }
    
    
    public Tramite buscarTramitePorID(String id) {
    for (int i = 1; i <= TodosLista.longitud(); i++) {
        Tramite t = TodosLista.iesimo(i);
        if (t.getExpediente().getId().equals(id)) {
            return t;
        }
    }
    return null; 
}


}
