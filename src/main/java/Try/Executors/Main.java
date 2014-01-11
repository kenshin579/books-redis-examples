package Try.Executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        RedisPoolManager.getInstance().connect();
        RedisActionManager.getInstance().cleanAllActions();

        CallableMethod callable = new CallableMethod();
        Future<Boolean> future = service.submit(callable);

        try {
            System.out.println("result: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
