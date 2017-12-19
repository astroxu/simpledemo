package hbidemo.core.test3.util;

import java.io.File;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


import hbidemo.core.test3.ContentTypeFilter;
import hbidemo.core.test3.Exception.StoragePathNotExsitException;
import hbidemo.core.test3.FileInfo;
import hbidemo.core.test3.Uploader;
import hbidemo.core.test3.dto.Picture;
import hbidemo.core.test3.impl.StandardController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadUtil {
    public static final String BASE_PATH = "${basePath}";
    private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
    protected UploadUtil() {
    }

    //初始化上传参数
    public static void initUploaderParams(Uploader uploader) throws StoragePathNotExsitException {
        //设置上传文件大小限制
        Long allowedFileSize = 100000000L;
        uploader.setSingleFileSize(allowedFileSize);
        //设置上传路径
        final String path = "D:\\image\\1";
        //final String path = "${basePath}"+"/resources/supplierpictures";
        uploader.setController(new StandardController() {
            public String getFileDir(HttpServletRequest request, String orginalName) {
                String newPath = path;
                logger.debug("newPath1" + newPath);
                if (newPath.contains("${basePath}")) {
                    newPath = newPath.replace("${basePath}", request.getSession().getServletContext().getRealPath("/"));
                    logger.debug("newPath2" + newPath);
                }
                File f = new File(newPath);
                if (!f.exists()) {
                    f.mkdirs();
                }

                return f.getAbsolutePath() + File.separator;
            }

            //获取文件绝对路径
            public String urlFix(HttpServletRequest request, File file) {
                logger.debug("getAbsolutePath" + file.getAbsolutePath());
                return file.getAbsolutePath();
            }
        });


    //设置允许的文件格式
    final String[] allowedExts = {".jpg",".png","gif","jpeg","bmp"};
    //设置i文件过滤
    uploader.setFilter(new ContentTypeFilter() {
        public boolean isAccept(String orginalName, String contentType) {
            String[] var3 = allowedExts;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String ext = var3[var5];
                if (orginalName.toLowerCase().endsWith(ext)) {
                    return true;
                }
            }

            return false;
        }
    });


    }
    public static Picture genPicture(FileInfo f) {
        Picture picture = new Picture();
        picture.setPicName(f.getOriginalName());
        picture.setPicSize(String.valueOf(f.getFile().length()));
        picture.setPicSaveDate(new Date());
        picture.setPicPath(f.getUrl());
        picture.setPicType(f.getContentType());
        return picture;
    }


    //删除文件
    public static void deleteFile(String path) {
        logger.debug("path: " + path);
        if (!StringUtils.isBlank(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }

        }
    }
}
