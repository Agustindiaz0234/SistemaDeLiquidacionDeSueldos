
package modelo;

public class Recibo {
    
    int id;
    String nombre;
    byte[] archivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Recibo(int id, String nombre, byte[] archivo) {
        this.id = id;
        this.nombre = nombre;
        this.archivo = archivo;
    }
    
    
    
}
