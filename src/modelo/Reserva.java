package modelo;

import java.time.LocalDate;
import java.time.format.*;

public class Reserva {
    private Usuario usuario;
    private DestinoEspacial destino;
    private String fechaViaje;
    private String observaciones;

    public Reserva(Usuario usuario, DestinoEspacial destino, String fechaViaje, String observaciones) {
        // Validar que el usuario y el destino son validos
        if (usuario == null || destino == null) {
            throw new IllegalArgumentException("Usuario y destino deben ser válidos.");
        }
        // Validador de formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(fechaViaje.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "La fecha debe tener formato dd/MM/aaaa válido.");
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
