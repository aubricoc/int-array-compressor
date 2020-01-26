package cat.aubricoc.intarraycompressor;

import cat.aubricoc.intarraycompressor.config.Configuration;
import cat.aubricoc.intarraycompressor.config.Operation;
import cat.aubricoc.intarraycompressor.exception.DestinationFileAlreadyExistsException;
import cat.aubricoc.intarraycompressor.exception.OriginFileNotFoundException;
import cat.aubricoc.intarraycompressor.exception.RequiredParametersException;
import cat.aubricoc.intarraycompressor.service.CompressorService;
import cat.aubricoc.intarraycompressor.service.DecompressorService;
import java.io.File;

public class Compressor {

    private static final Compressor INSTANCE = new Compressor();

    private Compressor() {
        super();
    }

    public static Compressor getInstance() {
        return INSTANCE;
    }

    public void run(Configuration config) {
        if (config == null || config.getOperation() == null || config.getOriginFile() == null
                || config.getDestionationFile() == null) {
            throw new RequiredParametersException();
        }
        File origin = new File(config.getOriginFile());
        File destination = new File(config.getDestionationFile());
        if (!origin.exists()) {
            throw new OriginFileNotFoundException(config.getOriginFile());
        }
        if (destination.exists()) {
            throw new DestinationFileAlreadyExistsException(config.getDestionationFile());
        }
        if (config.getOperation() == Operation.COMPRESS) {
            CompressorService.getInstance().compress(origin, destination);
        } else {
            DecompressorService.getInstance().decompress(origin, destination);
        }
    }
}
