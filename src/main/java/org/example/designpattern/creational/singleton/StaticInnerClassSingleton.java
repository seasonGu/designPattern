package org.example.designpattern.creational.singleton;

/**
 * 写法四：静态内部类单例 (Static Inner Class / Holder Pattern)
 *
 * 原理：利用 JVM 类加载机制 —— 内部类在被使用时才加载。
 *       外部类加载时，内部类不会被加载，实现了懒加载。
 *       内部类加载时，JVM 保证线程安全。
 *
 * 加载流程：
 *   1. StaticInnerClassSingleton 被加载 → Holder 不会被加载（没人用它）
 *   2. 调用 getInstance() → 触发 Holder 类加载 → INSTANCE 被初始化
 *   3. JVM 类加载是线程安全的 → 天然的线程安全
 *
 * 优点：懒加载 + 线程安全 + 无锁高性能 + 代码优雅
 * 缺点：无法防御反射攻击
 *
 * ★★★★ 推荐使用！兼具懒加载和线程安全，是最优雅的写法之一。
 */
public class StaticInnerClassSingleton {

    // 1. 私有构造函数
    private StaticInnerClassSingleton() {
        System.out.println("[静态内部类] 实例被创建");
    }

    // 2. 静态内部类 —— 持有外部类的唯一实例
    //    只有在 getInstance() 被调用时，Holder 类才会被 JVM 加载
    private static class Holder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    // 3. 全局访问点 —— 触发内部类加载
    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void doSomething() {
        System.out.println("[静态内部类] 执行业务方法, hashCode=" + this.hashCode());
    }
}
