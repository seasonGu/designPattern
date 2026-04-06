package org.example.designpattern.structural.decorator;

/**
 * 装饰器基类
 * <p>
 * 关键设计：
 * 1. 实现 DataSource 接口（和被装饰对象是同一类型）
 * 2. 持有一个 DataSource 引用（被装饰对象）
 * 3. 默认实现是直接委托给被装饰对象
 * <p>
 * 子类只需要覆盖想要增强的方法，在调用 super 前后添加自己的逻辑。
 */
public abstract class DataSourceDecorator implements DataSource {

    /** 被装饰的对象（可以是具体组件，也可以是另一个装饰器）*/
    protected final DataSource wrappee;

    public DataSourceDecorator(DataSource wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void write(String data) {
        wrappee.write(data);
    }

    @Override
    public String read() {
        return wrappee.read();
    }
}
