
package controlador;

public class Interesado {
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private String tipo;
    
    // Constructor
    public Interesado(String dni, String nombre, String telefono, String email, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
    }
    
    // Getters y Setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "Interesado:\n" +
               "  DNI: " + dni + "\n" +
               "  Nombre: " + nombre + "\n" +
               "  Teléfono: " + telefono + "\n" +
               "  Email: " + email + "\n" +
               "  Tipo: " + tipo;
    }
    
}
