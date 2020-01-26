package cat.aubricoc.intarraycompressor.config;

import cat.aubricoc.intarraycompressor.exception.RequiredParametersException;

public class ConfigurationParser {

    private ConfigurationParser() {
        throw new UnsupportedOperationException();
    }

    public static Configuration parse(String... args) {
        if (args == null || args.length < 3) {
            throw new RequiredParametersException();
        }
        Operation operation = Operation.getByParameter(args[0]);
        return new Configuration(operation, args[1], args[2]);
    }
}
