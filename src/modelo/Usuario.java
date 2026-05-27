package modelo;

public class Usuario {
    private String nombre;
    private String correo;
    private String preferencias;

    public Usuario(String nombre, String correo, String preferencias) {
        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setPreferencias(preferencias);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!nombre.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "El nombre solo puede contener letras y espacios."
            );
        }
        this.nombre = nombre.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty() || !correo.contains("@") || (!correo.contains(".com") || !correo.contains(".co"))) {
            throw new IllegalArgumentException("El correo no es válido.");
        }
        this.correo = correo.trim();
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        if (preferencias == null) {
            preferencias = "";
        }
        this.preferencias = preferencias.trim();
    }
}
