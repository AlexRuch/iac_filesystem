package org.filesystem.rest.entity;


import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.List;


@Entity(name = "path_db")
@Table(name = "dir_path")
public class PathEntity {

    @Id
    @GeneratedValue
    private int path_id;

    @Column
    private String path;

    @Column
    private String create_date;

    @Column
    private int file_count;

    @Column
    private int dir_count;

    @Column
    private String files_size;

    @OneToMany(mappedBy = "file_path")
    private List<FileEntity> file_db;


    public int getPath_id() {
        return path_id;
    }

    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getFile_count() {
        return file_count;
    }

    public void setFile_count(int file_count) {
        this.file_count = file_count;
    }

    public int getDir_count() {
        return dir_count;
    }

    public void setDir_count(int dir_count) {
        this.dir_count = dir_count;
    }

    public String getFiles_size() {
        return files_size;
    }

    public void setFiles_size(String files_size) {
        this.files_size = files_size;
    }

    public List<FileEntity> getFile_db() {
        return file_db;
    }

    public void setFile_db(List<FileEntity> file_db) {
        this.file_db = file_db;
    }
}
