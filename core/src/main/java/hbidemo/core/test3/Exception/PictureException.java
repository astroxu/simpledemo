package hbidemo.core.test3.Exception;

import com.hand.hap.core.exception.BaseException;


public class PictureException extends BaseException {
    public PictureException(String code, String descriptionKey, Object[] parameters) {
        super(code, descriptionKey, parameters);
    }
}
