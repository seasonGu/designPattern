# Design Pattern - 设计模式学习项目

基于 **Spring Boot 4.0.5 + Java 17** 的 GoF 23种设计模式学习项目，每个模式都包含详细注释和可运行的示例代码。

## 项目结构

```
src/main/java/org/example/designpattern/
├── creational/                          # 创建型模式
│   ├── singleton/                       # 单例模式（5种实现）
│   ├── factorymethod/                   # 工厂方法模式
│   ├── abstractfactory/                 # 抽象工厂模式
│   └── builder/                         # 建造者模式
├── structural/                          # 结构型模式
│   ├── decorator/                       # 装饰器模式
│   ├── adapter/                         # 适配器模式
│   ├── proxy/                           # 代理模式（静态代理 + JDK动态代理）
│   └── facade/                          # 外观模式
└── behavioral/                          # 行为型模式
    ├── strategy/                        # 策略模式
    ├── observer/                        # 观察者模式
    ├── templatemethod/                  # 模板方法模式
    ├── command/                         # 命令模式（支持Undo/Redo）
    └── chain/                           # 责任链模式
```

## 学习路线

### 第一阶段（入门）

| # | 模式 | 场景 | 状态 |
|---|------|------|------|
| 1 | **单例模式 Singleton** | 5种实现方式对比 + 并发安全测试 | ✅ |
| 2 | **工厂方法 Factory Method** | 消息通知系统（邮件/短信/微信） | ✅ |
| 3 | **策略模式 Strategy** | 电商支付系统（支付宝/微信/信用卡） | ✅ |
| 4 | **观察者模式 Observer** | 订单状态变更通知 | ✅ |
| 5 | **模板方法 Template Method** | 数据导出系统（CSV/Excel/PDF） | ✅ |
| 6 | **装饰器模式 Decorator** | 数据流处理（加密/压缩/Base64） | ✅ |

### 第二阶段（进阶）

| # | 模式 | 场景 | 状态 |
|---|------|------|------|
| 7 | **抽象工厂 Abstract Factory** | UI主题系统（暗色/亮色整套组件） | ✅ |
| 8 | **建造者模式 Builder** | HTTP请求构建（链式调用+不可变对象） | ✅ |
| 9 | **适配器模式 Adapter** | 第三方支付SDK对接（支付宝/微信/PayPal） | ✅ |
| 10 | **代理模式 Proxy** | 服务访问控制（权限/日志/缓存+动态代理） | ✅ |
| 11 | **命令模式 Command** | 文本编辑器（插入/删除/替换+Undo/Redo） | ✅ |
| 12 | **责任链 Chain of Responsibility** | 请假审批流程（组长→经理→总监→CEO） | ✅ |
| 13 | **外观模式 Facade** | 电商下单（库存+支付+物流+通知） | ✅ |

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
# 创建型模式
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.singleton.SingletonDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.factorymethod.FactoryMethodDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.abstractfactory.AbstractFactoryDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.creational.builder.BuilderDemo"

# 结构型模式
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.structural.decorator.DecoratorDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.structural.adapter.AdapterDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.structural.proxy.ProxyDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.structural.facade.FacadeDemo"

# 行为型模式
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.strategy.StrategyDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.observer.ObserverDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.templatemethod.TemplateMethodDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.command.CommandDemo"
./mvnw compile exec:java -Dexec.mainClass="org.example.designpattern.behavioral.chain.ChainDemo"
```

## 模式速查表

### 创建型模式 — 关注对象的创建

| 模式 | 一句话总结 | 关键词 |
|------|-----------|--------|
| 单例 | 确保一个类只有一个实例 | 唯一实例 |
| 工厂方法 | 子类决定创建哪个对象 | 一个工厂→一个产品 |
| 抽象工厂 | 创建一整族相关产品 | 一个工厂→一族产品 |
| 建造者 | 分步构建复杂对象 | 链式调用、不可变 |

### 结构型模式 — 关注类和对象的组合

| 模式 | 一句话总结 | 关键词 |
|------|-----------|--------|
| 装饰器 | 层层包裹，动态增强功能 | 套娃、Java I/O |
| 适配器 | 转换不兼容的接口 | 转接头、第三方SDK |
| 代理 | 控制对对象的访问 | 权限、日志、Spring AOP |
| 外观 | 简化复杂子系统的使用 | 一个方法搞定、Service层 |

### 行为型模式 — 关注对象间的通信

| 模式 | 一句话总结 | 关键词 |
|------|-----------|--------|
| 策略 | 算法可互换，消灭if-else | 支付方式、排序算法 |
| 观察者 | 状态变化自动通知所有监听者 | 事件驱动、Spring Event |
| 模板方法 | 流程固定，细节可变 | final骨架、钩子方法 |
| 命令 | 将动作封装为对象 | Undo/Redo、任务队列 |
| 责任链 | 沿链传递，直到有人处理 | 审批流、Filter链 |

## 技术栈

- Java 17
- Spring Boot 4.0.5
- Maven（清华镜像源）
- Lombok
