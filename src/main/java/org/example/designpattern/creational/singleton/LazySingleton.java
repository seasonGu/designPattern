package org.example.designpattern.creational.singleton;

/**
 * 写法二：懒汉式单例 (Lazy Initialization) —— 线程不安全版本
 *
 * 原理：第一次调用 getInstance() 时才创建实例。
 *
 * 优点：支持懒加载，按需创建
 * 缺点：多线程环境下不安全！
 *       两个线程同时判断 instance == null 都为 true，会创建两个实例。
 *
 * ⚠️ 这是一个反面教材，实际项目中不要使用！
 *
 * 线程不安全场景示意：
 *   线程A: if (instance == null)  → true
 *   线程B: if (instance == null)  → true（A还没来得及创建）
 *   线程A: instance = new LazySingleton()  → 创建实例1
 *   线程B: instance = new LazySingleton()  → 创建实例2 ← 破坏了单例！
 */
public class LazySingleton {

    // 1. 声明但不立即初始化
    private static LazySingleton instance;

    // 2. 私有构造函数
    private LazySingleton() {
        System.out.println("[懒汉式] 实例被创建");
    }

    // 3. 第一次调用时才创建（线程不安全！）
    public static LazySingleton getInstance() {
        if (instance == null) {           // 多线程时，多个线程可能同时通过此判断
            instance = new LazySingleton(); // 导致创建多个实例
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("[懒汉式] 执行业务方法, hashCode=" + this.hashCode());
    }
}
