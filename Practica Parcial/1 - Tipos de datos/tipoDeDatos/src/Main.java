import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu(scanner);
        scanner.close();
    }

    private static void menu(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("Seleccione una opción:");
            System.out.println("1 - Es múltiplo");
            System.out.println("2 - (pendiente)");
            System.out.println("3 - (pendiente)");
            System.out.println("4 - (pendiente)");
            System.out.println("5 - (pendiente)");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next(); // consumir token erróneo
                continue;
            }

            switch (opcion) {
                case 1:
                    option1(scanner);
                    break;

                case 2:
                    option2(scanner);
                    break;
                case 3:
                    option3(scanner);
                    break;
                case 4:
                    option4(scanner);
                    break;
                case 5:
                    option5(scanner);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Maneja la opción 1: lee entrada y usa la función auxiliar para determinar si es múltiplo
    private static void option1(Scanner scanner) {
        System.out.print("Ingrese un número: ");
        int n = scanner.nextInt();
        System.out.print("Ingrese el múltiplo: ");
        int mul = scanner.nextInt();

        if (mul == 0) {
            System.out.println("El múltiplo no puede ser 0. Operación cancelada.");
            return;
        }

        if (isMultipleOf(mul, n)) {
            System.out.println("El número es múltiplo.");
        } else {
            System.out.println("El número no es múltiplo.");
        }
    }

    private static void option2(Scanner scanner) {
        System.out.println("Opción 2 pendiente de implementar.");
    }

    private static void option3(Scanner scanner) {
        System.out.println("Opción 3 pendiente de implementar.");
    }

    private static void option4(Scanner scanner) {
        System.out.println("Opción 4 pendiente de implementar.");
    }

    private static void option5(Scanner scanner) {
        System.out.println("Opción 5 pendiente de implementar.");
    }

    // Función auxiliar que devuelve true si n es múltiplo de mul
    private static boolean isMultipleOf(int mul, int n) {
        if (mul == 0) {
            return false; // protección adicional aunque el llamador ya lo valida
        }
        return n % mul == 0;
    }
}