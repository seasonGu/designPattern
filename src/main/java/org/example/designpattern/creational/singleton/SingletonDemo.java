package org.example.designpattern.creational.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 单例模式演示类
 *
 * 运行此类可以看到：
 * 1. 每种单例写法的基本用法
 * 2. 多线程环境下，懒汉式如何出问题
 * 3. 其他写法如何保证线程安全
 */
public class SingletonDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("====================================");
        System.out.println("  单例模式 (Singleton Pattern) 演示");
        System.out.println("====================================\n");

        // ========== 1. 饿汉式 ==========
        System.out.println("--- 1. 饿汉式 (Eager) ---");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        eager1.doSomething();
        System.out.println("两次获取是否同一实例: " + (eager1 == eager2));
        System.out.println();

        // ========== 2. 懒汉式（演示线程不安全） ==========
        System.out.println("--- 2. 懒汉式 (Lazy) —— 多线程测试 ---");
        testThreadSafety("懒汉式", LazySingleton::getInstance);
        System.out.println();

        // ========== 3. 双重检查锁 ==========
        System.out.println("--- 3. 双重检查锁 (DCL) ---");
        testThreadSafety("双重检查锁", DoubleCheckedLockingSingleton::getInstance);
        System.out.println();

        // ========== 4. 静态内部类 ==========
        System.out.println("--- 4. 静态内部类 (Holder) ---");
        testThreadSafety("静态内部类", StaticInnerClassSingleton::getInstance);
        System.out.println();

        // ========== 5. 枚举 ==========
        System.out.println("--- 5. 枚举 (Enum) ---");
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        enum1.doSomething();
        enum2.doSomething();
        System.out.println("两次获取是否同一实例: " + (enum1 == enum2));
        System.out.println();

        // ========== 总结 ==========
        System.out.println("====================================");
        System.out.println("  总结");
        System.out.println("====================================");
        System.out.println("• 简单场景 → 饿汉式 或 枚举");
        System.out.println("• 需要懒加载 → 静态内部类（推荐）或 双重检查锁");
        System.out.println("• 需要防反射/序列化 → 枚举（最安全）");
        System.out.println("• 懒汉式（无锁）→ 永远不要在生产环境使用！");
    }

    /**
     * 多线程安全性测试
     * 启动100个线程同时获取单例，检查是否所有线程拿到的都是同一个实例
     */
    private static <T> void testThreadSafety(String name,
                                              java.util.function.Supplier<T> supplier)
            throws InterruptedException {
        int threadCount = 100;
        CountDownLatch readyLatch = new CountDownLatch(threadCount); // 让所有线程就绪
        CountDownLatch startLatch = new CountDownLatch(1);           // 统一发令起跑
        CopyOnWriteArraySet<T> instances = new CopyOnWriteArraySet<>();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                readyLatch.countDown();   // 标记自己已就绪
                try {
                    startLatch.await();   // 等待统一信号
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                instances.add(supplier.get()); // 获取实例
            }).start();
        }

        readyLatch.await();   // 等所有线程就绪
        startLatch.countDown(); // 发令：所有线程同时获取实例

        Thread.sleep(500);    // 等待所有线程执行完毕

        System.out.println("[" + name + "] " + threadCount + "个线程并发获取，共产生 "
                + instances.size() + " 个不同实例");
        if (instances.size() == 1) {
            System.out.println("[" + name + "] ✅ 线程安全！所有线程获取到同一实例");
        } else {
            System.out.println("[" + name + "] ❌ 线程不安全！产生了多个实例");
        }
    }
}
