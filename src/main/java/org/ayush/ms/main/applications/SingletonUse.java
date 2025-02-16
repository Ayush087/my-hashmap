package org.ayush.ms.main.applications;

import org.ayush.ms.SingletonLazy;

public class SingletonUse {
    public static void main(String[] args) {
        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
        System.out.print(singletonLazy1.hashCode() + "\n" + singletonLazy2.hashCode());
    }
}