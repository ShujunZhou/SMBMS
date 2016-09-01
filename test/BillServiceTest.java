import cn.smbms.pojo.bill.Bill;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by alren on 16-8-27.
 */
public class BillServiceTest {
    BillService billService;
    @Before
    public void setUp() throws Exception {
        billService = new BillServiceImpl();
    }

    @Test
    public void testGetBillByProviderId() throws Exception {
        List<Bill> bills = billService.getBillByProviderId(2);
        Assert.assertEquals(1, bills.size());
    }
}
