package app.service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static app.utility.Copier.copyData;

public class DecompressService {
    private static final String OPERATION_SYSTEM = System.getProperty("os.name");
    private static final String CURRENT_DIR = System.getProperty("user.dir");

    public boolean decompressFile(String zipFileName) throws Exception {
        Path archivePath;
        if (OPERATION_SYSTEM.startsWith("Windows")) {
            archivePath = Paths.get(CURRENT_DIR + "\\" + zipFileName);
        } else {
            archivePath = Paths.get(CURRENT_DIR + "/" + zipFileName);
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(archivePath))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                Path fileFullName = Paths.get(CURRENT_DIR).resolve(fileName);

                Path parent = fileFullName.getParent();
                if (Files.notExists(parent))
                    Files.createDirectories(parent);

                try (OutputStream outputStream = Files.newOutputStream(fileFullName)) {
                    copyData(zipInputStream, outputStream);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
