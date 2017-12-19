package hbidemo.core.test3;


import hbidemo.core.test3.impl.StandardUploader;


public abstract class UploaderFactory {
    public UploaderFactory() {
    }

    public static Uploader getMutiUploader() {
        return new StandardUploader();
    }

    public static Uploader getSingleUploader() {
        return null;
    }
}
