package com.mauri.Biblioteca.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;
import java.sql.Clob;
import java.util.Date;

@Entity
@Table(name = "JUEGOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JUEGO_ID")
    private long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "FECHA_LANZAMIENTO")
    private Date fechaLanzamiento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEN_ID") // nombre de la FK en la tabla JUEGOS
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESA_ID")
    private Desarrollador desarrollador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAT_ID")
    private Plataforma plataforma;

    @Column(name = "CLASIFICACION_ESRB")
    @Enumerated(EnumType.STRING) // <-- si Clasificacion es un ENUM
    private Clasificacion clasificacionEsrb;


    @Column(name = "RATING")
    private double rating;

    @Column(name = "JUEGOS_FINALIZADOS")
    private int juegosFinalizados;

    @Column(name = "JUGANDO")
    private int jugando;

    @Column(name = "RESUMEN")
    private String resumen;

}
