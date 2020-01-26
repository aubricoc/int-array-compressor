package cat.aubricoc.intarraycompressor.service;

import cat.aubricoc.intarraycompressor.exception.CompressorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

public class CompressorService {

    private static final CompressorService INSTANCE = new CompressorService();

    private CompressorService() {
        super();
    }

    public static CompressorService getInstance() {
        return INSTANCE;
    }

    public void compress(File origin, File destination) {
        try (FileInputStream input = new FileInputStream(origin);
                FileOutputStream output = new FileOutputStream(destination);
                DeflaterOutputStream deflater = new DeflaterOutputStream(output);) {
            int b;
            while ((b = input.read()) != -1) {
                deflater.write((byte) b);
            }
        } catch (IOException e) {
            throw new CompressorException("Error when compress file", e);
        }
    }
}
