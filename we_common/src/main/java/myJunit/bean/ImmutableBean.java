package myJunit.bean;

import myJunit.util.DateUtils;

import java.text.DateFormat;
import java.util.Date;

/**
 * @Auther: biao.feng
 * @Date: 2018/8/13 22:00
 * @Description: 测试不可变对象
 */
public final class ImmutableBean {

    private final String name;

    private final Integer age;

    private final Date birthday;

    public ImmutableBean(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = new Date(birthday.getTime());
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Date getBirthday() {
        return (Date) birthday.clone();
    }

    @Override
    public String toString() {
        return String.format("ImmutableBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + DateUtils.formatDate(birthday, "yyyy-MM-dd") +
                '}');
    }
}
