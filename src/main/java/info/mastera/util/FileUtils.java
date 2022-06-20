package info.mastera.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtils {

    private static final String ERROR_URI_SYNTAX = "Error in URI syntax.";

    public static File getFileFromResource(@SuppressWarnings("SameParameterValue") String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException(String.format("File %s not found.", fileName));
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(ERROR_URI_SYNTAX, e);
            }
        }
    }

    public static boolean isFileExist(String filePath) {
        return new File(filePath).isFile();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createIfNotExists(String filePath) {
        if (!isFileExist(filePath)) {
            try {
                var file = new File(filePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("File was not created", e);
            }
        }
    }
}
