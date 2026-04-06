package org.example.designpattern.structural.decorator;

/**
 * 组件接口 —— 数据源
 * <p>
 * 定义读写数据的统一接口。
 * 具体组件和装饰器都实现这个接口，保证可以互相嵌套。
 */
public interface DataSource {

    /**
     * 写入数据
     */
    void write(String data);

    /**
     * 读取数据
     */
    String read();
}
