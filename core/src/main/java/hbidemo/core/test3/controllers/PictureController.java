package hbidemo.core.test3.controllers;

import com.hand.hap.core.exception.TokenException;
import com.hand.hap.core.util.FormatUtil;
import com.hand.hap.security.TokenUtils;
import com.mysql.fabric.Server;
import hbidemo.core.test3.Exception.PictureException;
import hbidemo.core.test3.Exception.UniqueFileMutiException;
import hbidemo.core.test3.FileInfo;
import hbidemo.core.test3.Uploader;
import hbidemo.core.test3.UploaderFactory;
import hbidemo.core.test3.util.UploadFileUtil;
import hbidemo.core.test3.util.UploadUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbidemo.core.test3.dto.Picture;
import hbidemo.core.test3.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.*;

import static java.lang.Long.valueOf;
import static javax.swing.text.html.CSS.getAttribute;
import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;



/*
* 用upload附件上传
*
* */
@Controller
    public class PictureController extends BaseController{

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private IPictureService service;

    private static Logger logger = LoggerFactory.getLogger(PictureController.class);
    /*@RequestMapping(value = {"/supplier/picture/upload"}, method = {RequestMethod.POST})
    public Map<String, Object> upload(HttpServletRequest request, @RequestParam List<MultipartFile> files,@RequestParam String folder) throws Exception {


        // The Name of the Upload component is "files"
        if (files != null)
        {
            for(MultipartFile file:files)
            {
                // Some browsers send file names with full path.
                // We are only interested in the file name.
                String  fileName = file.getName();
                InputStream filePath = file.getInputStream();
                logger.debug("++++++++++++++++++++++++");
                logger.debug("fileName:" + fileName);
                logger.debug("++++++++++++++++++++++++");
                // The files are not actually saved in this demo
                // file.SaveAs(physicalPath);
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = UUID.randomUUID() + ".jpg";
        logger.debug("fileName:" + fileName);
        if(folder.isEmpty()){
            folder = UUID.randomUUID().toString();
        }
        String uploadpath="D:/mcdoc/" + folder;
        for(MultipartFile file : files) {
            UploadFileUtil.fileUpload(file.getInputStream(), fileName, uploadpath);
        }
        result.put("uploadpath", uploadpath);
        result.put("folder", folder);
        result.put("success", true);
        return result;
    }*/

   /* @RequestMapping({"/supplier/picture/query"})
    public ResponseData queryFilesBySourceTypeAndSourceKey(HttpServletRequest request,) {
        return new ResponseData(this.sysFileService.selectFilesBySourceTypeAndSourceKey(this.createRequestContext(request), ));
    }*/

    /*@RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request,
                                          @RequestParam MultipartFile uploadFile){
        Map<String,  Object> resultMap = new HashMap<>();
        resultMap = this.service.insert(uploadFile);
        return resultMap;
    }*/


    @RequestMapping(value = {"/supplier/picture/upload"}, method = {RequestMethod.POST})
    public ResponseData upload(HttpServletRequest request, Locale locale, String contextPath) throws Exception {
        //初始化
        Uploader uploader = UploaderFactory.getMutiUploader();
        uploader.init(request);

        ResponseData response = new ResponseData();

        //IRequest requestContext = this.createRequestContext(request);

        UploadUtil.initUploaderParams(uploader);
        List<FileInfo> fileInfos = uploader.upload();
        FileInfo f = (FileInfo)fileInfos.get(0);

        try {
            Picture picture = UploadUtil.genPicture(f);
         /*   Attachment attach = UploadUtil.genAttachment(category, sourceKey, requestContext.getUserId(), requestContext.getUserId());*/

                this.service.insert(picture);


            /*TokenUtils.generateAndSetToken(TokenUtils.getSecurityKey(request.getSession(false)), sysFile);*/
        } catch (Exception var15) {
            if (logger.isErrorEnabled()) {
                logger.error("database error", var15);
            }

            File file = f.getFile();
            if (file.exists()) {
                file.delete();
            }

            response.setSuccess(false);
        }
        String var1 = "10024";
        response.setMessage(this.messageSource.getMessage(var1, (Object[])null, locale));
        //response.setMessage(this.messageSource.getMessage("hap.upload_success", (Object[])null, locale));
        return response;


    }


    @RequestMapping({"/supplier/picture/detail", "/supplier/picture/download"})
    public void detail(HttpServletRequest request, HttpServletResponse response, @RequestParam String pictureId) throws Exception {
        IRequest requestContext = this.createRequestContext(request);
        Picture picture = this.service.selectByPrimaryKey1(requestContext, Long.valueOf(pictureId));
        //picture.set_token(token);
        //TokenUtils.checkToken(request.getSession(false), picture);
        File file = new File(picture.getPicPath());
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(picture.getPicName(), "UTF-8") + "\"");
            response.setContentType(picture.getPicType() + ";charset=" + "UTF-8");
            response.setHeader("Accept-Ranges", "bytes");
            int fileLength = (int)file.length();
            response.setContentLength(fileLength);
            if (fileLength > 0) {
                this.writeFileToResp(response, file);
            }

        } else {
            throw new PictureException("msg.warning.download.file.error", "msg.warning.download.file.error", new Object[0]);
        }
    }


    @RequestMapping({"/supplier/picture/query"})
    public ResponseData queryFilesBySourceTypeAndSourceKey(HttpServletRequest request) throws Exception {
        IRequest requestContext = this.createRequestContext(request);
        Long userid = requestContext.getUserId();
        logger.debug("userid" + userid);
        return new ResponseData(this.service.queryBySupplierId(this.createRequestContext(request), 2312L));
    }

    /*@RequestMapping({"/supplier/picture/remove"})
    public Map<String, Object> remove(HttpServletRequest request,@RequestBody List<Picture> pictureList) throws Exception {
        Map<String, Object> response = new HashMap();
        IRequest requestContext = this.createRequestContext(request);
        logger.debug("pictureList:" + pictureList.size());
        for(Picture picture:pictureList){
            Long pictureId = picture.getPictureId();
            this.service.deleteByPrimaryKey1(requestContext, pictureId);
        }
        //Picture picture = new Picture();
        //picture.setPictureId(Long.valueOf(pictureId));
        //this.service.deleteByPrimaryKey1(requestContext, Long.valueOf(pictureId));
        response.put("message", "success");
        return response;
    }
*/
    @RequestMapping({"/supplier/picture/remove"})
    public ResponseData remove(HttpServletRequest request,@RequestBody List<Picture> pictureList) throws Exception {
        Map<String, Object> response = new HashMap();
        IRequest requestContext = this.createRequestContext(request);
        logger.debug("pictureList:" + pictureList.size());
        for(Picture picture:pictureList){
            /*Long pictureId = picture.getPictureId();
            this.service.deleteByPrimaryKey1(requestContext, pictureId);*/
            this.service.deleteByPrimaryKey1(requestContext, picture);
        }
        //Picture picture = new Picture();
        //picture.setPictureId(Long.valueOf(pictureId));
        //this.service.deleteByPrimaryKey1(requestContext, Long.valueOf(pictureId));
        response.put("message", "success");
        return  new ResponseData(pictureList);
    }


    @RequestMapping(value = "/supplier/picture/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Picture> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/supplier/picture/remove1")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Picture> dto){
        service.batchDelete(dto);
        for(Picture picture:dto){
            /*Long pictureId = picture.getPictureId();
            this.service.deleteByPrimaryKey1(requestContext, pictureId);*/
            logger.debug("picture得到的路径" + picture.getPicPath());
            UploadUtil.deleteFile(picture.getPicPath());
        }
        return new ResponseData();
    }


    private void writeFileToResp(HttpServletResponse response, File file) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        InputStream inStream = new FileInputStream(file);
        Throwable var5 = null;

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            Throwable var7 = null;

            try {
                int readLength;
                while((readLength = inStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, readLength);
                }

                outputStream.flush();
            } catch (Throwable var30) {
                var7 = var30;
                throw var30;
            } finally {
                if (outputStream != null) {
                    if (var7 != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable var29) {
                            var7.addSuppressed(var29);
                        }
                    } else {
                        outputStream.close();
                    }
                }

            }
        } catch (Throwable var32) {
            var5 = var32;
            throw var32;
        } finally {
            if (inStream != null) {
                if (var5 != null) {
                    try {
                        inStream.close();
                    } catch (Throwable var28) {
                        var5.addSuppressed(var28);
                    }
                } else {
                    inStream.close();
                }
            }

        }

    }


  /*  @RequestMapping(value = {"/supplier/picture/upload"}, method = {RequestMethod.POST},produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String Save(List<HttpPostedFileBase> files)
    {
        // The Name of the Upload component is "files"
        if (files != null)
        {
            foreach (var file in files)
            {
                // Some browsers send file names with full path.
                // We are only interested in the file name.
                var fileName = Path.GetFileName(file.FileName);
                var physicalPath = Path.Combine(Server.MapPath("~/App_Data"), fileName);

                // The files are not actually saved in this demo
                // file.SaveAs(physicalPath);
            }
        }

        // Return an empty string to signify success
        return Content("");
    }*/


   /* @RequestMapping(value = {"/supplier/picture/upload"}, method = {RequestMethod.POST},produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String uploadSystemLogo(HttpServletRequest request) {
        return this.uploadSystemImage(request, 500, "logo.png", "SYS_LOGO_VERSION");
    }

    private String uploadSystemImage(HttpServletRequest request, int fileSizeWithKB, String fileName, String type) {
        File uploadFolder = new File(request.getServletContext().getRealPath("/") + "/resources/upload1");
        Locale locale = RequestContextUtils.getLocale(request);
        long fileSize = (long)(fileSizeWithKB * 1024);

        try {
            FileUtils.forceMkdir(uploadFolder);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(fileSize);
            List<FileItem> items = upload.parseRequest(request);
            Iterator var12 = items.iterator();

            while(var12.hasNext()) {
                FileItem fi = (FileItem)var12.next();
                if (!fi.isFormField()) {
                    String contentType = fi.getContentType();
                    if (StringUtils.contains(contentType, "image/")) {
                        InputStream is = fi.getInputStream();
                        Throwable var16 = null;

                        try {
                            FileOutputStream os = new FileOutputStream(new File(uploadFolder, fileName));
                            Throwable var18 = null;

                            try {
                                IOUtils.copy(is, os);
                            } catch (Throwable var43) {
                                var18 = var43;
                                throw var43;
                            } finally {
                                if (os != null) {
                                    if (var18 != null) {
                                        try {
                                            os.close();
                                        } catch (Throwable var42) {
                                            var18.addSuppressed(var42);
                                        }
                                    } else {
                                        os.close();
                                    }
                                }

                            }
                        } catch (Throwable var45) {
                            var16 = var45;
                            throw var45;
                        } finally {
                            if (is != null) {
                                if (var16 != null) {
                                    try {
                                        is.close();
                                    } catch (Throwable var41) {
                                        var16.addSuppressed(var41);
                                    }
                                } else {
                                    is.close();
                                }
                            }

                        }
                        return "<script>window.parent.uploadSuccess('" + this.getMessageSource().getMessage("hap.upload.success", (Object[])null, locale) + "')</script>";
                    }

                    return "<script>window.parent.showUploadMessage('" + this.getMessageSource().getMessage("hap.upload.file.type.mismatch", (Object[])null, locale) + "')</script>";
                }
            }
        } catch (Exception var47) {
            if (logger.isErrorEnabled()) {
                logger.error("upload error", var47);
            }

            if (var47 instanceof FileUploadBase.FileSizeLimitExceededException) {
                return "<script>window.parent.showUploadMessage('" + this.getMessageSource().getMessage("hap.upload.file.size.limit.exceeded", new String[]{FormatUtil.formatFileSize(fileSize)}, locale) + "')</script>";
            }
        }

        return "<script>window.parent.showUploadMessage('" + this.getMessageSource().getMessage("hap.upload.error", (Object[])null, locale) + "')</script>";
    }*/
    }