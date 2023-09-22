import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase representa una Universidad que gestiona sedes, cursos, edificios y salones,
 * y permite cargar datos desde archivos CSV, asignar salones a cursos, generar informes
 * y realizar consultas sobre sedes y cursos.
 */

public class Universidad {
    private List<Sede> sedes;
    private List<Curso> cursos;
    private List<Curso> cursosSinSalon;
    private List<Edificio> edificios;

    /**
     * Constructor de la clase Universidad.
     */

    public Universidad() {
        sedes = new ArrayList<>();
        cursos = new ArrayList<>();
        cursosSinSalon = new ArrayList<>();
        edificios = new ArrayList<>();
    }

    /**
     * Carga información de salones desde un archivo CSV.
     *
     * @param archivoSalones El nombre del archivo CSV que contiene los datos de salones.
     */
    public void cargarSedesDesdeCSV(String archivoSalones) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSalones))) {
            String header = br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 5) {
                    int idSede = Integer.parseInt(datos[0].trim());
                    char edificioChar = datos[1].trim().charAt(0);
                    int nivel = Integer.parseInt(datos[2].trim());
                    int idSalon = Integer.parseInt(datos[3].trim());
                    int capacidad = Integer.parseInt(datos[4].trim());

                    Sede sede = buscarSedePorId(idSede);
                    if (sede == null) {
                        sede = new Sede(idSede, "NombreDeSede");
                        sedes.add(sede);
                    }
                    Edificio edificioExistente = sede.buscarEdificioPorNombre(String.valueOf(edificioChar));
                    if (edificioExistente == null) {
                        edificioExistente = new Edificio(String.valueOf(edificioChar), nivel);
                        sede.agregarEdificio(edificioExistente);
                    }

                    edificioExistente.agregarSalon(idSalon, capacidad);
                } else {
                    System.out.println("Formato incorrecto en la línea del archivo CSV de salones: " + linea);
                }
            }
            System.out.println("Datos de salones cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar datos de salones desde el archivo CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Busca un edificio por su nombre.
     *
     * @param nombre El nombre del edificio a buscar.
     * @return El edificio encontrado o null si no se encontró ninguno con ese nombre.
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
     * Carga información de cursos desde un archivo CSV.
     *
     * @param archivoCursos El nombre del archivo CSV que contiene los datos de cursos.
     */
    public void cargarCursosDesdeCSV(String archivoCursos) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCursos))) {
            br.readLine(); 
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (datos.length == 7) {
                    int idCurso = Integer.parseInt(datos[0].replaceAll("\"", "").trim());
                    int idSede = Integer.parseInt(datos[1].replaceAll("\"", "").trim());
                    String nombreCurso = datos[2].replaceAll("\"", "").trim();
                    int horario = Integer.parseInt(datos[3].replaceAll("\"", "").trim());
                    int duracion = Integer.parseInt(datos[4].replaceAll("\"", "").trim());
                    String diasString = datos[5].replaceAll("\"", "").trim();
                    String[] diasArray = diasString.split(",");
                    List<String> dias = new ArrayList<>(Arrays.asList(diasArray));
                    int cantidadEstudiantes = Integer.parseInt(datos[6].replaceAll("\"", "").trim());

                    Curso curso = new Curso(idCurso, idSede, nombreCurso, horario, duracion, dias, cantidadEstudiantes);
                    cursos.add(curso);
                } else {
                    System.out.println("Formato incorrecto en la línea del archivo CSV de cursos: " + linea);
                }
            }
            System.out.println("Datos de cursos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar datos de cursos desde el archivo CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Asigna salones a cursos.
     */
    public void asignarSalones() {
        cursosSinSalon.clear(); 
        for (Curso curso : cursos) {
            Sede sede = buscarSedePorId(curso.getIdSede());
            if (sede != null) {
                Salon salonDisponible = sede.buscarSalonDisponible();
                if (salonDisponible != null) {
                    if (curso.verificarCapacidad(salonDisponible.getCapacidad())) {
                        curso.asignarSalon(salonDisponible); 
                    } else {
                        System.out.println("El salón no tiene capacidad suficiente para el curso: " + curso.getNombreCurso());
                        cursosSinSalon.add(curso);
                    }
                } else {
                    System.out.println("No se encontró un salón válido para el curso: " + curso.getNombreCurso());
                    cursosSinSalon.add(curso);
                }
            }
        }
    }

    /**
     * Consulta una sede por su ID.
     *
     * @param idSede El ID de la sede a consultar.
     * @return La sede encontrada o null si no se encontró ninguna con ese ID.
     */
    public Sede consultarSedePorId(int idSede) {
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                return sede;
            }
        }
        return null;
    }

    /**
     * Consulta un curso por su ID.
     *
     * @param idCurso El ID del curso a consultar.
     * @return El curso encontrado o null si no se encontró ninguno con ese ID.
     */

    public Curso consultarCursoPorId(int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getIdCurso() == idCurso) {
                return curso;
            }
        }
        return null;
    }
    /**
     * Genera un informe de cursos asignados y cursos sin salón asignado.
     */

    public void generarInforme() {
        System.out.println("---- Cursos Asignados ----");
        for (Curso curso : cursos) {
            if (curso.tieneSalonAsignado()) {
                curso.imprimirInformacionCurso();
                System.out.println("Salon Asignado: " + curso.getSalonAsignado().getIdSalon());
                System.out.println();
            }
        }

        System.out.println("---- Cursos Sin Salon Asignado ----");
        for (Curso curso : cursosSinSalon) {
            curso.imprimirInformacionCurso();
            System.out.println("Sin Asignar");
            System.out.println();
        }
    }

    /**
     * Busca una sede por su ID.
     *
     * @param idSede El ID de la sede a buscar.
     * @return La sede encontrada o null si no se encontró ninguna con ese ID.
     */

    public Sede buscarSedePorId(int idSede) {
        for (Sede sede : sedes) {
            if (sede.getIdSede() == idSede) {
                return sede;
            }
        }
        return null;
    }

    /**
     * Exporta el informe generado a un archivo CSV.
     *
     * @param archivoSalida El nombre del archivo CSV de salida.
     */

    public void exportarInformeCSV(String archivoSalida) {
        try (FileWriter writer = new FileWriter(archivoSalida)) {
            writer.write("ID Curso,Sede,Nombre del Curso,Horario,Cantidad de Estudiantes,Salón Asignado\n");

            for (Curso curso : cursos) {
                if (curso.tieneSalonAsignado()) {
                    writer.write(String.format("%d,%d,%s,%d,%d,%d\n", curso.getIdCurso(), curso.getIdSede(), curso.getNombreCurso(), curso.getHorario(), curso.getCantidadEstudiantes(), curso.getSalonAsignado().getIdSalon()));
                }
            }

            for (Curso curso : cursosSinSalon) {
                writer.write(String.format("%d,%d,%s,%d,%d,Sin Asignar\n", curso.getIdCurso(), curso.getIdSede(), curso.getNombreCurso(), curso.getHorario(), curso.getCantidadEstudiantes()));
            }

            System.out.println("Informe exportado exitosamente a " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al exportar el informe a CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Método principal para ejecutar la Universidad.
     */

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("---- Menu Principal ----");
            System.out.println("1. Cargar Datos desde CSV");
            System.out.println("2. Asignar Salones");
            System.out.println("3. Generar Informe");
            System.out.println("4. Consultar Sede");
            System.out.println("5. Consultar Curso");
            System.out.println("6. Exportar Informe a CSV");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cargarSedesDesdeCSV("salones.csv");
                    cargarCursosDesdeCSV("cursos.csv");
                    System.out.println("Datos cargados exitosamente.");
                    break;

                case 2:
                    asignarSalones();
                    System.out.println("Salones asignados con exito.");
                    break;

                case 3:
                    if (cursosSinSalon.isEmpty()) {
                        System.out.println("No se han asignado salones a los cursos.");
                    } else {
                        generarInforme();
                    }

                case 4:
                    System.out.print("Ingrese el ID de la sede a consultar: ");
                    int idSedeConsulta = scanner.nextInt();
                    Sede sedeConsultada = consultarSedePorId(idSedeConsulta);
                    if (sedeConsultada != null) {
                        sedeConsultada.imprimirInformacionSede();
                    } else {
                        System.out.println("Sede no encontrada.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ID del curso a consultar: ");
                    int idCursoConsulta = scanner.nextInt();
                    Curso cursoConsultado = consultarCursoPorId(idCursoConsulta);
                    if (cursoConsultado != null) {
                        cursoConsultado.imprimirInformacionCurso();
                    } else {
                        System.out.println("Curso no encontrado.");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese el nombre del archivo CSV de salida: ");
                    String archivoSalida = scanner.next();
                    exportarInformeCSV(archivoSalida);
                    break;

                case 7:
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    /**
     * Método principal para iniciar la aplicación.
     *
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    
    public static void main(String[] args) {
        Universidad universidad = new Universidad();
        universidad.ejecutar();
    }
}