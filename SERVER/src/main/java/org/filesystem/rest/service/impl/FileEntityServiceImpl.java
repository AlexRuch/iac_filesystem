package org.filesystem.rest.service.impl;

import org.filesystem.rest.dataForClient.FileData;
import org.filesystem.rest.dataForClient.PathData;
import org.filesystem.rest.entity.FileEntity;
import org.filesystem.rest.entity.PathEntity;
import org.filesystem.rest.enums.FileTypeEnum;
import org.filesystem.rest.repository.FileEntityRepository;
import org.filesystem.rest.repository.PathEntityRepository;
import org.filesystem.rest.service.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileEntityServiceImpl implements FileEntityService{

    @Autowired
    FileEntityRepository fileRepository;

    @Autowired
    PathEntityRepository pathRepository;

    @Transactional
    @Override
    public List<FileData> getByPath(int path_id) {
        List<FileData> fileDataList = new ArrayList<>();
        PathEntity pathEntity = pathRepository.findOne(path_id);
        for(FileEntity fileEntity : fileRepository.findByPath(pathEntity)){
            if(fileEntity.getFile_type() == FileTypeEnum.DOCUMENT){
                fileDataList.add(new FileData(fileEntity.getFile_name(), String.valueOf(fileEntity.getFile_size())));
            }
            else if (fileEntity.getFile_type() == FileTypeEnum.DIRECTORY){
                fileDataList.add(new FileData(fileEntity.getFile_name(), "DIR"));
            }
        }
        return fileDataList;
    }

    @Transactional
    @Override
    public void addFile(PathEntity pathEntity) {
        int file_count = 0;
        int dir_count = 0;
        double file_size = 0;
        String file_size_str;
        FileEntity fileEntity;
        List<FileEntity> fileDBList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathEntity.getPath()))) {
            for (Path file : stream) {
                fileEntity = new FileEntity();
                fileEntity.setFile_name(file.toFile().getName());
                fileEntity.setFile_path(pathEntity);
                fileDBList.add(fileEntity);
                if (!file.toFile().isDirectory()) {
                    fileEntity.setFile_type(FileTypeEnum.DOCUMENT);
                    fileEntity.setFile_size(file.toFile().length());
                    file_size = file_size + file.toFile().length();
                    file_count++;
                } else {
                    fileEntity.setFile_type(FileTypeEnum.DIRECTORY);
                    dir_count++;
                }
                fileRepository.save(fileEntity);
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
        file_size_str = String.valueOf(file_size);
        pathEntity.setDir_count(dir_count);
        pathEntity.setFile_count(file_count);
        pathEntity.setFiles_size(file_size_str);
        pathEntity.setFile_db(fileDBList);
        pathRepository.save(pathEntity);
    }
}
