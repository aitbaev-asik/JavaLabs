package com.example.crud.service;

import com.example.crud.entity.Entity;
import com.example.crud.repository.EntityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntityService {
    private final EntityRepository repository;

    public EntityService(EntityRepository repository) {
        this.repository = repository;
    }

    public List<Entity> getAllEntities() {
        return repository.findAll();
    }

    public Entity saveEntity(Entity entity) {
        return repository.save(entity);
    }

    public void deleteEntity(Long id) {
        repository.deleteById(id);
    }

    public Entity updateEntity(Long id, Entity newEntity) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(newEntity.getName());
                    entity.setDescription(newEntity.getDescription());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Entity getEntityById(Long id) {
        return repository.findByIdWithSubEntities(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }
}
