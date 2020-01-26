package cat.aubricoc.intarraycompressor.config;

import java.util.Objects;

public class Configuration {

    private final Operation operation;
    private final String originFile;
    private final String destionationFile;

    public Configuration(Operation operation, String originFile, String destionationFile) {
        this.operation = operation;
        this.originFile = originFile;
        this.destionationFile = destionationFile;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getOriginFile() {
        return originFile;
    }

    public String getDestionationFile() {
        return destionationFile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destionationFile, operation, originFile);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Configuration other = (Configuration) obj;
        return Objects.equals(destionationFile, other.destionationFile) && operation == other.operation
                && Objects.equals(originFile, other.originFile);
    }

    @Override
    public String toString() {
        return "Configuration: operation = " + operation + ", origin file = '" + originFile + "', destination file = '"
                + destionationFile + "'";
    }
}
