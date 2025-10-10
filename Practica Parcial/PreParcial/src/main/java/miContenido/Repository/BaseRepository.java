package miContenido.Repository;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class BaseRepository<T, ID> {
    protected EntityManager em;
    protected Class<T> entityClass;

    public BaseRepository(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public List<T> findAll(){
        String jpql = "select t from "+entityClass.getSimpleName()+" t ";
        return em.createQuery(jpql, entityClass).getResultList();
    }

    public T findById(ID id){
        return em.find(entityClass, id);
    }


    // Usamos merge para save y update, ya que si el entity no tiene id, lo inserta, y si tiene id, lo actualiza
    public void save(T entity){

        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();

        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void update(T entity){
        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(T entity){
        try{
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }
    }


}
