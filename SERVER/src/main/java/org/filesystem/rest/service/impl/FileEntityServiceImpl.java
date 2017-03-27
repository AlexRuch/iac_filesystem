package org.filesystem.rest.service.impl;

import org.filesystem.rest.dataForClient.FileData;
import org.filesystem.rest.entity.FileEntity;
import org.filesystem.rest.entity.PathEntity;
import org.filesystem.rest.enums.FileTypeEnum;
import org.filesystem.rest.repository.FileEntityRepository;
import org.filesystem.rest.repository.PathEntityRepository;
import org.filesystem.rest.service.FileEntityService;
import org.filesystem.rest.sort.SortData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class FileEntityServiceImpl implements FileEntityService {

    @Autowired
    FileEntityRepository fileRepository;

    @Autowired
    PathEntityRepository pathRepository;

    @Autowired
    SortData sortData;

    @Transactional
    @Override
    public List<FileData> getByPath(int path_id) {
        List<FileData> fileDataList = new LinkedList<>();
        List<FileEntity> fileEntityList;
        PathEntity pathEntity = pathRepository.findOne(path_id);

        fileEntityList = fileRepository.findByPath(pathEntity);


        fileEntityList = sortData.naturalSort(fileEntityList);

        for (FileEntity fileEntity : fileEntityList) {
            if (fileEntity.getFile_type() == FileTypeEnum.DIRECTORY) {
                fileDataList.add(new FileData(fileEntity.getFile_name(), "DIR"));
            }
        }

        for (FileEntity fileEntity : fileEntityList) {
            if (fileEntity.getFile_type() == FileTypeEnum.DOCUMENT) {
                fileDataList.add(new FileData(fileEntity.getFile_name(), fileSize(fileEntity.getFile_size())));
            }
        }
        return fileDataList;
    }


    @Transactional
    @Override
    public String addFile(PathEntity pathEntity) {

        if (Paths.get(pathEntity.getPath()).toFile().isDirectory()) {
            int file_count = 0;
            int dir_count = 0;
            Long file_size = 0L;
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


            file_size_str = fileSize(file_size);
            pathEntity.setDir_count(dir_count);
            pathEntity.setFile_count(file_count);
            pathEntity.setFiles_size(file_size_str);
            pathEntity.setFile_db(fileDBList);
            pathRepository.save(pathEntity);
            return "ok";
        } else {
            return "err";
        }
    }


    private String fileSize(double size) {
        String fileSize;
        if (size < 1024) {
            fileSize = String.valueOf(size) + "байт";
            return fileSize;
        } else if (size > (1024 * 1024 * 1024)) {
            fileSize = String.format("%(.2f", (size / (1024 * 1024 * 1024))) + "Гб";
            return fileSize;
        } else if (size > (1024 * 1024)) {
            fileSize = String.format("%(.2f", (size / (1024 * 1024))) + "Мб";
            return fileSize;
        } else if (size > 1024) {
            fileSize = String.format("%(.2f", (size / (1024))) + "Кб";
            return fileSize;
        } else return "";
    }

}

