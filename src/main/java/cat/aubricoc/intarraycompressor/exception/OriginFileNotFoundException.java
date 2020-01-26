package cat.aubricoc.intarraycompressor.exception;

public class OriginFileNotFoundException extends CompressorException {

    private static final long serialVersionUID = 1L;

    public OriginFileNotFoundException(String file) {
        super("File '" + file + "' not found");
    }
}
