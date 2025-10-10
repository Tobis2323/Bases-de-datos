package com.mauri.Biblioteca.Repository;

import com.mauri.Biblioteca.Models.Desarrollador;
import com.mauri.Biblioteca.Utils.EntityManagerProvider;
import jakarta.persistence.EntityManager;

public class DesarrolladorRepository extends BaseRepository<Desarrollador, Long>{

    public DesarrolladorRepository() {
        super(EntityManagerProvider.getEntityManager() , Desarrollador.class);
    }
}
