package org.filesystem.rest.repository;

import org.filesystem.rest.entity.PathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
@Component
public interface PathEntityRepository extends JpaRepository<PathEntity, Integer>{
}
