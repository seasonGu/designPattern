package org.example.designpattern.behavioral.mediator;

/**
 * 抽象同事类 —— 用户
 * <p>
 * 每个用户只持有中介者的引用，不直接引用其他用户。
 * 发送消息通过中介者转发，接收消息由中介者调用 receive()。
 */
public abstract class User {

    /** 用户名 */
    protected final String name;

    /** 持有中介者引用 —— 用户只认识中介者，不认识其他用户 */
    protected ChatMediator mediator;

    public User(String name) {
        this.name = name;
    }

    /** 加入聊天室（绑定中介者） */
    public void joinRoom(ChatMediator mediator) {
        this.mediator = mediator;
        mediator.addUser(this);
    }

    /** 离开聊天室 */
    public void leaveRoom() {
        if (mediator != null) {
            mediator.removeUser(this);
            this.mediator = null;
        }
    }

    /** 发送群消息 —— 委托给中介者 */
    public abstract void send(String message);

    /** 发送私聊 —— 委托给中介者 */
    public abstract void sendPrivate(String message, String receiverName);

    /** 接收消息 —— 由中介者回调 */
    public abstract void receive(String message, String from);

    public String getName() {
        return name;
    }
}
