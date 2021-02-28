package app.service;

import app.domain.Directory;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressService {
    private static final String OPERATION_SYSTEM = System.getProperty("os.name");

    public boolean compressFiles(List<Path> paths) {
        Path archivePath;
        if (OPERATION_SYSTEM.startsWith("Windows")) {
            archivePath = Paths.get(System.getProperty("user.dir") + "\\archive.zip");
        } else {
            archivePath = Paths.get(System.getProperty("user.dir") + "/archive.zip");
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(archivePath))) {
            for (Path p : paths) {
                addToArchive(zipOutputStream, p);
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

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}