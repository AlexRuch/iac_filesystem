package org.filesystem.rest.service;

import org.filesystem.rest.dataForClient.FileData;
import org.filesystem.rest.entity.FileEntity;
import org.filesystem.rest.entity.PathEntity;

import java.util.List;

public interface FileEntityService {

    List<FileData> getByPath(int path_id);
    String addFile(PathEntity pathEntity);
}
