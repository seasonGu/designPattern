package org.example.designpattern.behavioral.mediator;

/**
 * 中介者接口 —— 聊天室
 * <p>
 * 定义中介者的通信协议：
 * - 注册用户
 * - 发送群消息
 * - 发送私聊消息
 */
public interface ChatMediator {

    /** 注册用户到聊天室 */
    void addUser(User user);

    /** 移除用户 */
    void removeUser(User user);

    /** 发送群消息（转发给除发送者外的所有人） */
    void sendMessage(String message, User sender);

    /** 发送私聊消息（只转发给指定用户） */
    void sendPrivateMessage(String message, User sender, String receiverName);

    /** 发送公告（转发给所有人，包括发送者） */
    void sendAnnouncement(String message, User sender);
}
