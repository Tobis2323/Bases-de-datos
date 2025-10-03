import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese un numero entero positivo: \n");
        int num = entrada.nextInt();
        System.out.println((num <= 0) ? "Ingresaste un negativo o cero" : "Numero ingresado correctamente");
        while (num <= 0){
            num = entrada.nextInt();
            System.out.println((num <= 0) ? "Ingresaste un negativo o cero" : "Numero ingresado correctamente");
        }

        ArrayList<Integer> multiplos = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            if((i % 3) == 0 ^ (i%5) == 0){
                multiplos.add(i);
            }
        }

        System.out.println(multiplos);
    }
}