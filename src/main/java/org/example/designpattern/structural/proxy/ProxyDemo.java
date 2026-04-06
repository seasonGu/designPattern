package org.example.designpattern.structural.proxy;

/**
 * 代理模式 —— 演示
 */
public class ProxyDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 无代理 —— 直接调用真实服务");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        UserService realService = new UserServiceImpl();
        realService.findUser("U001");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 权限代理 —— 拦截无权限的操作");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 普通用户尝试操作
        UserService viewerProxy = new AccessControlProxy(new UserServiceImpl(), "viewer");
        System.out.println("--- viewer 查询（允许）---");
        viewerProxy.findUser("U001");

        System.out.println();
        System.out.println("--- viewer 删除（拒绝）---");
        try {
            viewerProxy.deleteUser("U001");
        } catch (SecurityException e) {
            System.out.println("  捕获异常: " + e.getMessage());
        }

        System.out.println();
        // admin 用户操作
        UserService adminProxy = new AccessControlProxy(new UserServiceImpl(), "admin");
        System.out.println("--- admin 删除（允许）---");
        adminProxy.deleteUser("U001");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 日志代理 —— 记录调用信息和耗时");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        UserService loggingProxy = new LoggingProxy(new UserServiceImpl());
        loggingProxy.findUser("U002");
        System.out.println();
        loggingProxy.updateUser("U002", "李四");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 缓存代理 —— 同样的查询只查一次数据库");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        UserService cachingProxy = new CachingProxy(new UserServiceImpl());
        System.out.println("--- 第一次查询（走数据库）---");
        String result1 = cachingProxy.findUser("U003");
        System.out.println("  结果: " + result1);

        System.out.println();
        System.out.println("--- 第二次查询（走缓存，不再查数据库）---");
        String result2 = cachingProxy.findUser("U003");
        System.out.println("  结果: " + result2);

        System.out.println();
        System.out.println("--- 更新后再查询（缓存被清除，重新查数据库）---");
        cachingProxy.updateUser("U003", "王五");
        String result3 = cachingProxy.findUser("U003");
        System.out.println("  结果: " + result3);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 代理嵌套 —— 像装饰器一样叠加多种功能");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 日志 → 权限 → 缓存 → 真实服务（从外到内）
        UserService fullProxy = new LoggingProxy(
                new AccessControlProxy(
                        new CachingProxy(
                                new UserServiceImpl()
                        ), "admin"
                )
        );
        fullProxy.findUser("U004");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  6. JDK 动态代理 —— 运行时自动生成（Spring AOP 原理）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 一行代码给任意接口添加日志功能！
        UserService dynamicProxy = DynamicProxyFactory.createLoggingProxy(new UserServiceImpl());
        dynamicProxy.findUser("U005");
        System.out.println();
        dynamicProxy.updateUser("U005", "赵六");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  7. 静态代理 vs 动态代理");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                ┌──────────┬──────────────────────────┬────────────────────────────┐
                │          │ 静态代理                  │ JDK 动态代理                │
                ├──────────┼──────────────────────────┼────────────────────────────┤
                │ 创建时机  │ 编译期（手动写代理类）     │ 运行时（反射自动生成）       │
                │ 代码量    │ 每个方法都要写代理逻辑     │ invoke() 里统一处理所有方法  │
                │ 限制      │ 无                       │ 被代理对象必须实现接口       │
                │ Spring    │ 不常用                   │ 基于接口的 AOP 用此方式     │
                └──────────┴──────────────────────────┴────────────────────────────┘

                Spring AOP 的选择策略：
                  有接口 → JDK 动态代理
                  无接口 → CGLIB 代理（通过继承生成子类）
                """);
    }
}
