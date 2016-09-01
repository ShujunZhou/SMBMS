import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alren on 16-8-28.
 */
public class ProdiverServiceTest {
    ProviderService providerService;
    @Before
    public void setup() throws Exception{
        providerService = new ProviderServiceImpl();
    }
    @Test
    public void deleteProviderTest() throws Exception {
        boolean flag = providerService.deleteProvider(4);
        Assert.assertTrue(flag);
    }
}
