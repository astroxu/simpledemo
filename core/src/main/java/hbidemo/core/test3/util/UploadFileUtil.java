package hbidemo.core.test3.util;

import java.io.*;

public class UploadFileUtil {
    public static void fileUpload(InputStream in, String filename, String upload_path) throws FileNotFoundException, IOException {
        File uploadFolder = new File(upload_path);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdir();
        }
        File uploadFile = new File(uploadFolder + "/" + filename);
        OutputStream out = new FileOutputStream(uploadFile);
        byte[] buffer = new byte[1024 * 1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }
}
