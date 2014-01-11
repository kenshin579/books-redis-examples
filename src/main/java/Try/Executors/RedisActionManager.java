/*
 * 
 */
package Try.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

final public class RedisActionManager {

    private static final Logger log = LoggerFactory.getLogger(RedisActionManager.class);

    /**
     * The Constant instance.
     */
    private static final RedisActionManager instance = new RedisActionManager();

    /**
     * Instantiates a new redis action manager.
     */
    private RedisActionManager() {
    }

    /**
     * Gets the single instance of RedisActionManager.
     *
     * @return single instance of RedisActionManager
     */
    public static RedisActionManager getInstance() {
        return instance;
    }

    /**
     * Clean all actions.
     */
    public void cleanAllActions() {
        new RedisContext<Object>().execute(new RedisCommand<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.flushAll();  // 임시로 flush, tomcat restart시 action cleanup 어떻게 처리할지 고려필요
                return null;
            }
        });
    }

    /**
     * Initialize edit action index to zero
     *
     * @param documentId documentId
     */
    public void initEditActionIndex(final String documentId) {
        new RedisContext<Object>().execute(new RedisCommand<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.set(getActionIndexStr(documentId), "0");
                return null;
            }
        });
    }

    /**
     * setnx edit action index to zero
     *
     * @param documentId the document id
     */
    public void setnxEditActionIndex(final String documentId) {
        new RedisContext<Object>().execute(new RedisCommand<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.setnx(getActionIndexStr(documentId), "0");
                return null;
            }
        });
    }

    /**
     * Delete all related document actions
     *
     * @param documentId the document id
     */
    public void deleteAllDocumentActions(final String documentId) {
        new RedisContext<Object>().execute(new RedisCommand<Object>() {
            @Override
            public Object execute(Jedis jedis) {

//                Set<String> keys = jedis.keys("docId:" + documentId + ":*");
                Set<String> keys = jedis.keys(RedisKey.DOCID_ALL_KEYS.formated(documentId));
                String[] queryResult = keys.toArray(new String[keys.size()]);
                if (queryResult.length > 0)
                    jedis.del(queryResult);
                return null;
            }
        });
//        return new RedisContext<Boolean>().execute(new RedisCommand<Boolean>() {
//            @Override
//            public Boolean execute(Jedis jedis) {
//                long returnValue = jedis.del(RedisKey.DOCID_ACTIONLIST.formated(documentId));
//                return returnValue > 0;
//            }
//        });
    }

    /**
     * Get ActionIndex String
     *
     * @param documentId the document id
     * @return actionIndexStr
     */
    private String getActionIndexStr(String documentId) {
//        return "docId:" + documentId + ":actionIdx:index";
        return RedisKey.DOCID_ACTIONINDEX.formated(documentId);
    }

    /**
     * Save edit command action.
     *
     * @param documentId the document id
     * @param actionCmd  the action cmd
     * @return true, if successful
     */
    public boolean saveEditCommandAction(final String documentId, final String actionCmd) {
//        new RedisContext<List<Object>>().execute(new RedisCommand<List<Object>>() {
//            @Override
//            public List<Object> execute(Jedis jedis) {
//                setnxEditActionIndex(documentId);
//
//                long actionIdx = Long.parseLong(jedis.get(getActionIndexStr(documentId))) + 1;
//
//                Transaction trans = jedis.multi();
//                trans.incr(getActionIndexStr(documentId));
//
////                String keyStr = "docId:" + documentId + ":actionIdx:" + actionIdx;
//                String keyStr = RedisKey.DOCID_ACTIONIDX.formated(documentId, String.valueOf(actionIdx));
//
//                trans.set(keyStr, actionCmd);
//                return trans.exec();
//
//            }
//        });
//        return true;
        return new RedisContext<Boolean>().execute(new RedisCommand<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
//                jedis.set("hello", "world");
//                jedis.rpush("key:rlog", "test");
//                jedis.lpush("key:log", actionCmd);

                log.info("key: {}, action: {}", RedisKey.DOCID_ACTIONLIST.formated(documentId), actionCmd);
                long returnValue = jedis.rpush(RedisKey.DOCID_ACTIONLIST.formated(documentId), actionCmd);


                return returnValue > 0;
//                return true;
            }
        });

    }

    /**
     * Retrieve edit command action.
     *
     * @param documentId the document id
     * @param commandSeq the command seq
     * @return the list
     */
    public List<String> retrieveEditCommandAction(final String documentId, final long commandSeq) {
//        return new RedisContext<List<String>>().execute(new RedisCommand<List<String>>() {
//            @Override
//            public List<String> execute(Jedis jedis) {
//                List<String> result = new ArrayList<String>();
//
//                Long maxIndex = Long.parseLong(jedis.get(getActionIndexStr(documentId)));
//                for (long actionIdx = commandSeq; actionIdx <= maxIndex; actionIdx++) {
////                    keyStr = "docId:" + documentId + ":actionIdx:" + actionIdx;
//                    result.add(jedis.get(RedisKey.DOCID_ACTIONIDX.formated(documentId, String.valueOf(actionIdx))));
//                }
//
//                return result;
//            }
//        });
        return new RedisContext<List<String>>().execute(new RedisCommand<List<String>>() {
            @Override
            public List<String> execute(Jedis jedis) {
                return jedis.lrange(RedisKey.DOCID_ACTIONLIST.formated(documentId), commandSeq, -1);
            }
        });
    }

    /**
     * The Interface RedisCommand.
     *
     * @param <T> the generic type
     */
    private interface RedisCommand<T> {

        /**
         * Execute.
         *
         * @param jedis the jedis
         * @return the t
         */
        T execute(Jedis jedis);
    }

    /**
     * The Class RedisContext.
     *
     * @param <T> the generic type
     */
    private static final class RedisContext<T> {

        /**
         * Execute.
         *
         * @param command the command
         * @return the t
         */
        public T execute(RedisCommand<T> command) {
            final Jedis jedis = RedisPoolManager.getInstance().getJedis();
            try {
                return command.execute(jedis);
            } finally {
                RedisPoolManager.getInstance().returnJedis(jedis);
            }
        }
    }
}
