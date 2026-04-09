package org.example.designpattern.behavioral.mediator;

/**
 * 中介者模式 —— 演示
 */
public class MediatorDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用中介者，代码会怎样？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                不用中介者的聊天：
                class User {
                    List<User> contacts;  // 每个人维护一份联系人列表
                    void send(String msg) {
                        for (User u : contacts) {
                            u.receive(msg);       // 直接调用其他用户
                        }
                    }
                }
                // 4个人互相通信 = 4×3 = 12 条单向引用
                // 10个人 = 90 条引用！
                // 新增一个人？要修改所有已有用户的联系人列表！
                // 加一个"消息过滤"功能？每个 User 都要改！

                用中介者后：
                class User {
                    ChatMediator mediator;  // 只认识中介者
                    void send(String msg) {
                        mediator.sendMessage(msg, this);  // 委托给中介者
                    }
                }
                // 不管多少人，每人只有 1 条引用（指向中介者）
                // 新增功能只改中介者，用户类无需修改
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 创建聊天室，用户加入");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 创建中介者（聊天室）
        ChatRoom chatRoom = new ChatRoom("技术交流群");

        // 创建用户 —— 注意：用户之间完全不知道彼此的存在！
        NormalUser alice = new NormalUser("Alice");
        NormalUser bob = new NormalUser("Bob");
        NormalUser charlie = new NormalUser("Charlie");
        AdminUser admin = new AdminUser("Admin");

        // 用户加入聊天室（绑定中介者）
        alice.joinRoom(chatRoom);
        bob.joinRoom(chatRoom);
        charlie.joinRoom(chatRoom);
        admin.joinRoom(chatRoom);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 群消息 —— 发给除自己外的所有人");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        alice.send("大家好，我是 Alice！");
        System.out.println();

        bob.send("Hi Alice，欢迎！我是 Bob");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 私聊 —— 只有目标用户收到");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        alice.sendPrivate("Bob，明天的会议几点？", "Bob");
        System.out.println();

        bob.sendPrivate("下午两点", "Alice");
        System.out.println();

        // 私聊不存在的用户
        charlie.sendPrivate("你好", "David");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 管理员公告 —— 所有人都收到（包括自己）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        admin.sendAnnouncement("系统将于今晚 22:00 进行维护，请注意保存工作");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 用户离开和加入");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        charlie.leaveRoom();
        System.out.println();

        // Charlie 离开后，消息不会再发给他
        alice.send("Charlie 走了吗？");
        System.out.println();

        // 新用户加入
        NormalUser david = new NormalUser("David");
        david.joinRoom(chatRoom);
        System.out.println();

        david.send("大家好，我是新来的 David！");
        System.out.println();

        System.out.printf("  当前在线人数: %d%n", chatRoom.getOnlineCount());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  6. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                中介者模式的关键：网状 → 星状

                不用中介者（网状）：         用中介者（星状）：
                  A ←──→ B                    A ──┐
                  ↕ ╲  ╱ ↕                    B ──┼── ChatRoom
                  D ←──→ C                    C ──┤
                  6条双向关系                  D ──┘
                                              4条关系

                中介者内部：
                ┌──────────────────────────────────────┐
                │ ChatRoom（中介者）                     │
                │                                      │
                │  List<User> users; // 知道所有参与者   │
                │                                      │
                │  sendMessage(msg, sender) {          │
                │    for (user : users)                │
                │      if (user != sender)             │
                │        user.receive(msg); // 转发     │
                │  }                                   │
                │                                      │
                │  可以在这里加：                        │
                │    消息过滤、敏感词检测、消息记录...     │
                │    只改中介者，用户类不用动！           │
                └──────────────────────────────────────┘

                Java/Spring 中的中介者模式：
                  MVC 中的 Controller —— 协调 Model 和 View
                  Spring ApplicationEvent —— EventBus 就是中介者
                  消息队列 (RabbitMQ, Kafka) —— 生产者消费者通过 MQ 解耦
                  java.util.Timer —— 协调多个 TimerTask
                """);
    }
}
