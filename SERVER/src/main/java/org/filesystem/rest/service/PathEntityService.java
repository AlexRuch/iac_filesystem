package org.filesystem.rest.service;

import org.filesystem.rest.dataForClient.PathData;
import org.filesystem.rest.entity.PathEntity;

import java.util.List;

public interface PathEntityService {

    List<PathData> getAllPath();
    void addPath(String path);
}
