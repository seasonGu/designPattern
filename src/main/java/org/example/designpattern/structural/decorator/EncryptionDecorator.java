package org.example.designpattern.structural.decorator;

/**
 * 具体装饰器 —— 加密装饰器
 * <p>
 * 写入时加密，读取时解密。
 * 使用简单的字符偏移来模拟加密（实际项目用 AES 等）。
 */
public class EncryptionDecorator extends DataSourceDecorator {

    private static final int SHIFT = 3; // 凯撒加密偏移量

    public EncryptionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void write(String data) {
        System.out.println("    [加密] 加密数据...");
        String encrypted = encrypt(data);
        // 加密后，委托给下一层（被装饰对象）写入
        super.write(encrypted);
    }

    @Override
    public String read() {
        // 先从下一层读取加密数据
        String encrypted = super.read();
        System.out.println("    [加密] 解密数据...");
        return decrypt(encrypted);
    }

    /** 简单加密：每个字符 ASCII 值 +3 */
    private String encrypt(String data) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            sb.append((char) (c + SHIFT));
        }
        return sb.toString();
    }

    /** 解密：每个字符 ASCII 值 -3 */
    private String decrypt(String data) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            sb.append((char) (c - SHIFT));
        }
        return sb.toString();
    }
}
