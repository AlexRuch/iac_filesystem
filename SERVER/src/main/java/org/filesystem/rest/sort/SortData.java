package org.filesystem.rest.sort;


import org.filesystem.rest.entity.FileEntity;

import java.util.List;

public interface SortData {
    List<FileEntity> naturalSort(List<FileEntity> fileEntityList);
}
