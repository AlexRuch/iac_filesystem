package org.filesystem.rest.dataForClient;

/**
 * Created by ralex on 3/25/17.
 */
public class FileData {
    private String file_name;
    private String file_size;

    public FileData(String file_name, String file_size){
        this.file_name = file_name;
        this.file_size = file_size;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_size() {
        return file_size;
    }
}
