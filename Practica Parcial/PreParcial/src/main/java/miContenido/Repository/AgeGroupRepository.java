package miContenido.Repository;


import miContenido.model.AgeGroup;
import miContenido.util.LocalEntityManagerProvider;

public  class AgeGroupRepository extends BaseRepository<AgeGroup, Integer> {
    public AgeGroupRepository () {
        super(LocalEntityManagerProvider.getEntityManager(), AgeGroup.class);
    }

}



