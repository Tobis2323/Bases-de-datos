package miContenido.Repository;

import miContenido.model.Theme;
import miContenido.util.LocalEntityManagerProvider;

public class ThemeRepository extends BaseRepository<Theme, Integer> {
    public ThemeRepository() {
        super(LocalEntityManagerProvider.getEntityManager(), Theme.class);
    }

}
