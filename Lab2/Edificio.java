import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un edificio que puede contener varios salones.
 */
public class Edificio {
    // Atributos del edificio
    private String nombre;
    private int nivel;
    private List<Salon> salones;

    /**
     * Constructor de la clase Edificio.
     *
     * @param nombre El nombre del edificio.
     * @param nivel  El nivel del edificio.
     */
    public Edificio(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salones = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del edificio.
     *
     * @return El nombre del edificio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nivel del edificio.
     *
     * @return El nivel del edificio.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene la lista de salones dentro del edificio.
     *
     * @return La lista de salones del edificio.
     */
    public List<Salon> getSalones() {
        return salones;
    }

    /**
     * Agrega un salón al edificio.
     *
     * @param idSalon   El identificador único del salón.
     * @param capacidad La capacidad del salón.
     * @param horario   El horario del salón.
     */
    public void agregarSalon(int idSalon, int capacidad, Horario horario) {
        // ...
    }

    /**
     * Busca un salón en el edificio por su identificador único.
     *
     * @param idSalon El identificador único del salón a buscar.
     * @return El salón encontrado o null si no se encuentra.
     */
    public Salon buscarSalonPorId(int idSalon) {
        // ...
    }

    /**
     * Busca salones disponibles en el edificio (con horarios disponibles).
     *
     * @return Una lista de salones disponibles en el edificio.
     */
    public List<Salon> buscarSalonesDisponibles() {
        // ...
    }

    /**
     * Imprime la información del edificio, incluyendo sus salones.
     */
    public void imprimirInformacionEdificio() {
        // ...
    }
}