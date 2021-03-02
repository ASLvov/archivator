package app.service;

import app.domain.Directory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static app.utility.Copier.copyData;

public class CompressService {
    private static final String OPERATION_SYSTEM = System.getProperty("os.name");
    private static final String CURRENT_DIR = System.getProperty("user.dir");

    public boolean compressFiles(List<String> paths) {
        Path archivePath;
        if (OPERATION_SYSTEM.startsWith("Windows")) {
            archivePath = Paths.get(CURRENT_DIR + "\\archive.zip");
        } else {
            archivePath = Paths.get(CURRENT_DIR + "/archive.zip");
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(archivePath))) {
            for (String p : paths) {
                addToArchive(zipOutputStream, Paths.get(p));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addToArchive(ZipOutputStream zipOutputStream, Path p) throws Exception {
        if (Files.isDirectory(p)) {
            Directory dir = new Directory(p);

            for (Path fileName : dir.getContent()) {
                addNewZipEntry(zipOutputStream, p.getParent(), fileName);
            }

        } else if (Files.isRegularFile(p)) {

            addNewZipEntry(zipOutputStream, p.getParent(), p.getFileName());
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            ZipEntry entry = new ZipEntry(fileName.toString());

            zipOutputStream.putNextEntry(entry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }
}