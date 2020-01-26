package cat.aubricoc.intarraycompressor;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressorRunnerTest {

    @Test
    public void testCompress() throws IOException {
        String file = ClassLoader.getSystemResource("integers").getPath();
        String compressed = file + ".compressed";
        File compressedFile = new File(compressed);
        try {
            CompressorRunner.main("-c", file, compressed);

            String expected = ClassLoader.getSystemResource("compressed").getPath();
            assertEquals(FileUtils.sizeOf(new File(expected)), FileUtils.sizeOf(compressedFile));
        } finally {
            FileUtils.forceDelete(compressedFile);
        }
    }

    @Test
    public void testDecompress() throws IOException {
        String file = ClassLoader.getSystemResource("compressed").getPath();
        String decompressed = file + ".decompressed";
        File decompressedFile = new File(decompressed);
        try {
            CompressorRunner.main("-d", file, decompressed);

            String expected = ClassLoader.getSystemResource("integers").getPath();
            assertEquals(FileUtils.sizeOf(new File(expected)), FileUtils.sizeOf(decompressedFile));
        } finally {
            FileUtils.forceDelete(decompressedFile);
        }
    }
}
