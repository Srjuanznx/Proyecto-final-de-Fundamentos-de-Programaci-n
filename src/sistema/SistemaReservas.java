package sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.DestinoEspacial;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public class SistemaReservas {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<DestinoEspacial> destinos = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    public SistemaReservas() {
        cargarDestinos();
    }

    private void cargarDestinos() {
        destinos.add(new DestinoEspacial(
                "Luna",
                "Viaje de 3 días a la estación lunar con caminata en la superficie.",
                9500.0,
                3));

        destinos.add(new DestinoEspacial(
                "Marte",
                "Viaje de 7 días con visita a la base marciana y observación de cráteres.",
                21000.0,
                7));

        destinos.add(new DestinoEspacial(
                "Estación Orbital",
                "Estancia de 5 días en una estación espacial con laboratorio y vistas al cosmos.",
                13500.0,
                5));
    }

    public Usuario registrarUsuario(String nombre, String correo, String preferencias) {
        Usuario usuario = new Usuario(nombre, correo, preferencias);
        usuarios.add(usuario);
        return usuario;
    }

    public List<DestinoEspacial> listarDestinos() {
        return Collections.unmodifiableList(destinos);
    }

    public List<Usuario> listarUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public List<Reserva> listarReservas() {
        return Collections.unmodifiableList(reservas);
    }

    public DestinoEspacial buscarDestino(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (DestinoEspacial destino : destinos) {
            if (destino.getNombre().equalsIgnoreCase(nombre.trim())) {
                return destino;
            }
        }
        return null;
    }

    public Reserva crearReserva(Usuario usuario, DestinoEspacial destino, String fechaViaje, String observaciones) {
        Reserva reserva = new Reserva(usuario, destino, fechaViaje, observaciones);
        reservas.add(reserva);
        return reserva;
    }

    public Viaje obtenerInformacionViaje(DestinoEspacial destino) {
        if (destino == null) {
            return null;
        }
        String nombre = destino.getNombre();
        return switch (nombre.toLowerCase()) {
            case "luna" -> new Viaje(
                    "Día 1: Entrenamiento y despegue. Día 2: Llegada a la Luna y caminata. Día 3: Regreso a la nave y retorno.",
                    "Traje espacial, casco, guantes térmicos, agua y cámara de viaje.",
                    "Descansa bien antes del viaje y sigue las instrucciones de la tripulación.");

            case "marte" -> new Viaje(
                    "Día 1: Despegue. Día 2: En ruta a Marte. Día 3: Aterrizaje. Días 4-6: Visitas a la base y al cráter. Día 7: Regreso.",
                    "Traje presurizado, zapatos magnéticos y reglas de seguridad de la base.",
                    "Mantente hidratado y no te separes del grupo.");

            case "estación orbital" -> new Viaje(
                    "Día 1: Despegue y acoplamiento. Días 2-4: Experimentos y turismo orbital. Día 5: Regreso a Tierra.",
                    "Ropa cómoda, kit de higiene y cargador para dispositivos.",
                    "Disfruta de la vista y sigue las normas de la estación.");

            default -> new Viaje(
                    "Itinerario personalizado según el destino seleccionado.",
                    "Equipo básico de viaje espacial.",
                    "Sigue siempre las indicaciones del personal de vuelo.");
        };
    }
}
