package pau.m06.dam.IES.HivernatePauEx2;

import java.util.List;
import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public static int showMenu() {
        int option = -1;
        while (option == -1) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar deporte");
            System.out.println("2. Agregar atleta");
            System.out.println("3. Buscar atleta por nombre");
            System.out.println("4. Listar atletas por deporte");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                option = scanner.nextInt();
                if (option < 1 || option > 5) {
                    System.out.println("Opción inválida.");
                    option = -1;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.nextLine();  // Limpiar buffer
            }
        }
        return option;
    }

    public static String sportForm() {
        scanner.nextLine();  // Limpiar buffer de entrada
        System.out.print("Ingrese el nombre del deporte: ");
        return scanner.nextLine();
    }

    public static Athletes athleteForm() {
        scanner.nextLine();  // Limpiar buffer de entrada
        System.out.print("Ingrese el nombre del atleta: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el código del deporte: ");
        Integer sportCod = null;
        try {
            sportCod = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Código de deporte inválido. Por favor ingrese un número.");
            scanner.nextLine();  // Limpiar buffer
        }

        if (sportCod == null) {
            return null;  // Si el código no es válido, no se crea el atleta
        }

        Sports sport = new Sports();
        sport.setCod(sportCod);

        return new Athletes(null, name, sport);
    }

    public static String askAthlete() {
        scanner.nextLine();  // Limpiar buffer de entrada
        System.out.print("Ingrese el nombre del atleta (parcial): ");
        return scanner.nextLine();
    }

    public static int askSport(List<Sports> sports) {
        System.out.println("Deportes disponibles:");
        sports.forEach(s -> System.out.println(s.getCod() + ". " + s.getName()));
        System.out.print("Seleccione el ID del deporte: ");
        int sportId = -1;
        try {
            sportId = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("ID de deporte inválido. Por favor ingrese un número.");
            scanner.nextLine();  // Limpiar buffer
        }
        return sportId;
    }

    public static void printAthletes(List<Athletes> athletes) {
        if (athletes.isEmpty()) {
            System.out.println("No se encontraron atletas.");
        } else {
            athletes.forEach(View::printAthlete);
        }
    }

    public static void printAthlete(Athletes athlete) {
        System.out.println("ID: " + athlete.getCod() + ", Nombre: " + athlete.getName());
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
