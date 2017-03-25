package org.filesystem.rest.entity;

import org.filesystem.rest.enums.FileTypeEnum;

import javax.persistence.*;

@Entity(name = "file_db")
@Table(name = "dir_file")
public class FileEntity {

    @Id
    @GeneratedValue
    private int file_id;

    @Column
    private String file_name;

    @Column
    @Enumerated(EnumType.STRING)
    private FileTypeEnum file_type;

    @Column
    private Long file_size;

    @ManyToOne
    @JoinColumn(name = "path_id")
    private PathEntity file_path;

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public FileTypeEnum getFile_type() {
        return file_type;
    }

    public void setFile_type(FileTypeEnum file_type) {
        this.file_type = file_type;
    }

    public Long getFile_size() {
        return file_size;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public PathEntity getFile_path() {
        return file_path;
    }

    public void setFile_path(PathEntity file_path) {
        this.file_path = file_path;
    }
}

