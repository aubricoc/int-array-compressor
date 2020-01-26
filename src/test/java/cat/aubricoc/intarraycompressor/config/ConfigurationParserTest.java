package cat.aubricoc.intarraycompressor.config;

import cat.aubricoc.intarraycompressor.exception.InvalidOperationException;
import cat.aubricoc.intarraycompressor.exception.RequiredParametersException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigurationParserTest {

    private static final String FILE_1 = "/file.orig";
    private static final String FILE_2 = "/file.dest";

    @Test(expected = RequiredParametersException.class)
    public void testParseNoArgs() {
        ConfigurationParser.parse();
    }

    @Test(expected = RequiredParametersException.class)
    public void testParseEmptyString() {
        ConfigurationParser.parse("");
    }

    @Test(expected = RequiredParametersException.class)
    public void testParseInsufficientParameters() {
        ConfigurationParser.parse(FILE_1, FILE_2);
    }

    @Test(expected = InvalidOperationException.class)
    public void testParseInvalidOperation() {
        ConfigurationParser.parse("-x", FILE_1, FILE_2);
    }

    @Test
    public void testParseWithCompressOperation() {
        Configuration config = ConfigurationParser.parse("-c", FILE_1, FILE_2);
        verifyResult(config, Operation.COMPRESS);
    }

    @Test
    public void testParseWithDecompressOperation() {
        Configuration config = ConfigurationParser.parse("-d", FILE_1, FILE_2);
        verifyResult(config, Operation.DECOMPRESS);
    }

    private void verifyResult(Configuration config, Operation operation) {
        assertNotNull(config);
        assertEquals(operation, config.getOperation());
        assertEquals(FILE_1, config.getOriginFile());
        assertEquals(FILE_2, config.getDestionationFile());
    }
}
