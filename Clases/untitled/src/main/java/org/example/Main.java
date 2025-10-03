package org.example;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo del libro");
        String numero = sc.nextLine(); //Toma la linea de codigo

        Boolean validacion = validacionIsbn10(numero);
        System.out.println(validacion ? "Valido" : "No valido");
        sc.close();
    }

    public static boolean validacionIsbn10(String numeroConGuiones){
        if (numeroConGuiones == null) return false;

        String numeroLimpio = numeroConGuiones.toUpperCase().trim().replace("-","");

        if (numeroLimpio.length() != 10) return false;

        int suma = 0;

        for (int i = 0; i<10; i++){
            char c = numeroLimpio.charAt(i);
            int n = c - '0';
            int valor = 10 - i;
            suma += valor * n;
        }
        return (suma % 11 == 0);
    }
}