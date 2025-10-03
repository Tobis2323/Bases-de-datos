package ar.edu.utnfrc.backend.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter

public abstract class ProductoDigital {
    private final String sku;
    private final String nombre;
    private final double precioBase;
    private final List<Etiqueta> etiquetas;


    protected ProductoDigital(String sku, String nombre, double precioBase, List<Etiqueta> etiquetas) {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("SKU vacío");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre vacío");
        if (precioBase < 0) throw new IllegalArgumentException("Precio negativo");
        this.sku = sku.trim();
        this.nombre = nombre.trim();
        this.precioBase = precioBase;
        this.etiquetas = etiquetas == null ? new ArrayList<>() : new ArrayList<>(etiquetas);
    }

    /** Porcentaje de impuesto (0.05 = 5%). */
    public abstract double impuesto();

    /** Precio final exacto (sin redondeo a 2). */
    public double precioFinalExacto() {
        return precioBase * (1.0 + impuesto());
    }

    /** Precio final redondeado a 2 decimales (mostrar/reportar). */
    public double precioFinal() {
        return Math.round(precioFinalExacto() * 100.0) / 100.0;
    }

    /** Tipo legible basado en el nombre de la clase. */
    public String getTipo() {
        return this.getClass().getSimpleName().toUpperCase(); // EBOOK, APP, CURSOONLINE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoDigital)) return false;
        ProductoDigital pd = (ProductoDigital) o;
        return sku.equalsIgnoreCase(pd.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku.toLowerCase());
    }

    @Override
    public String toString() {
        return getTipo()+"{sku='" + sku + "', nombre='" + nombre + "', precioBase=" + precioBase +
                ", precioFinal=" + precioFinal() + "}";
    }
}
