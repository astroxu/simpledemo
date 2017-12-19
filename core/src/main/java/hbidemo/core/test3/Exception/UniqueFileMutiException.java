package hbidemo.core.test3.Exception;

import com.hand.hap.core.exception.BaseException;

public class UniqueFileMutiException extends BaseException {
    private static final long serialVersionUID = 9046687211507280533L;
    private static final String FILE_MUTI_ERROR = "msg.error.system.attach.file_muti_error";

    public UniqueFileMutiException() {
        super("msg.error.system.attach.file_muti_error", "msg.error.system.attach.file_muti_error", new Object[0]);
    }
}