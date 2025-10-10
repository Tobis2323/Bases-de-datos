package com.mauri.Biblioteca.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "PLATAFORMAS")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAT_ID")
    private long id;

    @Column(name = "NOMBRE")
    private String nome;

    @OneToMany(mappedBy = "plataforma", fetch = FetchType.LAZY)
    private List<Juego> juegos;

    public Plataforma(long id, String nombre) {
        this.id = id;
        this.nome = nombre;
    }
}
