package com.mauri.Biblioteca.Repository;

import com.mauri.Biblioteca.Models.Juego;
import com.mauri.Biblioteca.Utils.EntityManagerProvider;

public class JuegoRepository extends BaseRepository<Juego, Long> {
    public JuegoRepository() {
        super(EntityManagerProvider.getEntityManager(), Juego.class);
    }
}
