package org.example.designpattern.structural.proxy;

/**
 * 静态代理① —— 权限控制代理（保护代理）
 * <p>
 * 在调用真实服务之前，先检查当前用户是否有权限。
 * 没有权限则直接拦截，不会到达真实服务。
 */
public class AccessControlProxy implements UserService {

    private final UserService target;
    private final String currentUserRole;

    public AccessControlProxy(UserService target, String currentUserRole) {
        this.target = target;
        this.currentUserRole = currentUserRole;
    }

    @Override
    public String findUser(String userId) {
        // 查询不需要特殊权限
        System.out.println("  [权限代理] 查询操作，放行");
        return target.findUser(userId);
    }

    @Override
    public void deleteUser(String userId) {
        // 删除需要 admin 权限
        System.out.printf("  [权限代理] 删除操作，校验权限... 当前角色: %s%n", currentUserRole);
        if (!"admin".equals(currentUserRole)) {
            System.out.println("  [权限代理] ❌ 权限不足！只有 admin 才能删除用户");
            throw new SecurityException("权限不足：需要 admin 角色");
        }
        System.out.println("  [权限代理] ✅ 权限校验通过");
        target.deleteUser(userId);
    }

    @Override
    public void updateUser(String userId, String newName) {
        System.out.printf("  [权限代理] 更新操作，校验权限... 当前角色: %s%n", currentUserRole);
        if (!"admin".equals(currentUserRole) && !"editor".equals(currentUserRole)) {
            System.out.println("  [权限代理] ❌ 权限不足！需要 admin 或 editor 角色");
            throw new SecurityException("权限不足：需要 admin 或 editor 角色");
        }
        System.out.println("  [权限代理] ✅ 权限校验通过");
        target.updateUser(userId, newName);
    }
}
