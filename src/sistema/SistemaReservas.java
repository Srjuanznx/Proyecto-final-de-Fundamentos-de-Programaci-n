package sistema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.DestinoEspacial;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;

public class SistemaReservas {
    // Declaraciones generales
    private static String prepCsv(String s) /*Prepara texto para llevar al CSV */ {
        if (s == null)
            s = "";
        String esc = s.replace("\"", "\"\"");
        return "\"" + esc + "\"";
    }

    private static List<String> coluCSV(String line) /*Resive del CSV y divide las columnas */ {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());
        return fields;
    }

    private static String unquote(String s) /*Transforma texto en CSV a texto normal */ {
        if (s == null)
            return "";
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
            s = s.substring(1, s.length() - 1);
            s = s.replace("\"\"", "\"");
        }
        return s;
    }

    // Sistema de Usuarios
    private final Path usuariosCsvPath = Paths.get("src", "sistema", "Usuarios.csv");

    private final List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> listarUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    private void guardarUsuarioEnCSV(Usuario usuario) {
        try {
            String linea = String.join(",",
                    prepCsv(usuario.getNombre()),
                    prepCsv(usuario.getCorreo()),
                    prepCsv(usuario.getPreferencias()));
            Files.writeString(usuariosCsvPath, linea + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario registrarUsuario(String nombre, String correo, String preferencias) {
        Usuario usuario = new Usuario(nombre, correo, preferencias);
        usuarios.add(usuario);
        guardarUsuarioEnCSV(usuario);
        return usuario;
    }

    private void cargarUsuarios() {
        try {
            if (Files.exists(usuariosCsvPath)) {
                Files.lines(usuariosCsvPath).forEach(line -> {
                    if (line == null || line.trim().isEmpty())
                        return;
                    // Separar en las 3 propiedades de Usuario
                    String[] parts = line.split(",", 3);
                    String nombre = unquote(parts.length > 0 ? parts[0].trim() : "");
                    String correo = unquote(parts.length > 1 ? parts[1].trim() : "");
                    String preferencias = unquote(parts.length > 2 ? parts[2].trim() : "");
                    try {
                        Usuario u = new Usuario(nombre, correo, preferencias);
                        usuarios.add(u);
                    } catch (IllegalArgumentException ex) {
                        // Ignorar las lineas invalidad y continuar
                    }
                });
            } else {
                // Verificar el archivo existe
                Path parent = usuariosCsvPath.getParent();
                if (parent != null && !Files.exists(parent)) {
                    try {
                        Files.createDirectories(parent);
                    } catch (IOException ignored) {
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //Sistema de destinos
    private final Path destinosCsvPath = Paths.get("src", "sistema", "infoDestinos.csv");

    private final List<DestinoEspacial> destinos = new ArrayList<>();

    public List<DestinoEspacial> listarDestinos() {
        return Collections.unmodifiableList(destinos);
    }

    private void cargarDestinos() {
        if (Files.exists(destinosCsvPath)) {
            try {
                Files.lines(destinosCsvPath).forEach(line -> {
                    if (line == null || line.trim().isEmpty()) {
                        return;
                    }
                    try {
                        List<String> fields = coluCSV(line);
                        if (fields.size() >= 7) {
                            String nombre = unquote(fields.get(0));
                            String descripcion = unquote(fields.get(1));
                            double precio = Double.parseDouble(fields.get(2).trim());
                            int duracionDias = Integer.parseInt(fields.get(3).trim());
                            String itinerario = unquote(fields.get(4));
                            String equipoNecesario = unquote(fields.get(5));
                            String recomendaciones = unquote(fields.get(6));
                            destinos.add(new Viaje(nombre, descripcion, precio, duracionDias,
                                    itinerario, equipoNecesario, recomendaciones));
                        }
                    } catch (Exception ex) {
                        // Ignorar líneas mal formadas y continuar
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

    public Viaje obtenerInformacionViaje(DestinoEspacial destino) {
        if (destino == null) {
            return null;
        }
        if (destino instanceof Viaje viaje) {
            return viaje;
        }
        for (DestinoEspacial d : destinos) {
            if (d.getNombre().equalsIgnoreCase(destino.getNombre()) && d instanceof Viaje viaje) {
                return viaje;
            }
        }
        return new Viaje(
                destino.getNombre(),
                destino.getDescripcion(),
                destino.getPrecio(),
                destino.getDuracionDias(),
                "Itinerario personalizado según el destino seleccionado.",
                "Equipo básico de viaje espacial.",
                "Sigue siempre las indicaciones del personal de vuelo.");
    }


    // Sistema de reservas
    private final Path reservasCsvPath = Paths.get("src", "sistema", "Reservas.csv");

    private final List<Reserva> reservas = new ArrayList<>();

    public List<Reserva> listarReservas() {
        return Collections.unmodifiableList(reservas);
    }

    private void guardarReservaEnCSV(Reserva reserva) {
        try {
            String linea = String.join(",",
                    prepCsv(reserva.getUsuario().getCorreo()),
                    prepCsv(reserva.getDestino().getNombre()),
                    prepCsv(reserva.getFechaViaje()),
                    prepCsv(reserva.getObservaciones()));
            Files.writeString(reservasCsvPath, linea + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Reserva crearReserva(Usuario usuario, DestinoEspacial destino, String fechaViaje, String observaciones) {
        Reserva reserva = new Reserva(usuario, destino, fechaViaje, observaciones);
        reservas.add(reserva);
        guardarReservaEnCSV(reserva);
        return reserva;
    }

        private void cargarReservas() {
        if (Files.exists(reservasCsvPath)) {
            try {
                Files.lines(reservasCsvPath).forEach(line -> {
                    if (line == null || line.trim().isEmpty()) {
                        return;
                    }
                    try {
                        List<String> fields = coluCSV(line);
                        if (fields.size() >= 4) {
                            String correoUsuario = unquote(fields.get(0));
                            String nombreDestino = unquote(fields.get(1));
                            String fechaViaje = unquote(fields.get(2));
                            String observaciones = unquote(fields.get(3));

                            Usuario usuario = buscarUsuarioPorCorreo(correoUsuario);
                            DestinoEspacial destino = buscarDestino(nombreDestino);
                            if (usuario != null && destino != null) {
                                reservas.add(new Reserva(usuario, destino, fechaViaje, observaciones));
                            }
                        }
                    } catch (Exception ex) {
                        // Ignorar líneas mal formadas y continuar
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Usuario buscarUsuarioPorCorreo(String correo) {
        if (correo == null) {
            return null;
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo.trim())) {
                return usuario;
            }
        }
        return null;
    }

    // Sitema a llamar
    public SistemaReservas() {
        cargarDestinos();
        cargarUsuarios();
        cargarReservas();
    }
}
