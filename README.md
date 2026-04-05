# Design Pattern - 设计模式学习项目

基于 **Spring Boot 4.0.5 + Java 17** 的 GoF 23种设计模式学习项目，每个模式都包含详细注释和可运行的示例代码。

## 项目结构

```
src/main/java/org/example/designpattern/
├── creational/                 # 创建型模式
│   ├── singleton/              # 单例模式
│   └── factorymethod/          # 工厂方法模式
└── behavioral/                 # 行为型模式
    ├── strategy/               # 策略模式
    └── observer/               # 观察者模式
```

## 学习路线

### 第一阶段（入门）

| # | 模式 | 场景 | 状态 |
|---|------|------|------|
| 1 | **单例模式 Singleton** | 5种实现方式对比 + 并发安全测试 | ✅ |
| 2 | **工厂方法 Factory Method** | 消息通知系统（邮件/短信/微信） | ✅ |
| 3 | **策略模式 Strategy** | 电商支付系统（支付宝/微信/信用卡） | ✅ |
| 4 | **观察者模式 Observer** | 订单状态变更通知 | ✅ |
| 5 | 模板方法 Template Method | | |
| 6 | 装饰器模式 Decorator | | |

### 第二阶段（进阶）

| # | 模式 | 场景 | 状态 |
|---|------|------|------|
| 7 | 抽象工厂 Abstract Factory | | |
| 8 | 建造者模式 Builder | | |
| 9 | 适配器模式 Adapter | | |
| 10 | 代理模式 Proxy | | |
| 11 | 命令模式 Command | | |
| 12 | 责任链 Chain of Responsibility | | |
| 13 | 外观模式 Facade | | |

### 第三阶段（高级）

| # | 模式 | 场景 | 状态 |
|---|------|------|------|
| 14 | 原型模式 Prototype | | |
| 15 | 桥接模式 Bridge | | |
| 16 | 组合模式 Composite | | |
| 17 | 享元模式 Flyweight | | |
| 18 | 状态模式 State | | |
| 19 | 中介者模式 Mediator | | |
| 20 | 备忘录模式 Memento | | |
| 21 | 迭代器模式 Iterator | | |
| 22 | 访问者模式 Visitor | | |
| 23 | 解释器模式 Interpreter | | |

## 运行方式

```bash
# 运行指定模式的 Demo
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.singleton.SingletonDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.factorymethod.FactoryMethodDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.strategy.StrategyDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.observer.ObserverDemo"
```

## 技术栈

- Java 17
- Spring Boot 4.0.5
- Maven
- Lombok
