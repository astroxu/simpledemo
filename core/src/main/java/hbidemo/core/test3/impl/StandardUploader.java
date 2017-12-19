package hbidemo.core.test3.impl;

import com.hand.hap.core.util.FormatUtil;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import hbidemo.core.test3.*;
import hbidemo.core.test3.Exception.PictureException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

public class StandardUploader implements Uploader {
    private static Logger logger = LoggerFactory.getLogger(StandardUploader.class);
    private HttpServletRequest request = null;
    private Map<String, String> params = new HashMap();
    private StandardFileChain chain = null;
    private Controller controller = null;
    private ContentTypeFilter filter;
    private List<FileInfo> fileInfos;
    private List<FileItem> fileItems = new ArrayList();
    private long singleFileSize = 1048576000L;
    private long allFileSize = 104857600L;
    private int maxFileNum = 9999;
    private String status = "UPLOAD_SUCCESS";
    private boolean isMultiPartFiled;

    public StandardUploader() {
    }


    public void init(HttpServletRequest request) {
        long allSize = 0L;
        int fileNum = 0;
        this.request = request;
        //文件信息
        this.fileInfos = new ArrayList();
        this.isMultiPartFiled = request instanceof MultipartHttpServletRequest;

        //文件类型过滤器，
        if (this.filter == null) {
            this.filter = new DefaultContentTypeFilter();
        }

        try {
            //判断是否分段上传
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                this.status = "NOT_FILE_ERROR";
                return;
            }

            this.chain = new StandardFileChain(this.fileInfos, this);
            //分部上传
            if (this.isMultiPartFiled) {
                MultipartFile multipartFile = ((MultipartHttpServletRequest)request).getFile("files");
                this.fileItems.add(new StandardUploader.MultipartFiledByFileItem(multipartFile));
            } else {
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                List<FileItem> items = upload.parseRequest(request);
                Iterator iter = items.iterator();

                FileItem item;
                while(iter.hasNext()) {
                    item = (FileItem)iter.next();
                    if (!item.isFormField()) {
                        allSize += item.getSize();
                        ++fileNum;
                    }
                }

                if (allSize > this.allFileSize) {
                    this.status = "LIMIT_SIZE_MAX_ERROR";
                    return;
                }

                if (fileNum > this.maxFileNum) {
                    this.status = "LIMIT_UPLOADNUM_ERROR";
                    return;
                }

                iter = items.iterator();

                while(iter.hasNext()) {
                    item = (FileItem)iter.next();
                    if (item.isFormField()) {
                        this.processFormField(item);
                    } else if (!item.isFormField()) {
                        this.fileItems.add(item);
                    }
                }
            }
        } catch (Exception var10) {
            this.status = "UPLOAD_ERROR";
            logger.info("文件上传错误" + var10);
            /*if (logger.isErrorEnabled()) {
                logger.error("文件上传错误", var10);
            }*/
        }

    }

    public List<FileInfo> upload() throws PictureException {
        if (this.controller == null) {
            this.controller = new StandardController();
        }

        if (this.fileItems != null && !this.fileItems.isEmpty()) {
            Iterator var1 = this.fileItems.iterator();

            while(var1.hasNext()) {
                FileItem f = (FileItem)var1.next();
                this.processUploadedFile(f);
            }
        }

        try {
            if (this.chain != null) {
                this.chain.doProcess();
            }
        } catch (Exception var3) {
            logger.info("文件上传错误" + var3);
            /*if (logger.isErrorEnabled()) {
                logger.error("文件处理发生错误", var3);
            }*/

            this.status = "FILE_PROCESS_ERROR";
        }

        return this.fileInfos;
    }

    private void validate(FileItem fileItem) throws PictureException {
        String fileName = this.getFileName(fileItem);
        if (fileItem.getSize() > 0L && !org.apache.commons.lang.StringUtils.isEmpty(fileName)) {
            if (fileItem.getSize() > this.singleFileSize) {
                throw new PictureException("hap.upload.file.size.limit.exceeded", "hap.upload.file.size.limit.exceeded", new String[]{FormatUtil.formatFileSize(this.singleFileSize)});
            } else {
               /* if (logger.isLoggable(2L)) {
                    logger.info("上传文件名为：{}  =====> 其 contentType: {} "+ fileName + fileItem.getContentType());
                }*/
                /*if (logger.isDebugEnabled()) {
                    logger.debug("上传文件名为：{}  =====> 其 contentType: {} ", fileName, fileItem.getContentType());
                }*/

                if (!this.filter.isAccept(fileItem.getName(), fileItem.getContentType())) {
                    throw new PictureException("hap.upload.file.type.mismatch", "hap.upload.file.type.mismatch", new Object[0]);
                }
            }
        } else {
            throw new PictureException("msg.warning.upload.file.empty", "msg.warning.upload.file.empty", new Object[0]);
        }
    }

    private String getFileName(FileItem item) {
        String fileName = item.getName();
        if (fileName != null) {
            int index = fileName.lastIndexOf(92);
            if (index != -1) {
                fileName = fileName.substring(index + 1);
            }
        }

        return fileName;
    }

    private void processUploadedFile(FileItem item) throws PictureException {
        if (!item.isFormField()) {
            DefaultFileInfo fileInfo = new DefaultFileInfo();
            String fileName = this.getFileName(item);
            fileInfo.setOriginalName(fileName);
            fileInfo.setContentType(item.getContentType());
            this.validate(item);

            try {
                File f = new File(this.controller.getFileDir(this.request, fileName) + this.controller.newName(fileName));
                item.write(f);
                fileInfo.setFile(f);
                fileInfo.setStatus("UPLOAD_SUCCESS");
                fileInfo.setUrl(this.controller.urlFix(this.request, f));
               /* if (logger.isDebugEnabled()) {
                    logger.debug(f.getAbsolutePath());
                }*/
            } catch (Exception var5) {
                logger.info("文件上传错误！"+ var5);
               /* if (logger.isErrorEnabled()) {
                    logger.error("文件上传错误！", var5);
                }*/

                fileInfo.setStatus("UPLOAD_ERROR");
                this.status = "UPLOAD_ERROR";
            }

            this.fileInfos.add(fileInfo);
        }

    }

    private void processFormField(FileItem item) throws IOException {
        if (item.isFormField()) {
            String name = item.getFieldName();
            String value = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
            this.params.put(name, value);
        }

    }

    private void processFormField(String name, String value) throws IOException {
        value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
        this.params.put(name, value);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void addFileProcessor(FileProcessor processor) {
        this.chain.addProcessor(processor);
    }

    public void doProcess() throws Exception {
    }

    public String getParams(String key) {
        return (String)this.params.get(key);
    }

    public void setParams(String key, String value) {
        this.request.setAttribute(key, value);
    }

    public void setSingleFileSize(long singleFileSize) {
        this.singleFileSize = singleFileSize;
    }

    public void setAllFileSize(long allFileSize) {
        this.allFileSize = allFileSize;
    }

    public void setMaxFileNum(int maxFileNum) {
        this.maxFileNum = maxFileNum;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setFilter(ContentTypeFilter filter) {
        this.filter = filter;
    }

    public static class MultipartFiledByFileItem implements FileItem {
        MultipartFile multipartFile = null;

        MultipartFiledByFileItem(MultipartFile multipartFile) {
            this.multipartFile = multipartFile;
        }

        public InputStream getInputStream() throws IOException {
            return this.multipartFile.getInputStream();
        }

        public String getContentType() {
            return this.multipartFile.getContentType();
        }

        public String getName() {
            return this.multipartFile.getOriginalFilename();
        }

        public boolean isInMemory() {
            return false;
        }

        public long getSize() {
            return this.multipartFile.getSize();
        }

        public byte[] get() {
            return new byte[0];
        }

        public String getString(String s) throws UnsupportedEncodingException {
            return null;
        }

        public String getString() {
            return null;
        }

        public void write(File file) throws Exception {
            this.multipartFile.transferTo(file);
        }

        public void delete() {
        }

        public String getFieldName() {
            return this.multipartFile.getName();
        }

        public void setFieldName(String s) {
        }

        public boolean isFormField() {
            return false;
        }

        public void setFormField(boolean b) {
        }

        public OutputStream getOutputStream() throws IOException {
            return null;
        }

        public FileItemHeaders getHeaders() {
            return null;
        }

        public void setHeaders(FileItemHeaders fileItemHeaders) {
        }
    }
}
