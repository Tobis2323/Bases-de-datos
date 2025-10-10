package com.mauri.Biblioteca.Repository;

import com.mauri.Biblioteca.Models.Plataforma;
import com.mauri.Biblioteca.Utils.EntityManagerProvider;

public class PlataformaRepository extends BaseRepository<Plataforma, Long> {

    public PlataformaRepository() {
        super(EntityManagerProvider.getEntityManager(), Plataforma.class);
    }
}
