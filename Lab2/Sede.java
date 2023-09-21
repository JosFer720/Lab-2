/**
 * Esta clase representa una sede que puede contener varios edificios.
 */
public class Sede {
    // Atributos de la sede
    private int idSede;
    private String nombre;
    private List<Edificio> edificios;

    /**
     * Constructor de la clase Sede.
     *
     * @param idSede  El identificador único de la sede.
     * @param nombre  El nombre de la sede.
     */
    public Sede(int idSede, String nombre) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.edificios = new ArrayList<>();
    }

    // Getters y setters

    /**
     * Obtiene el identificador único de la sede.
     *
     * @return El identificador único de la sede.
     */
    public int getIdSede() {
        return idSede;
    }

    /**
     * Obtiene el nombre de la sede.
     *
     * @return El nombre de la sede.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de edificios asociados a la sede.
     *
     * @return La lista de edificios de la sede.
     */
    public List<Edificio> getEdificios() {
        return edificios;
    }

    /**
     * Establece el identificador único de la sede.
     *
     * @param idSede El nuevo identificador único de la sede.
     */
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    /**
     * Establece el nombre de la sede.
     *
     * @param nombre El nuevo nombre de la sede.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Agrega un edificio a la lista de edificios de la sede.
     *
     * @param nombreEdificio El nombre del edificio a agregar.
     * @param nivel          El nivel del edificio a agregar.
     */
    public void agregarEdificio(String nombreEdificio, int nivel) {
        Edificio edificio = new Edificio(nombreEdificio, nivel);
        edificios.add(edificio);
    }

    /**
     * Agrega un salón a un edificio específico dentro de la sede.
     *
     * @param idSalon       El identificador único del salón.
     * @param capacidad     La capacidad del salón.
     * @param horario       El horario del salón.
     * @param nombreEdificio El nombre del edificio al que se va a agregar el salón.
     */
    public void agregarSalon(int idSalon, int capacidad, Horario horario, String nombreEdificio) {
        // ...
    }

    /**
     * Busca un salón disponible para un curso en los edificios de la sede.
     *
     * @param curso El curso para el que se busca un salón disponible.
     * @return Un salón disponible para el curso o null si no se encuentra ninguno.
     */
    public Salon buscarSalonDisponible(Curso curso) {
        // ...
    }

    /**
     * Imprime la información de la sede, incluyendo sus edificios.
     */
    public void imprimirInformacionSede() {
        // ...
    }
}