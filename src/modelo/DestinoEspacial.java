package modelo;

public class DestinoEspacial {
    private final String nombre;
    private final String descripcion;
    private final double precio;
    private final int duracionDias;

    public DestinoEspacial(String nombre, String descripcion, double precio, int duracionDias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionDias = duracionDias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDuracionDias() {
        return duracionDias;
    }
}
