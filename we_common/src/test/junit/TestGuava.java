package junit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: biao.feng
 * @Date: 2018/8/22 21:39
 * @Description: guava测试类
 */
public class TestGuava {


    static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(1,TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            System.out.println("统一刷新任务");
            Thread.sleep(10000);
            return "aaaaa";
        }
    });

    static Cache<String, String> cache1= CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            return null;
        }
    });


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        cache.put("1","1");
        cache.put("2","2");
        cache.put("3","3");
        cache.put("4","4");
        cache.put("5","5");


        new Thread(() -> {
            try {
                System.out.println("进来了");
                System.out.println(cache.get("a"));
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(cache.get("9"));
        System.out.println(cache.get("9"));

    }

}
