package cat.aubricoc.intarraycompressor.exception;

public class InvalidOperationException extends CompressorException {

    private static final long serialVersionUID = 1L;

    public InvalidOperationException(String param) {
        super("Invalid operation: " + param);
    }
}
