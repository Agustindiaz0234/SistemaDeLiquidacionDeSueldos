
package modelo;


public class Empleado {
    
    int id;
    String nombre;
    String apellido;
    int telefono;
    String mail;
    int empresaId;

    public Empleado(int id, String nombre, String apellido, int telefono, String mail, int empresaId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.empresaId = empresaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
       public int getId() {
        return id;
    }
    
       public void setId(int id) {
        this.id = id;
    }
       
         public int getEmpresaId() {
        return empresaId;
    }
    
       public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }
    
}
