package cat.aubricoc.intarraycompressor.exception;

public class RequiredParametersException extends CompressorException {

    private static final long serialVersionUID = 1L;

    public RequiredParametersException() {
        super("3 parameters are mandatory: operation, origin file and destination file");
    }
}
