import java.util.ArrayList;
import java.util.List;

/**
 * La clase Sede representa una sede con un identificador, un nombre y una lista de edificios.
 */
public class Sede {
    private int idSede;          // Identificador de la sede
    private String nombre;       // Nombre de la sede
    private List<Edificio> edificios; // Lista de edificios en la sede

    /**
     * Constructor de la clase Sede.
     *
     * @param idSede El identificador único de la sede.
     * @param nombre El nombre de la sede.
     */
    public Sede(int idSede, String nombre) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.edificios = new ArrayList<>();
    }

    /**
     * Obtiene el identificador de la sede.
     *
     * @return El identificador de la sede.
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
     * Obtiene la lista de edificios en la sede.
     *
     * @return La lista de edificios en la sede.
     */
    public List<Edificio> getEdificios() {
        return edificios;
    }

    /**
     * Establece el identificador de la sede.
     *
     * @param idSede El identificador único de la sede.
     */
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    /**
     * Establece el nombre de la sede.
     *
     * @param nombre El nombre de la sede.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Agrega un edificio a la sede.
     *
     * @param edificio El edificio a agregar.
     */
    public void agregarEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    /**
     * Agrega un salón a un edificio en la sede, especificando el ID del salón, su capacidad y el nombre del edificio.
     *
     * @param idSalon       El ID del salón a agregar.
     * @param capacidad     La capacidad del salón a agregar.
     * @param nombreEdificio El nombre del edificio en el que se debe agregar el salón.
     */
    public void agregarSalon(int idSalon, int capacidad, String nombreEdificio) {
        Edificio edificio = buscarEdificioPorNombre(nombreEdificio);
        if (edificio != null) {
            edificio.agregarSalon(idSalon, capacidad);
        } else {
            System.out.println("El edificio " + nombreEdificio + " no existe en esta sede.");
        }
    }

    /**
     * Busca un salón disponible en la sede.
     *
     * @return El salón disponible encontrado o null si no se encontró ninguno.
     */
    public Salon buscarSalonDisponible() {
        for (Edificio edificio : edificios) {
            List<Salon> salonesDisponibles = edificio.buscarSalonesDisponibles();
            for (Salon salon : salonesDisponibles) {
                return salon;
            }
        }
        return null;
    }

    /**
     * Busca un edificio en la sede por su nombre.
     *
     * @param nombre El nombre del edificio a buscar.
     * @return El edificio encontrado o null si no se encontró.
     */
    public Edificio buscarEdificioPorNombre(String nombre) {
        for (Edificio edificio : edificios) {
            if (edificio.getNombre().equals(nombre)) {
                return edificio;
            }
        }
        return null;
    }

    /**
     * Imprime la información de la sede, incluyendo su nombre y la información de sus edificios.
     */
    public void imprimirInformacionSede() {
        System.out.println("Sede: " + nombre);
        for (Edificio edificio : edificios) {
            edificio.imprimirInformacionEdificio();
        }
    }
}