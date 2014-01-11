/*
 * 
 */
package Try.Executors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

// TODO: Auto-generated Javadoc

/**
 * Project: CollaborationChannel
 * User: tjlee
 * Date: 13. 4. 11.
 * Time: 오후 6:45
 */
final public class RedisPoolManager {

    /**
     * The Constant instance.
     */
    private static final RedisPoolManager instance = new RedisPoolManager();

    /**
     * The pool.
     */
    private JedisPool pool;

    /**
     * Instantiates a new redis pool manager.
     */
    private RedisPoolManager() {
    }

    /**
     * Gets the single instance of RedisPoolManager.
     *
     * @return single instance of RedisPoolManager
     */
    public static RedisPoolManager getInstance() {
        return instance;
    }

    /**
     * Connect.
     */
    public void connect() {
        // Create and set a JedisPoolConfig
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Maximum active connections to Redis instance
        poolConfig.setMaxActive(20);
        // Tests whether connection is dead when connection
        // retrieval method is called
        poolConfig.setTestOnBorrow(true);
        /* Some extra configuration */
        // Tests whether connection is dead when returning a
        // connection to the pool
        poolConfig.setTestOnReturn(true);
        // Number of connections to Redis that just sit there
        // and do nothing
        poolConfig.setMaxIdle(5);
        // Minimum number of idle connections to Redis
        // These can be seen as always open and ready to serve
        poolConfig.setMinIdle(1);
        // Tests whether connections are dead during idle periods
        poolConfig.setTestWhileIdle(true);
        // Maximum number of connections to test in each idle check
        poolConfig.setNumTestsPerEvictionRun(10);
        // Idle connection checking period
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        // Create the jedisPool
        pool = new JedisPool(poolConfig, Constants.REDIS_SERVER_ADDRESS, Constants.REDIS_SERVER_PORT, 0, Constants.REDIS_SERVER_PASSWORD);
    }

    /**
     * Release.
     */
    public void release() {
        pool.destroy();
    }

    /**
     * Gets the jedis.
     *
     * @return the jedis
     */
    public Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * Return jedis.
     *
     * @param jedis the jedis
     */
    public void returnJedis(Jedis jedis) {
        pool.returnResource(jedis);
    }
}
