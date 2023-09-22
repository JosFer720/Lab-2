/**
 * Esta clase representa un salón con capacidad.
 */
public class Salon {
    // Atributos del salón
    private int idSalon;
    private int capacidad;
    private boolean ocupado;

    /**
     * Constructor de la clase Salon.
     *
     * @param idSalon   El identificador único del salón.
     * @param capacidad La capacidad del salón.
     */
    public Salon(int idSalon, int capacidad) {
        this.idSalon = idSalon;
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el identificador único del salón.
     *
     * @return El identificador único del salón.
     */
    public int getIdSalon() {
        return idSalon;
    }

    /**
     * Obtiene la capacidad del salón.
     *
     * @return La capacidad del salón.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Verifica si el salón está ocupado.
     *
     * @return true si el salón está ocupado, false si no lo está.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Ocupa el salón, estableciendo su estado como ocupado.
     */
    public void ocupar() {
        ocupado = true;
    }

    /**
     * Desocupa el salón, estableciendo su estado como desocupado.
     */
    public void desocupar() {
        ocupado = false;
    }

    /**
     * Imprime la información del salón, incluyendo su identificador y capacidad.
     */
    public void imprimirInformacionSalon() {
        System.out.println("Salon " + idSalon);
        System.out.println("Capacidad: " + capacidad);
    }
}