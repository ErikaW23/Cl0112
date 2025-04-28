import java.util.Random;
import java.util.Scanner;

public class JuegoBatalla {
    // Atributos
    private static Robot[] robots;
    private static Scanner scanner = new Scanner(System.in);
    // Main y uso de Try-Catch
    public static void main(String[] args) {
        int cantidad = 0;

        try {
            System.out.println("Ingrese la cantidad de robots (Entre 2 y 10):");
            cantidad = Integer.parseInt(scanner.nextLine());

            if (cantidad >= 2 && cantidad <= 10) {
                robots = new Robot[cantidad];
            } else {
                System.out.println("El número debe estar entre 2 y 10");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("No has ingresado un número válido");
            return;
        }

        ingresarRobots();
        iniciarBatalla();
        mostrarGanador();
    }
    // Ingreso de los datos aleatorios y elección de nombres
    private static void ingresarRobots() {
        Random rand = new Random();

        for (int i = 0; i < robots.length; i++) {
            System.out.println("Ingrese el nombre del robot #" + (i + 1) + ":");
            String nombre = scanner.next();
            int puntosVida = 50 + rand.nextInt(51); // Vida entre 50 y 100
            int ataque = 10 + rand.nextInt(11); // Ataque entre 10 y 20
            int defensa = rand.nextInt(11); // Defensa entre 0 y 10

            robots[i] = new Robot(nombre, puntosVida, ataque, defensa);
        }
    }
    // Inicialización de la batalla
    private static void iniciarBatalla() {
        Random rand = new Random();
        // Sistema para que los robots no se ataquen a sí mismos
        while (contarRobotsVivos() > 1) {
            for (int i = 0; i < robots.length; i++) {
                if (robots[i] != null && robots[i].estaVivo()) {
                    int objetivo = rand.nextInt(robots.length);
                    while (objetivo == i || robots[objetivo] == null || !robots[objetivo].estaVivo()) {
                        objetivo = rand.nextInt(robots.length);
                    }
                    robots[i].atacar(robots[objetivo]);
                    System.out.println(robots[i].getNombre() + " ataca a " + robots[objetivo].getNombre());

                    if (!robots[objetivo].estaVivo()) {
                        System.out.println(robots[objetivo].getNombre() + " fue destruido");
                    }
                }
            }
            // Opciones para que el usuario decida como continuar la batalla
            if (contarRobotsVivos() > 1) {
                System.out.println("¿Comó deseas seguir?");
                System.out.println("(C) Continuar - (E) Estado de robots - (D) Detener simulación");

                String opción = scanner.next();

                switch (opción) {
                    case "C":
                        break;
                    case "E":
                        mostrarEstadoRobots();
                        break;
                    case "D":
                        System.out.println("Fin de la simulación");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor, digite una opción válida");
                        break;
                }
            }
        }
    }
    // Demostrar el ganador de la batalla
    private static void mostrarGanador() {
        for (Robot a : robots) {
            if (a != null && a.estaVivo()) {

                System.out.println("El ganador es: " + a.getNombre());
                System.out.println("Vida restante: " + a.getPuntosVida());
            }
        }
    }
    // Estado de cada robot
    private static void mostrarEstadoRobots() {

        System.out.println("Estado actual de los robots");
        for (Robot a : robots) {
            if (a != null) {
                String estado = a.estaVivo() ? "Vivo" : "Destruido";
                System.out.println(a.getNombre() + " | Vida: " + a.getPuntosVida() + " | Ataque: " + a.getAtaque() + " | Defensa: " + a.getDefensa() + " | Estado: " + estado);
            }
        }
    }

    private static int contarRobotsVivos() {
        int vivos = 0;
        for (Robot a : robots) {
            if (a != null && a.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }
}