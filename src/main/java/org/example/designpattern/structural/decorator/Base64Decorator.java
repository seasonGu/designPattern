package org.example.designpattern.structural.decorator;

import java.util.Base64;

/**
 * 具体装饰器 —— Base64 编码装饰器
 * <p>
 * 写入时编码为 Base64，读取时解码。
 */
public class Base64Decorator extends DataSourceDecorator {

    public Base64Decorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void write(String data) {
        System.out.println("    [Base64] 编码数据...");
        String encoded = Base64.getEncoder().encodeToString(data.getBytes());
        super.write(encoded);
    }

    @Override
    public String read() {
        String encoded = super.read();
        System.out.println("    [Base64] 解码数据...");
        return new String(Base64.getDecoder().decode(encoded));
    }
}
