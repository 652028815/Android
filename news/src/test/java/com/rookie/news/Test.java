package com.rookie.news;

import java.lang.reflect.Field;

/**
 * Author: FK
 * Date：  2018/5/29.
 */
public class Test {
    @org.junit.Test
    public void test(){
        Child child = new Child();
        child.set();
    }
    class Father {
        private String a = "123";

        public String getA() {
            return a;
        }
    }

    class Child extends Father {
        public void set() {
            try {
                Field[] declaredFields = getClass().getSuperclass().getDeclaredFields();
                for(Field f:declaredFields){
                    System.out.println(f.getName());
                }
                Field field = getClass().getSuperclass().getDeclaredField("a");
                field.setAccessible(true);
                field.set(this, "b");
                System.out.println(getA());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
