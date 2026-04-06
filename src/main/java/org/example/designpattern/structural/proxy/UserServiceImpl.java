package org.example.designpattern.structural.proxy;

/**
 * 真实对象 —— 用户服务实现
 * <p>
 * 纯粹的业务逻辑，不包含任何权限、日志、缓存等横切关注点。
 */
public class UserServiceImpl implements UserService {

    @Override
    public String findUser(String userId) {
        // 模拟数据库查询
        System.out.printf("    [真实服务] 查询数据库... userId=%s%n", userId);
        simulateDelay(100);
        return "User{id=" + userId + ", name='张三', age=28}";
    }

    @Override
    public void deleteUser(String userId) {
        System.out.printf("    [真实服务] 从数据库删除用户... userId=%s%n", userId);
        simulateDelay(50);
        System.out.println("    [真实服务] 删除成功");
    }

    @Override
    public void updateUser(String userId, String newName) {
        System.out.printf("    [真实服务] 更新用户信息... userId=%s, newName=%s%n", userId, newName);
        simulateDelay(80);
        System.out.println("    [真实服务] 更新成功");
    }

    private void simulateDelay(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
