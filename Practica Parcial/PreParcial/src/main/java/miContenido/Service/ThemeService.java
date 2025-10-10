package miContenido.Service;

import miContenido.Repository.ThemeRepository;
import miContenido.model.Theme;
import java.util.List;

public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService() {
        this.themeRepository = new ThemeRepository();
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme getThemeById(Integer id) {
        return themeRepository.findById(id);
    }

    public void saveTheme(Theme theme) {
        themeRepository.save(theme);
    }

    public void updateTheme(Theme theme) {
        themeRepository.update(theme);
    }

    public void deleteTheme(Theme theme) {
        themeRepository.delete(theme);
    }

    // ✅ NUEVO MÉTODO PARA GUARDAR UNA LISTA ENTERA
    public void agregarArrayList(List<Theme> themes) {
        for (Theme theme : themes) {
            themeRepository.save(theme);
        }
    }

    public Theme findOrCreateByName(String themeName) {
        // Buscar si existe un theme con ese nombre
        List<Theme> existentes = themeRepository.findAll();
        for (Theme theme : existentes) {
            if (theme.getName().equalsIgnoreCase(themeName)) {
                return theme;
            }
        }
        // Si no existe, lo creo, lo guardo y lo retorno
        Theme nuevo = new Theme();
        nuevo.setName(themeName);
        themeRepository.save(nuevo);
        return nuevo;
    }
}