import java.util.List;
import java.util.ArrayList;

/**
 * La clase Edificio representa un edificio con un nombre, un nivel y una lista de salones.
 */
public class Edificio {
    private String nombre;       // Nombre del edificio
    private int nivel;           // Nivel del edificio
    private List<Salon> salones; // Lista de salones en el edificio

    /**
     * Constructor para crear un objeto Edificio.
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
     * Obtiene la lista de salones en el edificio.
     *
     * @return La lista de salones en el edificio.
     */
    public List<Salon> getSalones() {
        return salones;
    }

    /**
     * Agrega un nuevo salón al edificio con el ID y la capacidad especificados.
     *
     * @param idSalon   El ID del salón a agregar.
     * @param capacidad La capacidad del salón a agregar.
     */
    public void agregarSalon(int idSalon, int capacidad) {
        Salon salon = new Salon(idSalon, capacidad);
        salones.add(salon);
    }

    /**
     * Busca y devuelve una lista de salones disponibles en el edificio.
     *
     * @return Una lista de salones disponibles en el edificio.
     */
    public List<Salon> buscarSalonesDisponibles() {
        List<Salon> salonesDisponibles = new ArrayList<>();
        for (Salon salon : salones) {
            if (!salon.isOcupado()) {
                salonesDisponibles.add(salon);
            }
        }
        return salonesDisponibles;
    }

    /**
     * Imprime la información del edificio, incluyendo su nombre y la información de sus salones.
     */
    public void imprimirInformacionEdificio() {
        System.out.println("Edificio: " + nombre);
        for (Salon salon : salones) {
            salon.imprimirInformacionSalon();
        }
    }
}