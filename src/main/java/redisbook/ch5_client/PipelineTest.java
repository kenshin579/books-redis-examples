package redisbook.ch5_client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.net.UnknownHostException;

public class PipelineTest {
    //    private static final int TOTAL_OPERATIONS = 10000000;
    private static final int TOTAL_OPERATIONS = 100000;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();

        long start = System.currentTimeMillis();

        String key, value;
        Pipeline p = jedis.pipelined();
        for (int n = 0; n <= TOTAL_OPERATIONS; n++) {
            key = value = String.valueOf("key" + (100000000 + n));
            p.set(key, "-test-" + value);
        }
        p.sync();

        jedis.disconnect();

        long elapsed = now() - start;
        System.out.println("초당 처리 건수 " + TOTAL_OPERATIONS / elapsed * 1000f);
        System.out.println("소요 시간 " + elapsed / 1000f + "초");
    }

    private static long now() {
        return System.currentTimeMillis();
    }
} 
