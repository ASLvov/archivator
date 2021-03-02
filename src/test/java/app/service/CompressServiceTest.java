package app.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompressServiceTest {

    private CompressService compressService = new CompressService();
    private static final String CURRENT_PATH = System.getProperty("user.dir");
    private static final String OS = System.getProperty("os.name");
    private static final List<String> PATHS = new ArrayList<>();

    @BeforeClass
    public static void prepareTestFiles() throws IOException {
        if (OS.startsWith("Windows")) {
            generateWindowsPaths();
        } else {
            generateUnixPaths();
        }
    }

    private static void generateWindowsPaths() throws IOException {
        PATHS.add(CURRENT_PATH + "\\test_dir");
        PATHS.add(CURRENT_PATH + "\\test_dir\\1.txt");
        PATHS.add(CURRENT_PATH + "\\test_dir\\subdir1");
        PATHS.add(CURRENT_PATH + "\\test_dir\\subdir1\\2.txt");
        PATHS.add(CURRENT_PATH + "\\test_dir\\subdir2");
        PATHS.add(CURRENT_PATH + "\\test_dir\\subdir2\\3.txt");
        for (String p : PATHS) {
            File file = new File(p);
            if (!file.getName().contains(".txt")) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
        }
    }

    private static void generateUnixPaths() throws IOException {
        PATHS.add(CURRENT_PATH + "/test_dir");
        PATHS.add(CURRENT_PATH + "/test_dir/1.txt");
        PATHS.add(CURRENT_PATH + "/test_dir/subdir1");
        PATHS.add(CURRENT_PATH + "/test_dir/subdir1/2.txt");
        PATHS.add(CURRENT_PATH + "/test_dir/subdir2");
        PATHS.add(CURRENT_PATH + "/test_dir/subdir2/3.txt");
        for (String p : PATHS) {
            File file = new File(p);
            if (!file.getName().contains(".txt")) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
        }
    }

    @Test
    public void compressFiles() {
        List<String> pathsToCompress = new ArrayList<>();
        pathsToCompress.add(PATHS.get(1));
        pathsToCompress.add(PATHS.get(2));
        Assert.assertTrue(compressService.compressFiles(pathsToCompress));
    }
}