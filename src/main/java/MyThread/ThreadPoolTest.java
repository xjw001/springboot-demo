package MyThread;

import java.util.concurrent.*;

/**
 * @author xiongjw
 * @version 1.0
 * @date 2020/7/2 17:25
 */
public class ThreadPoolTest {

    static ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {

    }
}
