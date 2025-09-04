import lombok.Data;

@Data
public class CafeteraInteligente {

    private String marca;
    private String modelo;
    private int capacidadMax;
    private int contenidoActual;
    private boolean encendida;
    private int cafesServidos;
    private int temperatura;

    public CafeteraInteligente(String marca, String modelo, int capacidadMax){
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadMax = capacidadMax;
        this.contenidoActual = 0;
        this.encendida = false;
        this.cafesServidos = 0;
        this.temperatura = 20;
    }

    public void encender(){
        this.encendida = true;
        this.temperatura = 20;
        this.contenidoActual = 0;
    }

    public void apagar(){
        this.encendida = false;
        this.cafesServidos = 0;
    }

    public void cargarAgua(int ml){
        if (this.encendida){
            this.contenidoActual += ml;
            if (this.contenidoActual > this.capacidadMax){
                this.contenidoActual = this.capacidadMax;
            }
        }
    }

    public void calentar(){
        this.temperatura += 40;
        if (this.temperatura >= 100){
            this.temperatura = 100;
        }
    }

    public Boolean servirCafe(){
        if (this.encendida && this.contenidoActual == 100 && this.temperatura >= 90){
            this.contenidoActual -= 100;
            this.cafesServidos++;
            return true;
        } return false;
    }

    public String toString(){
        String estado = this.encendida ? "Encendida" : "Apagada";
        return "Cafetera " + this.marca + " " + this.modelo +
                " - Agua: " + this.contenidoActual + "ml, " +
                "Temperatura: " + this.temperatura + "°C, " +
                "Servidos: " + this.cafesServidos + ", " +
                "Estado: " + estado;
    }
}
