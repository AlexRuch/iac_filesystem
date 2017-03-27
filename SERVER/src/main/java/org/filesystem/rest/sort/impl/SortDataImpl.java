package org.filesystem.rest.sort.impl;

import org.filesystem.rest.entity.FileEntity;
import org.filesystem.rest.sort.SortData;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SortDataImpl implements SortData {

    @Override
    public List<FileEntity> naturalSort(List<FileEntity> fileEntityList) {
        Collections.sort(fileEntityList, new NaturalOrderComparator());
        return fileEntityList;
    }
}

