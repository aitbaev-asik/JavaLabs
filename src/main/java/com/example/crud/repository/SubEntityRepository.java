package com.example.crud.repository;

import com.example.crud.entity.SubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubEntityRepository extends JpaRepository<SubEntity, Long> {
    List<SubEntity> findByEntityId(Long entityId);
}
