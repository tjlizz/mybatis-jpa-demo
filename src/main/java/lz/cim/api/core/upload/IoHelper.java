package lz.cim.api.core.upload;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class IoHelper {

    public static void judeDirExists(String path) {

        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdirs();
        }

    }

    public static void copyFile(File source, File dest)
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
}
