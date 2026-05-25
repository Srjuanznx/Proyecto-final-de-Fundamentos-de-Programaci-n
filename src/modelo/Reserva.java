package modelo;

public class Reserva {
    private Usuario usuario;
    private DestinoEspacial destino;
    private String fechaViaje;
    private String observaciones;

    public Reserva(Usuario usuario, DestinoEspacial destino, String fechaViaje, String observaciones) {
        if (usuario == null || destino == null) {
            throw new IllegalArgumentException("Usuario y destino deben ser válidos.");
        }
        if (fechaViaje == null || fechaViaje.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha del viaje no puede estar vacía.");
        }
        this.usuario = usuario;
        this.destino = destino;
        this.fechaViaje = fechaViaje.trim();
        this.observaciones = observaciones == null ? "" : observaciones.trim();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public DestinoEspacial getDestino() {
        return destino;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
