package org.example.designpattern.creational.singleton;

/**
 * 写法三：双重检查锁单例 (Double-Checked Locking, DCL)
 *
 * 原理：结合懒加载 + synchronized + volatile，既线程安全又高性能。
 *
 * 为什么需要两次检查？
 *   第一次检查：避免不必要的同步（大多数情况 instance 已存在，直接返回）
 *   第二次检查：防止多个线程同时通过第一次检查后重复创建
 *
 * 为什么需要 volatile？
 *   new 操作不是原子的，分为三步：
 *     ① 分配内存空间
 *     ② 初始化对象
 *     ③ 将引用指向内存地址
 *   JVM 可能重排序为 ①→③→②，导致其他线程拿到未初始化完成的对象。
 *   volatile 禁止指令重排序，确保安全。
 *
 * 优点：懒加载 + 线程安全 + 高性能（只在首次创建时同步）
 * 缺点：代码稍复杂，需要理解 volatile 语义
 */
public class DoubleCheckedLockingSingleton {

    // 1. volatile 关键字：禁止指令重排序，保证可见性
    private static volatile DoubleCheckedLockingSingleton instance;

    // 2. 私有构造函数
    private DoubleCheckedLockingSingleton() {
        System.out.println("[双重检查锁] 实例被创建");
    }

    // 3. 双重检查
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {                          // 第一次检查（无锁，快速路径）
            synchronized (DoubleCheckedLockingSingleton.class) {  // 加锁
                if (instance == null) {                  // 第二次检查（防止重复创建）
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("[双重检查锁] 执行业务方法, hashCode=" + this.hashCode());
    }
}
