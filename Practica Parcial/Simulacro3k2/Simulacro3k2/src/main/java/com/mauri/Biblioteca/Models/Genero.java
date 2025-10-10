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
@Table(name = "GENEROS")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GEN_ID")
    private long id;

    @Column(name = "NOMBRE")
    private String nome;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Juego> juegos;
}
