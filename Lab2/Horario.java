/**
 * Esta clase representa un horario de disponibilidad, especificando las horas en las que está disponible.
 */
public class Horario {
    // Atributos del horario
    private int horaInicio;
    private int horaFin;
    private boolean[] disponibilidad;

    /**
     * Constructor de la clase Horario.
     *
     * @param horaInicio
     * @param horaFin
     */
    public Horario(int horaInicio, int horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponibilidad = new boolean[15];

        for (int i = 0; i < disponibilidad.length; i++) {
            disponibilidad[i] = true;
        }
    }

    /**
     * Obtiene la hora de inicio del horario.
     *
     * @return La hora de inicio del horario.
     */
    public int getHoraInicio() {
        return horaInicio;
    }

    /**
     * Obtiene la hora de fin del horario.
     *
     * @return La hora de fin del horario.
     */
    public int getHoraFin() {
        return horaFin;
    }

    /**
     * Verifica si el horario está disponible en alguna de las horas especificadas.
     *
     * @return true si el horario está disponible en alguna hora, false en caso contrario.
     */
    public boolean isDisponible() {
        for (int i = horaInicio; i < horaFin; i++) {
            if (disponibilidad[i - 7]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asigna un horario como no disponible en las horas especificadas.
     *
     * @param horaInicio La hora de inicio del horario no disponible.
     * @param horaFin    La hora de fin del horario no disponible.
     * @return true si se pudo asignar el horario no disponible, false si los valores son inválidos.
     */
    public boolean asignarHorario(int horaInicio, int horaFin) {
        if (horaInicio < 7 || horaFin > 21 || horaInicio >= horaFin) {
            return false; // Valores de hora inválidos
        }
        for (int i = horaInicio - 7; i < horaFin - 7; i++) {
            disponibilidad[i] = false;
        }
        return true;
    }

    /**
     * Imprime el horario de disponibilidad, mostrando las horas y su estado de disponibilidad.
     */
    public void imprimirHorario() {
        System.out.println("Horario de disponibilidad:");
        System.out.println("Hora\tDisponibilidad");
        for (int i = 0; i < disponibilidad.length; i++) {
            int hora = 7 + i;
            System.out.println(hora + ":00\t" + (disponibilidad[i] ? "Disponible" : "No disponible"));
        }
    }
}