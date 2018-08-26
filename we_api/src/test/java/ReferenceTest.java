import org.junit.Test;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLOutput;

/**
 * @Auther: biao.feng
 * @Date: 2018/7/30 22:13
 * @Description:
 */
public class ReferenceTest {

    @Test
    public void testBigDecimal() {
       /* BigDecimal bigDecimal = BigDecimal.valueOf(1.01);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(1.000);
        System.out.println(bigDecimal.scale());
        System.out.println(bigDecimal1.scale());
        System.out.println(bigDecimal.equals(bigDecimal1));
        System.out.println(bigDecimal.compareTo(bigDecimal1));
        System.out.println(bigDecimal1);
        System.out.println(BigDecimal.valueOf(1.01));
        System.out.println(new Double(0.1));
        System.out.println(0.1);
        System.out.println(0.1 * 3);
        System.out.println(0.5 * 3);*/
       BigDecimal bigDecimal = BigDecimal.ONE;
       testAdd(bigDecimal);
       System.out.println(bigDecimal);

    }

    public void testAdd(BigDecimal bigDecimal){
        bigDecimal = bigDecimal.add(BigDecimal.ONE);
        System.out.println(bigDecimal);

    }
}
