package com.mauri.Biblioteca;

import com.mauri.Biblioteca.Models.Juego;
import com.mauri.Biblioteca.Models.Plataforma;
import com.mauri.Biblioteca.Repository.PlataformaRepository;
import com.mauri.Biblioteca.Utils.EntityManagerProvider;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        EntityManager em = EntityManagerProvider.getEntityManager();

        System.out.println("✅ Conexión establecida correctamente.");

        Plataforma plataforma = new Plataforma(1, "primeraPlataforma");

        PlataformaRepository plataformaRepository = new PlataformaRepository();
        plataformaRepository.save(plataforma);
        Plataforma plataformas = plataformaRepository.findById(1L);
        System.out.println(plataformas);

        em.close();
        EntityManagerProvider.close();
    }
}
