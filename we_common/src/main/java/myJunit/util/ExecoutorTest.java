package myJunit.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: biao.feng
 * @Date: 2018/7/1 12:41
 * @Description:
 */
public class ExecoutorTest {

    static ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(), 30, 20L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1024));
  /*  static ExecutorService executorService = Executors.newCachedThreadPool();
    static ExecutorService executorService1 = Executors.newFixedThreadPool(1);*/

    public static void main(String args[]) throws ExecutionException, InterruptedException {

       Future<String> stringFuture = executorService.submit(new Callable<String>() {
           @Override
           public String call() throws Exception {
               Thread.sleep(1);
               return "abc";
           }
       });
//       executorService.shutdown();
       executorService.submit(() -> System.out.println("fsfs"));

        System.out.println(stringFuture.get());
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("aaaa");

    }

}
