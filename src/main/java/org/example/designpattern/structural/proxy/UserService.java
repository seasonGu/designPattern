package org.example.designpattern.structural.proxy;

/**
 * 服务接口 —— 用户服务
 * <p>
 * 代理和真实对象都实现这个接口，客户端面向接口编程。
 */
public interface UserService {

    String findUser(String userId);

    void deleteUser(String userId);

    void updateUser(String userId, String newName);
}
