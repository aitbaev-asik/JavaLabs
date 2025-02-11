package com.example.crud.repository;

import com.example.crud.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {
    @Query("SELECT e FROM Entity e LEFT JOIN FETCH e.subEntities WHERE e.id = :id")
    Optional<Entity> findByIdWithSubEntities(@Param("id") Long id);
}
