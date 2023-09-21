/**
 * Esta clase representa un salón con capacidad y horario asociado.
 */
public class Salon {
    // Atributos del salón
    private int idSalon;
    private int capacidad;
    private Horario horario;

    /**
     * Constructor de la clase Salon.
     *
     * @param idSalon   El identificador único del salón.
     * @param capacidad La capacidad del salón.
     * @param horario   El horario del salón.
     */
    public Salon(int idSalon, int capacidad, Horario horario) {
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.horario = horario;
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
     * Obtiene el horario del salón.
     *
     * @return El horario del salón.
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Verifica si el horario del salón está disponible.
     *
     * @return true si el horario del salón está disponible, false en caso contrario.
     */
    public boolean horarioDisponible() {
        return horario.isDisponible();
    }

    /**
     * Asigna un horario al salón con una hora de inicio y una hora de fin.
     *
     * @param horaInicio La hora de inicio del horario a asignar.
     * @param horaFin    La hora de fin del horario a asignar.
     */
    public void asignarHorario(int horaInicio, int horaFin) {
        horario.asignarHorario(horaInicio, horaFin);
    }

    /**
     * Imprime la información del salón, incluyendo su identificador, capacidad y disponibilidad de horario.
     */
    public void imprimirInformacionSalon() {
        System.out.println("Salon " + idSalon);
        System.out.println("Capacidad: " + capacidad);
        System.out.println("Horario Disponible: " + (horarioDisponible() ? "Si" : "No"));
    }
}