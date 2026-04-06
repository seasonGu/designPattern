package org.example.designpattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK 动态代理 —— 运行时自动生成代理类
 * <p>
 * 静态代理的问题：每个方法都要手动写代理逻辑，方法多了很啰嗦。
 * 动态代理：用反射在运行时自动生成代理，所有方法统一拦截。
 * <p>
 * 这就是 Spring AOP 的底层原理！
 */
public class DynamicProxyFactory {

    /**
     * 创建一个带日志功能的动态代理
     *
     * @param target 被代理的真实对象
     * @return 代理对象（和真实对象实现相同接口）
     */
    @SuppressWarnings("unchecked")
    public static <T> T createLoggingProxy(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new LoggingHandler(target)
        );
    }

    /**
     * InvocationHandler —— 所有方法调用都会经过这里
     * <p>
     * 相当于一个统一的拦截器，无论接口有多少个方法，
     * 都只需要在 invoke() 里写一次逻辑。
     */
    private static class LoggingHandler implements InvocationHandler {

        private final Object target;

        LoggingHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            String argsStr = args != null ? Arrays.toString(args) : "()";

            System.out.printf("  [动态代理] >>> %s%s%n", methodName, argsStr);
            long start = System.currentTimeMillis();

            // 调用真实对象的方法
            Object result = method.invoke(target, args);

            long cost = System.currentTimeMillis() - start;
            System.out.printf("  [动态代理] <<< %s 返回: %s (耗时: %dms)%n",
                    methodName, result, cost);

            return result;
        }
    }
}
