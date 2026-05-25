package modelo;

public class Viaje {
    private final String itinerario;
    private final String equipoNecesario;
    private final String recomendaciones;

    public Viaje(String itinerario, String equipoNecesario, String recomendaciones) {
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
