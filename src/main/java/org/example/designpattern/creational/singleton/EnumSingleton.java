package org.example.designpattern.creational.singleton;

/**
 * 写法五：枚举单例 (Enum Singleton)
 *
 * 原理：Java 枚举本身就是单例的，JVM 从语言层面保证：
 *       1. 线程安全 —— 枚举实例在类加载时创建
 *       2. 防反射 —— JVM 禁止通过反射创建枚举实例（会抛异常）
 *       3. 防序列化 —— 枚举的序列化由 JVM 特殊处理，反序列化时返回同一实例
 *
 * 出自《Effective Java》第3条：
 *   "单元素的枚举类型是实现单例的最佳方法"  —— Joshua Bloch
 *
 * 优点：最简洁、最安全（防反射 + 防序列化），无任何漏洞
 * 缺点：不支持懒加载、不能继承其他类（枚举隐式继承 Enum）
 *
 * ★★★★★ 如果不需要懒加载和继承，这是最推荐的写法！
 */
public enum EnumSingleton {

    // 唯一实例
    INSTANCE;

    // 可以定义成员变量
    private int count = 0;

    public void doSomething() {
        count++;
        System.out.println("[枚举单例] 执行业务方法, 调用次数=" + count + ", hashCode=" + this.hashCode());
    }
}
