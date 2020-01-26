package cat.aubricoc.intarraycompressor.exception;

public class CompressorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CompressorException(String message) {
        super(message);
    }

    public CompressorException(String message, Throwable cause) {
        super(message, cause);
    }
}
