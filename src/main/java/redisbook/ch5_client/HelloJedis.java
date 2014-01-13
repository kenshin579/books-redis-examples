package redisbook.ch5_client;

import redis.clients.jedis.Jedis;

/**
 * first Jedis sample
 */
public class HelloJedis {
    /**
     * main function
     *
     * @param args none
     */
    public static void main(String[] args) {
        // connect to redis server running on 192.168.56.102
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("cloudoffice");

        // set data to key redisbook
        String result = jedis.set("redisbook", "hello redis!");
        System.out.println(result);

        // get data from key redisbook
        System.out.println(jedis.get("redisbook"));
    }
}
