package redisbook;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;

public class JedisTest {

    /**
     * Simple test.
     */
    @Test
    public void simpleTest() {
        Jedis jedis = new Jedis("localhost");
        jedis.auth("cloudoffice");

        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        assertEquals("bar", value);
    }
}

