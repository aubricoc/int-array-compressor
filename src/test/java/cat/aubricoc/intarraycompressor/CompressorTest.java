package cat.aubricoc.intarraycompressor;

import cat.aubricoc.intarraycompressor.config.Configuration;
import cat.aubricoc.intarraycompressor.config.Operation;
import cat.aubricoc.intarraycompressor.exception.DestinationFileAlreadyExistsException;
import cat.aubricoc.intarraycompressor.exception.OriginFileNotFoundException;
import cat.aubricoc.intarraycompressor.exception.RequiredParametersException;
import cat.aubricoc.intarraycompressor.service.CompressorService;
import cat.aubricoc.intarraycompressor.service.DecompressorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CompressorService.class, DecompressorService.class})
public class CompressorTest {

    @Mock
    private CompressorService compressorService;
    @Mock
    private DecompressorService decompressorService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(CompressorService.class);
        PowerMockito.mockStatic(DecompressorService.class);
        when(CompressorService.getInstance()).thenReturn(compressorService);
        when(DecompressorService.getInstance()).thenReturn(decompressorService);
    }

    @Test(expected = RequiredParametersException.class)
    public void testRunWithNullConfig() {
        Compressor.getInstance().run(null);
    }

    @Test(expected = RequiredParametersException.class)
    public void testRunWithNoActionConfig() {
        Configuration config = new Configuration(null, "xxx", "xxx");
        Compressor.getInstance().run(config);
    }

    @Test(expected = RequiredParametersException.class)
    public void testRunWithNoOriginFile() {
        Configuration config = new Configuration(Operation.COMPRESS, null, "xxx");
        Compressor.getInstance().run(config);
    }

    @Test(expected = RequiredParametersException.class)
    public void testRunWithNoDestinationFile() {
        Configuration config = new Configuration(Operation.COMPRESS, "xxx", null);
        Compressor.getInstance().run(config);
    }

    @Test(expected = OriginFileNotFoundException.class)
    public void testRunWithUnexistentOriginFile() {
        Configuration config = new Configuration(Operation.COMPRESS, "xxx", "xxx");
        Compressor.getInstance().run(config);
    }

    @Test(expected = DestinationFileAlreadyExistsException.class)
    public void testRunWithExistentOriginFile() {
        String file = ClassLoader.getSystemResource("integers").getPath();
        Configuration config = new Configuration(Operation.COMPRESS, file, file);
        Compressor.getInstance().run(config);
    }

    @Test
    public void testRunCompress() {
        String file = ClassLoader.getSystemResource("integers").getPath();
        Configuration config = new Configuration(Operation.COMPRESS, file, "xxx");
        Compressor.getInstance().run(config);
        verify(compressorService, times(1)).compress(any(), any());
    }

    @Test
    public void testRunDecompress() {
        String file = ClassLoader.getSystemResource("integers").getPath();
        Configuration config = new Configuration(Operation.DECOMPRESS, file, "xxx");
        Compressor.getInstance().run(config);
        verify(decompressorService, times(1)).decompress(any(), any());
    }
}
