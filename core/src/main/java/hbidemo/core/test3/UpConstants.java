package hbidemo.core.test3;

//常量
public class UpConstants {
    public static final String SUCCESS = "UPLOAD_SUCCESS";
    public static final String UPLOAD_IO_ERROR = "UPLOAD_IO_ERROR";
    public static final String FILE_CHUNK_ERROR = "FILE_CHUNK_ERROR";
    public static final String LIMIT_UPLOADNUM_ERROR = "LIMIT_UPLOADNUM_ERROR";
    public static final String ALL_SIZE_MAX_ERROR = "LIMIT_SIZE_MAX_ERROR";
    public static final String FILE_NULL_ERROR = "FILE_NULL_ERROR";
    public static final String FILE_DIR_ERROR = "FILE_DIR_ERROR";
    public static final String UPLOAD_ERROR = "UPLOAD_ERROR";
    public static final String FILE_PROCESS_ERROR = "FILE_PROCESS_ERROR";
    public static final String FILE_EMPTY_ERROR = "FILE_EMPTY_ERROR";
    public static final long ALL_FILE_SIZE = 104857600L;
    public static final long SINGLE_FILE_SIZE_MAX = 10485760L;
    public static final String NO_PROCESSOR_ERROR = null;
    public static final String NOT_FILE_ERROR = "NOT_FILE_ERROR";
    public static final String FILE_CHUNK = "FILE_CHUNK";
    public static final String CHARSET_UTF = "UTF-8";
    public static final String BEFORE_UPLOAD = "BEFORE_UPLOAD";
    public static final String BEFORE_PROCESS = "BEFORE_PROCESS";
    public static final String BEFORE_SAVEFILE_TO_DB = "BEFORE_SAVEFILE_TO_DB";
    public static final String BEFORE_FILE_TO_NFS = "BEFORE_FILE_TO_NFS";
    public static final String BEFORE_SAVE_PROFILE_TO_DB = "BEFORE_SAVE_PROFILE_TO_DB";
    public static final String BEFORE_PROFILE_TO_NFS = "BEFORE_PROFILE_TO_NFS";
    public static final String BEFORE_FINISHED = "BEFORE_FINISHED";
    public static final int MAX_FILE_NUM = 9999;
    public static final String ERROR_UPLOAD_SOURCE_TYPE_EMPTY = "msg.warning.upload.sourcetype.empty";
    public static final String ERROR_UPLOAD_SOURCE_TYPE_FOLDER_NOT_FOUND = "msg.warning.upload.folder.notfound";
    public static final String ERROR_UPLOAD_FILE_EMPTY_ERROR = "msg.warning.upload.file.empty";
    public static final String ERROR_UPLOAD_FILE_TYPE_MISMATCH = "hap.upload.file.type.mismatch";
    public static final String ERROR_UPLOAD_FILE_SIZE_ERROR = "hap.upload.file.size.limit.exceeded";
    public static final String ERROR_DOWNLOAD_FILE_ERROR = "msg.warning.download.file.error";
    public static final String SINGLE_FILE_SIZE_MAX_ERROR = "SINGLE_FILE_SIZE_MAX_ERROR";
    public static final String ERROR_UPLOAD_SINGLE_FILE_SIZE_MAX_ERROR = "msg.warning.upload";
    public static final String FILE_ALREADY_UPLOADED_ERROR = "FILE_ALREADY_UPLOADED_ERROR";

    protected UpConstants() {
    }
}
