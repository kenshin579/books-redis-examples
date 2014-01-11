package redisbook.ch5;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * connect to redis using jedis pool sample
 */
public class JedisPoolTest {
    /**
     * main function
     *
     * @param args none
     */
    public static void main(String[] args) {
        Config config = new Config();
        config.maxActive = 20; // ObjectPool의 최대 개수

        //최대 개수에 도달했을 떄 새로운 요청에 대한 처리 방법을 지정함
        // - 가용 연결이 생길 때까지 대기함
        config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;

        // create jedis pool for redis server running on 192.168.56.102
        JedisPool pool = new JedisPool(config, "localhost", 6379);

        // get the first jedis connection from jedis pool
        Jedis firstClient = pool.getResource();
        firstClient.auth("cloudoffice");

        // set data to redis using hset command
        firstClient.hset("info:자린고비", "이름", "자린고비");
        firstClient.hset("info:자린고비", "생일", "1970-12-20");

        // get the seconds jedis connection from jedis pool
        Jedis secondClient = pool.getResource();
        secondClient.auth("cloudoffice");

        // get date from redis using hgetall command, result is a map object
        Map<String, String> result = secondClient.hgetAll("info:자린고비");
        System.out.println("이름 : " + result.get("이름"));
        System.out.println("생일 : " + result.get("생일"));

        // return jedis first connection to jedis pool
        pool.returnResource(firstClient);

        // return jedis first connection to jedis pool
        pool.returnResource(secondClient);

        // destory the jedis pool
        pool.destroy();
    }
}
