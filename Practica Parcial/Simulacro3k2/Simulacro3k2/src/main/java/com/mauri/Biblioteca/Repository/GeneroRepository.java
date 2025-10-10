package com.mauri.Biblioteca.Repository;

import com.mauri.Biblioteca.Models.Genero;
import com.mauri.Biblioteca.Utils.EntityManagerProvider;

public class GeneroRepository extends BaseRepository<Genero, Long> {

    public GeneroRepository() {
        super(EntityManagerProvider.getEntityManager(), Genero.class);
    }
}
