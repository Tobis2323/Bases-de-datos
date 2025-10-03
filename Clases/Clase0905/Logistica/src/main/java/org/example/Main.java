import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import  lombok.NoArgsConstructor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String ruta = "viajes.csv";

        Map<String, Cliente> clientePorCuit = new HashMap<>();
        List<Viaje> Viajes = new ArrayList<>();


    }
    public static void cargarDesdeCSV(String ruta, Map<String, Cliente> clientesPorCuit, List <Viaje> viajes){
        try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea = br.readLine();

            if(linea != null && linea.toLowerCase().constains("codigo")){
                linea = br.readLine();
            }
        }
    }
}



@Data
@AllArgsConstructor
@NoArgsConstructor
abstract class Viaje {
    String codido;
    int nroReserva;
    double precio;
    int tipo;
    Cliente cliente;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Cliente {
    String nombre;
    String cuit;
}
class Aereo extends Viaje{
    private int millasAcumuladas;
    private String codAerolinea;

    public Aereo(String codigo, int nroReserva, double precio, int tipo, Cliente cliente,
                 int millasAcumuladas, String codAerolinea) {
        super(codigo, nroReserva, precio, tipo, cliente);
        this.millasAcumuladas = millasAcumuladas;
        this.codAerolinea = codAerolinea;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Terrestre extends Viaje {
    private int provinciasVisitadas;
    private int cantidadPasajeros;

    public Terrestre(String codigo, int nroReserva, double precio, int tipo, Cliente cliente,
                     int provinciasVisitadas, int cantidadPasajeros) {
        super(codigo, nroReserva, precio, tipo, cliente);
        this.provinciasVisitadas = provinciasVisitadas;
        this.cantidadPasajeros = cantidadPasajeros;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Maritimo extends Viaje {
    private int cantidadContenedores;
    private double costoPorKilo;
    private double pesoTransportado;

    public Maritimo(String codigo, int nroReserva, double precio, int tipo, Cliente cliente,
                    int cantidadContenedores, double costoPorKilo, double pesoTransportado) {
        super(codigo, nroReserva, precio, tipo, cliente);
        this.cantidadContenedores = cantidadContenedores;
        this.costoPorKilo = costoPorKilo;
        this.pesoTransportado = pesoTransportado;
    }
    public static double calcularCostoTotal(Maritimo m) {
        return m.costoPorKilo * m.pesoTransportado;
    }
}
