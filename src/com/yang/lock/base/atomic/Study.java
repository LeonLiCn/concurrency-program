package com.yang.lock.base.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Study {


    private static AtomicReference<User> a = new AtomicReference<>();
    static AtomicIntegerFieldUpdater<Student> au = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");

    public static void main(String[] args) {

        User u = new User("aaa", 98);
        User r = new User("bbb", 100);

        a.set(u);
        System.out.println(a.get().getName());
        System.out.println(a.get().getAge());
        a.compareAndSet(u, r);
        System.out.println(a.get().getName());
        System.out.println(a.get().getAge());

        Student s = new Student("ccc", 11);
        Student t = new Student("ddd", 21);

        System.out.println(au.getAndIncrement(s));
        System.out.println(au.incrementAndGet(t));
        System.out.println(au.get(s));
        System.out.println(au.get(t));

    }


    static class User{

        private String name;

        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }



}
