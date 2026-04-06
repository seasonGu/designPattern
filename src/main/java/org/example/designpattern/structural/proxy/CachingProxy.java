package org.example.designpattern.structural.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态代理③ —— 缓存代理
 * <p>
 * 缓存查询结果，相同参数的二次查询直接返回缓存，不再调用真实服务。
 * 写操作（delete/update）时清除缓存。
 * 类似 Spring 的 @Cacheable + @CacheEvict。
 */
public class CachingProxy implements UserService {

    private final UserService target;
    private final Map<String, String> cache = new HashMap<>();

    public CachingProxy(UserService target) {
        this.target = target;
    }

    @Override
    public String findUser(String userId) {
        // 先查缓存
        if (cache.containsKey(userId)) {
            System.out.printf("  [缓存代理] 命中缓存！key=%s ✅%n", userId);
            return cache.get(userId);
        }
        // 缓存未命中，调用真实服务
        System.out.printf("  [缓存代理] 缓存未命中，查询真实服务... key=%s%n", userId);
        String result = target.findUser(userId);

        // 存入缓存
        cache.put(userId, result);
        System.out.printf("  [缓存代理] 结果已缓存，key=%s%n", userId);
        return result;
    }

    @Override
    public void deleteUser(String userId) {
        target.deleteUser(userId);
        // 删除后清除缓存
        cache.remove(userId);
        System.out.printf("  [缓存代理] 清除缓存，key=%s%n", userId);
    }

    @Override
    public void updateUser(String userId, String newName) {
        target.updateUser(userId, newName);
        // 更新后清除缓存（下次查询会拿到最新数据）
        cache.remove(userId);
        System.out.printf("  [缓存代理] 清除缓存，key=%s%n", userId);
    }
}
