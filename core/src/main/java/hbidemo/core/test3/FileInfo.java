package hbidemo.core.test3;

import java.io.File;

//文件信息
public interface FileInfo {
    File getFile();

    String getUrl();

    String getOriginalName();

    String getContentType();
}
