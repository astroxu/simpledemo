package hbidemo.core.test3;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public interface Controller {
    //获取文件路径
    String getFileDir(HttpServletRequest var1, String var2);
    //
    String newName(String var1);

    String urlFix(HttpServletRequest var1, File var2);
}
