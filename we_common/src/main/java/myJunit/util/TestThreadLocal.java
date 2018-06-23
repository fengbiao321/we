package myJunit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/18 21:42
 * @Description:
 */
public class TestThreadLocal {

    //成员变量--共享，测试并发
    private static  Integer count = 0;
    private static Map<String, String> hashMap = new HashMap<>();
//    private static Random random = new Random();
    private static ThreadLocal<Map<String, String>> local = new ThreadLocal<>();
    static List<String>  list = new ArrayList<>();
    private static ThreadLocal<List<String>> local1 = new ThreadLocal<>();
//    private static ThreadLocal<Integer> local = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        TestThreadLocal testThreadLocal = new TestThreadLocal();
        Set<Integer> set = new ConcurrentSkipListSet<>();

        for (int i = 0; i< 10; i++) {
           new Thread(
                   () -> {
                       local1.set(list);
                       add();
//                       System.out.println(local.get());
                   }
           ).start();
        }

        Thread.sleep(1000);
        System.out.println(list.size());
        Set<String> set1 = new HashSet<>(list);
        System.out.println(set1.size());
    }

    public static void add (){

        List<String> list = TestThreadLocal.local1.get();
//        System.out.println(list == TestThreadLocal.list);

        for (int i=0;i<10000;i++) {
            list.add(i+"");
        }

    /* Map<String, String> temple = TestThreadLocal.local.get();
//        Integer integer = TestThreadLocal.local.get();

        for (int i=0 ;i<100; i++){
            temple.put(i+"", i+"");
        }
*/
        /*for (int i=0;i<1000;i++){
            local.get();
            count ++;
//            System.out.println( count ++);
        }*/
    }

}
