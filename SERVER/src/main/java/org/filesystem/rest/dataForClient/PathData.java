package org.filesystem.rest.dataForClient;

/**
 * Created by ralex on 3/25/17.
 */
public class PathData {
    private int path_id;
    private String create_time;
    private String path;
    private int dir_count;
    private int file_count;
    private String files_size;

    public PathData(int path_id, String create_time, String path, int dir_count, int file_count, String files_size){
        this.path_id = path_id;
        this.create_time = create_time;
        this.path = path;
        this.dir_count = dir_count;
        this.file_count = file_count;
        this.files_size = files_size;

    }

    public int getPath_id() {
        return path_id;
    }

    public String getPath() {
        return path;
    }

    public int getDir_count() {
        return dir_count;
    }

    public int getFile_count() {
        return file_count;
    }

    public String getFiles_size() {
        return files_size;
    }
}
