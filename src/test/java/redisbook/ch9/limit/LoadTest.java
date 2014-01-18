package redisbook.ch9.limit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redisbook.ch9.BasicLoadMaker;
import redisbook.util.JedisHelper;


public class LoadTest {

    static JedisHelper helper;

    private static final int LOOP_COUNT = 500000;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        helper.destoryPool();
    }

    @Test
    public void redisLoadTest() {
        BasicLoadMaker loadMaker = new BasicLoadMaker(helper);

        for (int i = 0; i < LOOP_COUNT; i++) {
            loadMaker.setData(String.valueOf(i));
            loadMaker.getData();
        }
    }
}
