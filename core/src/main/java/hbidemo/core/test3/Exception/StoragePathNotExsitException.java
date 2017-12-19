package hbidemo.core.test3.Exception;


import com.hand.hap.core.exception.BaseException;

public class StoragePathNotExsitException extends BaseException {
    private static final long serialVersionUID = 9046687211507280533L;
    private static final String STORAGE_PATH_NOT_EXSIT = "msg.error.system.storagepath.not_exsit";

    public StoragePathNotExsitException() {
        super("msg.error.system.storagepath.not_exsit", "msg.error.system.storagepath.not_exsit", new Object[0]);
    }
}