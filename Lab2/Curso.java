import java.util.List;

/**
 * Esta clase representa un curso con información sobre su horario, duración, días, capacidad de estudiantes y el salón asignado.
 */
public class Curso {
    // Atributos del curso
    private int idCurso;
    private int idSede;  
    private String nombreCurso;
    private int horario;
    private int duracion;
    private List<String> dias;
    private int cantidadEstudiantes;
    private Salon salonAsignado;

    /**
     * Constructor de la clase Curso.
     *
     * @param idCurso            El identificador único del curso.
     * @param idSede             El identificador de la sede a la que pertenece el curso.
     * @param nombreCurso        El nombre del curso.
     * @param horario            El horario del curso.
     * @param duracion           La duración del curso en horas.
     * @param dias               Los días en los que se lleva a cabo el curso.
     * @param cantidadEstudiantes La cantidad de estudiantes inscritos en el curso.
     */
    public Curso(int idCurso, int idSede, String nombreCurso, int horario, int duracion, List<String> dias, int cantidadEstudiantes) {
        this.idCurso = idCurso;
        this.idSede = idSede;  
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    /**
     * Obtiene el identificador único del curso.
     *
     * @return El identificador único del curso.
     */
    public int getIdCurso() {
        return idCurso;
    }

    /**
     * Obtiene el identificador de la sede a la que pertenece el curso.
     *
     * @return El identificador de la sede.
     */
    public int getIdSede() {
        return idSede;  
    }

    /**
     * Obtiene el nombre del curso.
     *
     * @return El nombre del curso.
     */
    public String getNombreCurso() {
        return nombreCurso;
    }

    /**
     * Obtiene el horario del curso.
     *
     * @return El horario del curso.
     */
    public int getHorario() {
        return horario;
    }

    /**
     * Obtiene la duración del curso en horas.
     *
     * @return La duración del curso en horas.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene los días en los que se lleva a cabo el curso.
     *
     * @return La lista de días del curso.
     */
    public List<String> getDias() {
        return dias;
    }

    /**
     * Obtiene la cantidad de estudiantes inscritos en el curso.
     *
     * @return La cantidad de estudiantes inscritos.
     */
    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    /**
     * Obtiene el salón asignado para el curso.
     *
     * @return El salón asignado o null si no se ha asignado ningún salón.
     */
    public Salon getSalonAsignado() {
        return salonAsignado;
    }

    /**
     * Asigna un salón al curso.
     *
     * @param salon El salón a asignar al curso.
     */
    public void asignarSalon(Salon salon) {
        this.salonAsignado = salon;
    }

    /**
     * Verifica si el curso tiene un salón asignado.
     *
     * @return true si el curso tiene un salón asignado, false en caso contrario.
     */
    public boolean tieneSalonAsignado() {
        return salonAsignado != null;
    }

    /**
     * Valida si el horario del curso se encuentra dentro de un rango de horas dado.
     *
     * @param horaInicio La hora de inicio del rango.
     * @param horaFin    La hora de fin del rango.
     * @return true si el horario del curso está dentro del rango, false en caso contrario.
     */
    public boolean validarHorario(int horaInicio, int horaFin) {
        return horario >= horaInicio && (horario + duracion) <= horaFin;
    }

    /**
     * Verifica si la capacidad del salón asignado es suficiente para la cantidad de estudiantes del curso.
     *
     * @param capacidadSalon La capacidad del salón asignado.
     * @return true si la capacidad del salón es suficiente, false en caso contrario.
     */
    public boolean verificarCapacidad(int capacidadSalon) {
        return cantidadEstudiantes <= capacidadSalon;
    }

    /**
     * Imprime la información del curso, incluyendo su nombre, horario, días y cantidad de estudiantes.
     */
    public void imprimirInformacionCurso() {
        System.out.println("Curso: " + nombreCurso);
        System.out.println("Horario: " + horario + " a " + (horario + duracion));
        System.out.println("Días: " + String.join(", ", dias));
        System.out.println("Cantidad de Estudiantes: " + cantidadEstudiantes);
    }
}