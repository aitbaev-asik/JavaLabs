package com.example.crud.service;

import com.example.crud.entity.Entity;
import com.example.crud.entity.SubEntity;
import com.example.crud.repository.EntityRepository;
import com.example.crud.repository.SubEntityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubEntityService {

    private final SubEntityRepository subEntityRepository;
    private final EntityRepository entityRepository;

    public SubEntityService(SubEntityRepository subEntityRepository, EntityRepository entityRepository) {
        this.subEntityRepository = subEntityRepository;
        this.entityRepository = entityRepository;
    }

    // Получаем все SubEntity для данного Entity
    public List<SubEntity> getSubEntitiesByEntityId(Long entityId) {
        return subEntityRepository.findByEntityId(entityId);
    }

    // Сохраняем SubEntity – обязательно получаем существующий Entity по entityId
    public SubEntity saveSubEntity(SubEntity subEntity, Long entityId) {
        Entity entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new IllegalArgumentException("Entity с ID " + entityId + " не найден"));
        subEntity.setEntity(entity);
        entity.getSubEntities().add(subEntity);
        return subEntityRepository.save(subEntity);
    }

    public void deleteSubEntity(Long id) {
        subEntityRepository.deleteById(id);
    }

    // Получение SubEntity по id (для редактирования)
    public SubEntity getSubEntityById(Long id) {
        return subEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubEntity not found"));
    }

    // Обновление SubEntity (только поля, например, value)
    public SubEntity updateSubEntity(Long id, String value) {
        SubEntity subEntity = getSubEntityById(id);
        subEntity.setValue(value);
        return subEntityRepository.save(subEntity);
    }
}
