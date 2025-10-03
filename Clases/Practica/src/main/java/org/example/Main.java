import org.example.Persona;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int [] v = arreglosGratis(20,-100,100);
        System.out.println("La suma de todos los valores es: " + sumar(v));
        System.out.println("El promedio de los valores es: " + sumar(v) / v.length);
        System.out.println("Cantidad de negativos: " + negativos(v));
        System.out.println("El mayor de todos es: " + mayor(v));
        Persona[] p = generarPersonas(5);
    }
    static String listado(Persona[] p){
        for (int i = 0; i < p.length; i++) {
            System.out.println("Documento: " + p[i].getDocumento() + "Nombre: " + p[i].getNombre() + "Edad: " + p[i].getEdad());
        }
    }
    static Persona[] generarPersonas(int cantidad){
        Persona[] p = new Persona[cantidad];
        for (int i = 0; i < cantidad; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese los datos de la persona " + (i + 1));

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Documento: ");
            int documento = sc.nextInt();

            System.out.print("Edad: ");
            int edad = sc.nextInt();

            p[i] = new Persona(documento, nombre, edad);
        }
        return p;
    }
    static int [] arreglosGratis(int tamaño, int min, int max){
        int [] v = new int[tamaño];
        Random r = new Random();
        for (int i = 0; i < v.length; i++) {
            v[i] = r.nextInt((max - min) + 1) + min;
        }
        return v;
    }
    static int sumar(int[] v){
        int suma = 0;
        for ( int x : v) suma += x;
        return suma;
    }
    static int negativos(int [] v){
        int contador = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] < 0){
                contador++;
            }
        }
        return contador;
    }
    static int mayor(int [] v){
        int mayor = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] > mayor){
                mayor = v[i];
            }
        }
        return mayor;
    }
}