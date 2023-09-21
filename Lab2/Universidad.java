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
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    int idSede = Integer.parseInt(datos[0].trim());
                    char edificioChar = datos[1].trim().charAt(0);
                    int nivel = Integer.parseInt(datos[2].trim());
                    int idSalon = Integer.parseInt(datos[3].trim());
                    int capacidad = Integer.parseInt(datos[4].trim());

                    Edificio edificioExistente = buscarEdificioPorNombre(String.valueOf(edificioChar));
                    if (edificioExistente == null) {
                        edificioExistente = new Edificio(String.valueOf(edificioChar), nivel);
                        edificios.add(edificioExistente);
                    }

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
                String[] datos = parsearLineaCSV(linea);
                if (datos != null) {
                    try {
                        int idCurso = Integer.parseInt(datos[0].trim());
                        int idSede = Integer.parseInt(datos[1].trim());
                        String nombreCurso = datos[2].trim();
                        int horario = Integer.parseInt(datos[3].trim());
                        int duracion = Integer.parseInt(datos[4].trim());
                        String[] dias = datos[5].trim().split(",");
                        int cantidadEstudiantes = Integer.parseInt(datos[6].trim());

                        if (idCurso >= 1 && idSede >= 1 && horario >= 7 && horario <= 21
                                && duracion >= 1 && duracion <= 3
                                && cantidadEstudiantes >= 1 && cantidadEstudiantes <= 60) {
                            System.out.println("ID Curso: " + idCurso);
                            System.out.println("ID Sede: " + idSede);
                            System.out.println("Nombre del Curso: " + nombreCurso);
                            System.out.println("Horario: " + horario);
                            System.out.println("Duración: " + duracion);
                            System.out.println("Días: " + String.join(", ", dias));
                            System.out.println("Cantidad de Estudiantes: " + cantidadEstudiantes);

                        } else {
                            System.out.println("Formato incorrecto en la línea del archivo CSV de cursos: " + linea);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir un valor a número en la linea del archivo CSV: " + linea);
                    }
                } else {
                    System.out.println("Formato incorrecto en la linea del archivo CSV de cursos: " + linea);
                }
            }
            System.out.println("Datos de cursos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar datos de cursos desde el archivo CSV.");
            e.printStackTrace();
        }
    }

    /**
     * Parsea una línea de texto CSV en un arreglo de campos.
     *
     * @param linea La línea de texto CSV a ser parseada.
     * @return Un arreglo de campos o null si la línea no se pudo parsear correctamente.
     */

    public String[] parsearLineaCSV(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder campoActual = new StringBuilder();
        boolean dentroDeComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c == '"') {
                dentroDeComillas = !dentroDeComillas;
            } else if (c == ',' && !dentroDeComillas) {
                campos.add(campoActual.toString().trim());
                campoActual.setLength(0);
            } else {
                campoActual.append(c);
            }
        }

        campos.add(campoActual.toString().trim());

        for (int i = 0; i < campos.size(); i++) {
            String campo = campos.get(i);
            campo = campo.replaceAll("\"\"", "\"");
            campos.set(i, campo);
        }

        return campos.toArray(new String[0]);
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
     * Asigna salones a cursos.
     */

    public void asignarSalones() {
        for (Curso curso : cursos) {
            if (!curso.tieneSalonAsignado()) {
                Sede sede = buscarSedePorId(curso.getIdSede());
                if (sede != null) {
                    Salon salonDisponible = sede.buscarSalonDisponible(curso);
                    if (salonDisponible != null) {
                        curso.asignarSalon(salonDisponible);
                    } else {
                        cursosSinSalon.add(curso);
                    }
                }
            }
        }
    }

    /**
     * Genera un informe de cursos asignados y cursos sin salón asignado.
     */

    public void generarInforme() {
        System.out.println("---- Cursos Asignados ----");
        for (Curso curso : cursos) {
            if (curso.tieneSalonAsignado()) {
                System.out.println("ID del Curso: " + curso.getIdCurso());
                System.out.println("Sede: " + curso.getIdSede());
                System.out.println("Nombre del Curso: " + curso.getNombreCurso());
                System.out.println("Horario: " + curso.getHorario());
                System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
                System.out.println("Salon Asignado: " + curso.getSalonAsignado().getIdSalon());
                System.out.println();
            }
        }

        System.out.println("---- Cursos Sin Salon Asignado ----");
        for (Curso curso : cursosSinSalon) {
            System.out.println("ID del Curso: " + curso.getIdCurso());
            System.out.println("Sede: " + curso.getIdSede());
            System.out.println("Nombre del Curso: " + curso.getNombreCurso());
            System.out.println("Horario: " + curso.getHorario());
            System.out.println("Cantidad de Estudiantes: " + curso.getCantidadEstudiantes());
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
                    generarInforme();
                    break;

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