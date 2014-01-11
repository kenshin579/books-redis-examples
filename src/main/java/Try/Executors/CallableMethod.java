package Try.Executors;

import java.util.concurrent.Callable;

public class CallableMethod implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        long documentId = 501;
        String actionCmd = "{cmd:\"insert\", p:[0,0], o:[1,1], value:\"hello world\"}";

        return RedisActionManager.getInstance().saveEditCommandAction(String.valueOf(documentId), actionCmd);

    }
}
