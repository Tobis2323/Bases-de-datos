package miContenido.Service;

import miContenido.Repository.AgeGroupRepository;
import miContenido.model.AgeGroup;

public class AgeGroupService {
    private final AgeGroupRepository ageGroupRepository;

    public AgeGroupService() {
        this.ageGroupRepository = new AgeGroupRepository();
    }

    public java.util.List<miContenido.model.AgeGroup> getAllAgeGroups() {
        return ageGroupRepository.findAll();
    }

    public miContenido.model.AgeGroup getAgeGroupById(Integer id) {
        return ageGroupRepository.findById(id);
    }

    public void saveAgeGroup(miContenido.model.AgeGroup ageGroup) {
        ageGroupRepository.save(ageGroup);
    }

    public void updateAgeGroup(miContenido.model.AgeGroup ageGroup) {
        ageGroupRepository.update(ageGroup);
    }

    public void deleteAgeGroup(miContenido.model.AgeGroup ageGroup) {
        ageGroupRepository.delete(ageGroup);
    }

    // ✅ NUEVO MÉTODO PARA GUARDAR UNA LISTA ENTERA
    public void agregarArrayList(java.util.List<miContenido.model.AgeGroup> ageGroups) {
        for (miContenido.model.AgeGroup ageGroup : ageGroups) {
            ageGroupRepository.save(ageGroup);
        }
    }

    public AgeGroup findOrCreateByCode(String ageCode) {
        // Buscar si existe un age group con ese código
        java.util.List<miContenido.model.AgeGroup> existentes = ageGroupRepository.findAll();
        for (miContenido.model.AgeGroup ageGroup : existentes) {
            if (ageGroup.getCode().equalsIgnoreCase(ageCode)) {
                return ageGroup;
            }
        }
        // Si no existe, lo creo, lo guardo y lo retorno
        miContenido.model.AgeGroup nuevo = new miContenido.model.AgeGroup();
        nuevo.setCode(ageCode);
        ageGroupRepository.save(nuevo);
        return nuevo;
    }

}
