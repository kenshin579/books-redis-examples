package Others.youngjin;

import java.util.concurrent.Callable;

/**
 * Callable 인터페이스는 '반환값이 있는 처리의 호출'을 추상화한 인터페이스임
 * - call() 메서드는 RealData 객체를 반환하도록 되어 있음.
 */
public class Host {
    public FutureData request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");

        // (1) FutureData의 인스턴스를 만든다.
        //      (생성자에게 Callable<RealData>를 건넨다)
        FutureData future = new FutureData(
                new Callable<RealData>() {
                    public RealData call() {
                        return new RealData(count, c);
                    }
                }
        );

        // (2) RealData를 만들기 위한 새로운 쓰레드를 기동한다.
        /*
         * Q: Thread의 run()은 어디에 있나?
         * A: FutureTask.run() 메서드를 호출하는 쓰레드가 call() 메서드도 호출함
         * - 위에 있는 call() 메서드가 실행되고 RealData를 생성해서 반환해준다.
         */
        new Thread(future).start();

        System.out.println("    request(" + count + ", " + c + ") END");

        // (3) FutureData를 만들기 위한 새로운 쓰레드를 기동함
        return future;
    }
}
