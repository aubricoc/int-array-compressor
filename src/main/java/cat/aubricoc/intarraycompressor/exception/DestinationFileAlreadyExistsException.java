package cat.aubricoc.intarraycompressor.exception;

public class DestinationFileAlreadyExistsException extends CompressorException {

    private static final long serialVersionUID = 1L;

    public DestinationFileAlreadyExistsException(String file) {
        super("File '" + file + "' already exists");
    }
}
