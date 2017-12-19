package hbidemo.core.test3.impl;

import hbidemo.core.test3.FileInfo;

import java.io.File;

public class DefaultFileInfo implements FileInfo {
    private File file = null;
    private String status = null;
    private String originalName = null;
    private String contentType = null;
    private String url;

    public DefaultFileInfo() {
    }

    public void setFile(File f) {
        this.file = f;
    }

    public File getFile() {
        return this.file;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}