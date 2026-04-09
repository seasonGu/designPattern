package org.example.designpattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者 —— 聊天室
 * <p>
 * 核心职责：
 * 1. 维护所有用户的列表
 * 2. 协调用户之间的消息转发
 * 3. 用户之间完全解耦，所有交互通过聊天室进行
 * <p>
 * 类比：
 * - 没有聊天室：每个人要有其他所有人的联系方式（网状）
 * - 有聊天室：每个人只需加入聊天室（星状）
 */
public class ChatRoom implements ChatMediator {

    /** 聊天室名称 */
    private final String roomName;

    /** 所有在线用户 —— 只有中介者知道全部用户 */
    private final List<User> users = new ArrayList<>();

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.printf("    📢 [%s] %s 加入了聊天室（当前 %d 人在线）%n",
                roomName, user.getName(), users.size());
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        System.out.printf("    📢 [%s] %s 离开了聊天室（当前 %d 人在线）%n",
                roomName, user.getName(), users.size());
    }

    /**
     * 群消息 —— 转发给除发送者外的所有人
     * <p>
     * 这就是中介者的核心：发送者不需要知道有哪些接收者，
     * 中介者负责遍历并转发。
     */
    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // 不转发给发送者自己
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }

    /**
     * 私聊 —— 只转发给指定用户
     */
    @Override
    public void sendPrivateMessage(String message, User sender, String receiverName) {
        for (User user : users) {
            if (user.getName().equals(receiverName)) {
                user.receive("[私聊] " + message, sender.getName());
                return;
            }
        }
        // 找不到接收者
        System.out.printf("    ⚠️ 用户 [%s] 不在线%n", receiverName);
    }

    /**
     * 公告 —— 转发给所有人（包括发送者）
     */
    @Override
    public void sendAnnouncement(String message, User sender) {
        for (User user : users) {
            user.receive("📣 公告: " + message, sender.getName());
        }
    }

    public int getOnlineCount() {
        return users.size();
    }
}
