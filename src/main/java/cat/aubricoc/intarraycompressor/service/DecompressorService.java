package cat.aubricoc.intarraycompressor.service;

import cat.aubricoc.intarraycompressor.exception.CompressorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

public class DecompressorService {

    private static final DecompressorService INSTANCE = new DecompressorService();

    private DecompressorService() {
        super();
    }

    public static DecompressorService getInstance() {
        return INSTANCE;
    }

    public void decompress(File origin, File destination) {
        try (FileInputStream input = new FileInputStream(origin);
                InflaterInputStream inflater = new InflaterInputStream(input);
                FileOutputStream output = new FileOutputStream(destination);) {
            int b;
            while ((b = inflater.read()) != -1) {
                output.write((byte) b);
            }
        } catch (IOException e) {
            throw new CompressorException("Error when decompress file", e);
        }
    }
}
