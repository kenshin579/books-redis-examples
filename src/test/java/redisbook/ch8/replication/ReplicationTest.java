/******************************************************
 * author : Kris Jeong
 * published : 2013. 6. 15.
 * project name : redis-book
 *
 * Email : smufu@naver.com, smufu1@hotmail.com
 *
 * Develop JDK Ver. 1.6.0_13
 *
 * Issue list.
 *
 * 	function.
 *     1. 
 *
 ********** process *********
 *
 ********** edited **********
 *
 ******************************************************/
package redisbook.ch8.replication;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redisbook.ch7_practice.redislogger.KeyMaker;

import static org.junit.Assert.fail;


/**
 * @author <A HREF="mailto:smufu@naver.com">kris jeong</A> smufu@naver.com
 *         <p/>
 *         class desc
 */
public class ReplicationTest {
    private static final int TEST_COUNT = 100000;

    static Jedis master;

    static Jedis slave;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        master = new Jedis("172.16.25.132", 6300);
        slave = new Jedis("localhost", 6301);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        master.disconnect();
        slave.disconnect();
    }

    @Test
    public void replicationTest() {
        DataWriter writer = new DataWriter(master);
        DataReader reader = new DataReader(slave);

        for (int i = 0; i < TEST_COUNT; i++) {
            KeyMaker keyMaker = new ReplicationKeyMaker(i);
            String value = "test value" + i;
            writer.set(keyMaker.getKey(), value);

            for (int j = 0; j < 3; j++) {
                String result = reader.get(keyMaker.getKey());

                if (value.equals(result)) {
                    // success case
                } else {
                    fail("The value Not match with a result. [" + value + "][" + result + "]");
                }
            }
        }
    }
}
