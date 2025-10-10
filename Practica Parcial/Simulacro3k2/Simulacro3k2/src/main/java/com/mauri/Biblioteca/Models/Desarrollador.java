package com.mauri.Biblioteca.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "DESARROLLADORES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desarrollador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESA_ID")
    private long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @OneToMany(mappedBy = "desarrollador", fetch = FetchType.LAZY)
    private List<Juego> juegos;
}
