package myJunit.bean;

import myJunit.annotation.myTestAnnotation;

public class UserAnnotation {

    @myTestAnnotation(name="hfdjshfksjdhfkjsd")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
