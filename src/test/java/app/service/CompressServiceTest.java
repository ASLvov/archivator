package app.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompressServiceTest {

    private CompressService compressService = new CompressService();
    private static final String CURRENT_PATH = System.getProperty("user.dir");
    private static final String OS = System.getProperty("os.name");
    private static final List<Path> PATHS = new ArrayList<>();

    @BeforeClass
    public static void prepareTestFiles() throws IOException {
        if (OS.startsWith("Windows")) {
            generateWindowsPaths();
        } else {
            generateUnixPaths();
        }
    }

    private static void generateWindowsPaths() throws IOException {
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir"));
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir\\1.txt"));
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir\\subdir1"));
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir\\subdir1\\2.txt"));
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir\\subdir2"));
        PATHS.add(Paths.get(CURRENT_PATH + "\\test_dir\\subdir2\\3.txt"));
        for (Path p: PATHS)
        {
            File file = new File(String.valueOf(p));
            if (!file.getName().contains(".txt")) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
        }
    }

    private static void generateUnixPaths() throws IOException {
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir"));
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir/1.txt"));
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir/subdir1"));
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir/subdir1/2.txt"));
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir/subdir2"));
        PATHS.add(Paths.get(CURRENT_PATH + "/test_dir/subdir2/3.txt"));
        for (Path p: PATHS)
        {
            File file = new File(String.valueOf(p));
            if (!file.getName().contains(".txt")) {
                file.mkdir();
            } else {
                file.createNewFile();
            }
        }
    }

    @Test
    public void compressFiles() {
        List<Path> pathsToCompress = new ArrayList<>();
        pathsToCompress.add(PATHS.get(1));
        pathsToCompress.add(PATHS.get(2));
        Assert.assertTrue(compressService.compressFiles(pathsToCompress));
    }
}