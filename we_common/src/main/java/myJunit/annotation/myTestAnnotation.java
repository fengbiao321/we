package myJunit.annotation;

import java.lang.annotation.*;

/**
 * @Auther: biao.feng
 * @Date: 2018/6/10 22:08
 * @Description: 自定义标签，使用待定 TODO
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface myTestAnnotation {
    String name() default "";
}
