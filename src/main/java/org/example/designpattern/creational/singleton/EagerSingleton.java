package org.example.designpattern.creational.singleton;

/**
 * 写法一：饿汉式单例 (Eager Initialization)
 *
 * 原理：利用 JVM 类加载机制保证线程安全。
 *       类被加载时就立即创建实例，不管你用不用。
 *
 * 优点：实现简单，线程安全（JVM 类加载本身是线程安全的）
 * 缺点：不支持懒加载，如果实例很重但从未使用，会浪费内存
 *
 * 适用：实例创建开销小、程序启动后一定会用到的场景
 */
public class EagerSingleton {

    // 1. 类加载时就创建唯一实例（static final 保证只初始化一次）
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // 2. 私有构造函数 —— 防止外部 new
    private EagerSingleton() {
        System.out.println("[饿汉式] 实例被创建");
    }

    // 3. 全局访问点
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void doSomething() {
        System.out.println("[饿汉式] 执行业务方法, hashCode=" + this.hashCode());
    }
}
