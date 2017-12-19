package hbidemo.core.test3.impl;

import hbidemo.core.test3.ContentTypeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DefaultContentTypeFilter implements ContentTypeFilter {
    private Map<String, String> extMaps = new HashMap();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultContentTypeFilter() {
        this.init();
    }

    private void init() {
        this.extMaps.put("png", "image/png");
        this.extMaps.put("gif", "image/gif");
        this.extMaps.put("bmp", "image/bmp");
        this.extMaps.put("ico", "image/x-ico");
        this.extMaps.put("jpeg", "image/jpeg");
        this.extMaps.put("jpg", "image/jpeg");
        this.extMaps.put("zip", "application/zip");
        this.extMaps.put("rar", "application/x-rar");
        this.extMaps.put("pdf", "application/pdf");
        this.extMaps.put("ppt", "application/vnd.ms-powerpoint");
        this.extMaps.put("xls", "application/vnd.ms-excel");
        this.extMaps.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        this.extMaps.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        this.extMaps.put("doc", "application/msword");
        this.extMaps.put("doc", "application/wps-office.doc");
        this.extMaps.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        this.extMaps.put("txt", "text/plain");
        this.extMaps.put("mp4", "video/mp4");
        this.extMaps.put("flv", "video/x-flv");
    }

    public boolean isAccept(String orginalName, String contentType) {
        String ext = null;
        try {
            ext = orginalName.substring(orginalName.lastIndexOf(46) + 1);
        } catch (Exception var5) {
            this.logger.error(var5.getMessage(), var5);
        }

        return this.extMaps.get(ext) != null && ((String)this.extMaps.get(ext)).equals(contentType);
    }
}
