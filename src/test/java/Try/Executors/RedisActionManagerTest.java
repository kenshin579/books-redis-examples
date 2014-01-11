package Try.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;


public class RedisActionManagerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void simpleTest() throws Exception {
        RedisPoolManager.getInstance().connect();
        Jedis jedis = RedisPoolManager.getInstance().getJedis();

        jedis.auth("cloudoffice");

        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        assertEquals("bar", value);

    }
}
