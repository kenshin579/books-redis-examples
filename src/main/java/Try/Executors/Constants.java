/*
 * 
 */
package Try.Executors;

import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc

/**
 * The Class Constants.
 */
public final class Constants {
    private Constants() {
    }

    /**
     * The Constant serverResource.
     */
    private static final ResourceBundle serverResource = ResourceBundle.getBundle("redis");

    /**
     * The Constant REDIS_SERVER_PASSWORD.
     */
    public static final String REDIS_SERVER_PASSWORD = serverResource.getString("redis.server.password");

    /**
     * The Constant REDIS_SERVER_ADDRESS.
     */
    public static final String REDIS_SERVER_ADDRESS = serverResource.getString("redis.server.address");

    /**
     * The Constant REDIS_SERVER_PORT.
     */
    public static final int REDIS_SERVER_PORT = Integer.parseInt(serverResource.getString("redis.server.port"));
}