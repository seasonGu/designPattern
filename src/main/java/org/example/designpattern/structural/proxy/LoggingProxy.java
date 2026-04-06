package org.example.designpattern.structural.proxy;

/**
 * 静态代理② —— 日志代理
 * <p>
 * 在方法调用前后记录日志，包括方法名、参数、耗时、结果。
 * 类似 Spring AOP 中的 @Around 通知。
 */
public class LoggingProxy implements UserService {

    private final UserService target;

    public LoggingProxy(UserService target) {
        this.target = target;
    }

    @Override
    public String findUser(String userId) {
        System.out.printf("  [日志代理] >>> 调用 findUser(\"%s\")%n", userId);
        long start = System.currentTimeMillis();

        String result = target.findUser(userId);

        long cost = System.currentTimeMillis() - start;
        System.out.printf("  [日志代理] <<< findUser 返回: %s (耗时: %dms)%n", result, cost);
        return result;
    }

    @Override
    public void deleteUser(String userId) {
        System.out.printf("  [日志代理] >>> 调用 deleteUser(\"%s\")%n", userId);
        long start = System.currentTimeMillis();

        target.deleteUser(userId);

        long cost = System.currentTimeMillis() - start;
        System.out.printf("  [日志代理] <<< deleteUser 完成 (耗时: %dms)%n", cost);
    }

    @Override
    public void updateUser(String userId, String newName) {
        System.out.printf("  [日志代理] >>> 调用 updateUser(\"%s\", \"%s\")%n", userId, newName);
        long start = System.currentTimeMillis();

        target.updateUser(userId, newName);

        long cost = System.currentTimeMillis() - start;
        System.out.printf("  [日志代理] <<< updateUser 完成 (耗时: %dms)%n", cost);
    }
}
