package cat.aubricoc.intarraycompressor;

import cat.aubricoc.intarraycompressor.config.Configuration;
import cat.aubricoc.intarraycompressor.config.ConfigurationParser;
import cat.aubricoc.intarraycompressor.exception.CompressorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompressorRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CompressorRunner.class);

    public static void main(String... args) {
        LOG.info("Integer Array Compressor starts...");
        try {
            Configuration config = ConfigurationParser.parse(args);
            LOG.info("{}", config);
            Compressor.getInstance().run(config);
            LOG.info("Integer Array Compressor finished!");
        } catch (CompressorException e) {
            LOG.error("Failed Compressor", e);
        }
    }
}
