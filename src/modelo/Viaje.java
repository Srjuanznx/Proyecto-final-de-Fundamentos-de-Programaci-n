package modelo;

public class Viaje extends DestinoEspacial {
    private final String itinerario;
    private final String equipoNecesario;
    private final String recomendaciones;

    public Viaje(String nombre, String descripcion, double precio, int duracionDias, String itinerario,
            String equipoNecesario, String recomendaciones) {
        super(nombre, descripcion, precio, duracionDias);
        this.itinerario = itinerario;
        this.equipoNecesario = equipoNecesario;
        this.recomendaciones = recomendaciones;
    }

    public String getItinerario() {
        return itinerario;
    }

    public String getEquipoNecesario() {
        return equipoNecesario;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }
}
