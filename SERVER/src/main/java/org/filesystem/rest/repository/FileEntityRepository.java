package org.filesystem.rest.repository;

import org.filesystem.rest.entity.FileEntity;
import org.filesystem.rest.entity.PathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FileEntityRepository extends JpaRepository<FileEntity, Integer>{

    @Query("select f from file_db f where f.file_path = :path  order by f.file_name")
    List<FileEntity> findByPath(@Param("path")PathEntity pathEntity);
}
