package redisbook.ch7_practice.visitcount;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redisbook.util.JedisHelper;

import java.util.List;

import static org.junit.Assert.*;

public class VisitCountTest {
    static JedisHelper helper;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        helper.destoryPool();
    }

    @Test
    public void testAddVisit() {
        VisitCount visitCount = new VisitCount(helper);
        assertNotNull(visitCount);

        assertTrue(visitCount.addVisit("52") > 0);
        assertTrue(visitCount.addVisit("180") > 0);
        assertTrue(visitCount.addVisit("554") > 0);
    }

    @Test
    public void testGetVisitCount() {
        VisitCount visitCount = new VisitCount(helper);
        assertNotNull(visitCount);

        List<String> result = visitCount.getVisitCount("52", "180", "554");
        assertNotNull(result);
        assertTrue(result.size() == 3);

        long sum = 0;
        for (String count : result) {
            sum = sum + Long.parseLong(count);
        }
        String totalCount = visitCount.getVisitTotalCount();

        assertEquals(String.valueOf(sum), totalCount);
    }
}
