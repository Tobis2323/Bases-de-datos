package miContenido.Repository;


import miContenido.model.AgeGroup;
import miContenido.util.EntityManagerProvider;

public  class AgeGroupRepository extends BaseRepository<AgeGroup, Integer> {
    public AgeGroupRepository () {
        super(EntityManagerProvider.getEntityManager(), AgeGroup.class);
    }

}



