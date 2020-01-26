package cat.aubricoc.intarraycompressor.config;

import cat.aubricoc.intarraycompressor.exception.InvalidOperationException;
import java.util.stream.Stream;

public enum Operation {

    COMPRESS("-c"), DECOMPRESS("-d");

    private String parameter;

    Operation(String parameter) {
        this.parameter = parameter;
    }

    public static Operation getByParameter(String param) {
        return Stream.of(values()).filter(it -> it.parameter.equals(param)).findFirst()
                .orElseThrow(() -> new InvalidOperationException(param));
    }
}
