package org.filesystem.rest.service.impl;

import org.filesystem.rest.dataForClient.PathData;
import org.filesystem.rest.entity.PathEntity;
import org.filesystem.rest.repository.PathEntityRepository;
import org.filesystem.rest.service.FileEntityService;
import org.filesystem.rest.service.PathEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("fileEntityService")
public class PathEntityServiceImpl implements PathEntityService {

    @Autowired
    private PathEntityRepository pathRepository;

    @Autowired
    private FileEntityService fileService;

    @Transactional
    @Override
    public List<PathData> getAllPath() {
        List<PathData> pathDataList = new ArrayList<>();
        for (PathEntity pathEntity : pathRepository.findAll()) {
            pathDataList.add(new PathData(
                    pathEntity.getPath_id(),
                    pathEntity.getCreate_date(),
                    pathEntity.getPath(),
                    pathEntity.getDir_count(),
                    pathEntity.getFile_count(),
                    pathEntity.getFiles_size()
            ));
        }
        return pathDataList;
    }

    @Override
    public String addPath(String path) {
        PathEntity pathEntity = new PathEntity();
        pathEntity.setPath(path);
        pathEntity.setCreate_date(currentTime());
        return fileService.addFile(pathEntity);
    }

    private String currentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentTime = new Date();
        return sdf.format(currentTime);
    }

}
