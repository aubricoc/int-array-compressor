package cat.aubricoc.intarraycompressor.service;

import cat.aubricoc.intarraycompressor.exception.CompressorException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import me.lemire.integercompression.differential.IntegratedIntCompressor;
import org.apache.commons.io.FileUtils;

public class DecompressorService {

    private static final DecompressorService INSTANCE = new DecompressorService();

    private DecompressorService() {
        super();
    }

    public static DecompressorService getInstance() {
        return INSTANCE;
    }

    public void decompress(File origin, File destination) {
        try {
            IntegratedIntCompressor intCompressor = new IntegratedIntCompressor();
            Charset encoding = Charset.defaultCharset();
            String stringInput = FileUtils.readFileToString(origin, encoding);
            int[] arrayInput = Stream.of(stringInput.split(",")).mapToInt(Integer::parseInt).toArray();
            int[] arrayOutput = intCompressor.uncompress(arrayInput);
            String stringOutput = IntStream.of(arrayOutput).mapToObj(item -> item + ",").collect(Collectors.joining());
            FileUtils.writeStringToFile(destination, stringOutput, encoding);
        } catch (IOException e) {
            throw new CompressorException("Error when decompress int array", e);
        }
    }
}
