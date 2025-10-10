package miContenido.Repository;

import miContenido.model.Theme;
import miContenido.util.EntityManagerProvider;

public class ThemeRepository extends BaseRepository<Theme, Integer> {
    public ThemeRepository() {
        super(EntityManagerProvider.getEntityManager(), Theme.class);
    }

}
