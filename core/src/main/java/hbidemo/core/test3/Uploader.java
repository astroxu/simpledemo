package hbidemo.core.test3;

import hbidemo.core.test3.Exception.PictureException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface Uploader {
    void setFilter(ContentTypeFilter var1);

    void setController(Controller var1);

    void addFileProcessor(FileProcessor var1);

    void init(HttpServletRequest var1);

    List<FileInfo> upload() throws PictureException;

    String getParams(String var1);

    void setParams(String var1, String var2);

    void setSingleFileSize(long var1);

    void setAllFileSize(long var1);

    void setMaxFileNum(int var1);

    HttpServletRequest getRequest();
}
