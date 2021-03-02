package app.service;

import org.junit.Assert;
import org.junit.Test;

public class DecompressServiceTest {
    private DecompressService decompressService = new DecompressService();
    private static final String FILE_NAME = "archive.zip";

    @Test
    public void decompressFile() {
        try {
            Assert.assertTrue(decompressService.decompressFile(FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}