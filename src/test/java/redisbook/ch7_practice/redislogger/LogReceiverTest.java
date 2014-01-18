package redisbook.ch7_practice.redislogger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redisbook.util.JedisHelper;

public class LogReceiverTest {
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
    public void testLogger() {
        LogReceiver receiver = new LogReceiver();
        receiver.start();
    }
}
