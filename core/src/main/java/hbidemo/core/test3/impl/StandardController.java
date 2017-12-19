package hbidemo.core.test3.impl;

import hbidemo.core.test3.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.UUID;

public class StandardController implements Controller {
    private static final String TOP_PATH = "picture";

    public StandardController() {
    }

    public String getFileDir(HttpServletRequest request, String orginalName) {
        File f = new File(request.getSession().getServletContext().getRealPath("picture" + File.separator + FileUtils.formatYMD(new Date())) + File.separator);
        if (!f.exists()) {
            f.mkdirs();
        }

        return f.getAbsolutePath() + File.separator;
    }

    public String newName(String orginalName) {
        return UUID.randomUUID().toString();
    }

    public String urlFix(HttpServletRequest request, File file) {
        String projectName = request.getContextPath().substring(1, request.getContextPath().length());
        return file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(projectName) + projectName.length());
    }
}
