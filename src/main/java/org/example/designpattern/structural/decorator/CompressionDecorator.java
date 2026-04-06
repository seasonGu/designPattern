package org.example.designpattern.structural.decorator;

/**
 * 具体装饰器 —— 压缩装饰器
 * <p>
 * 写入时压缩，读取时解压。
 * 使用简单的 Run-Length Encoding (RLE) 来模拟压缩。
 * 例如: "aaabbc" → "a3b2c1"
 */
public class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void write(String data) {
        System.out.printf("    [压缩] 压缩前: %d 字符%n", data.length());
        String compressed = compress(data);
        System.out.printf("    [压缩] 压缩后: %d 字符%n", compressed.length());
        super.write(compressed);
    }

    @Override
    public String read() {
        String compressed = super.read();
        System.out.println("    [压缩] 解压数据...");
        return decompress(compressed);
    }

    /** 简单 RLE 压缩 */
    private String compress(String data) {
        if (data.isEmpty()) return data;
        StringBuilder sb = new StringBuilder();
        char current = data.charAt(0);
        int count = 1;
        for (int i = 1; i < data.length(); i++) {
            if (data.charAt(i) == current) {
                count++;
            } else {
                sb.append(current).append(count);
                current = data.charAt(i);
                count = 1;
            }
        }
        sb.append(current).append(count);
        return sb.toString();
    }

    /** 简单 RLE 解压 */
    private String decompress(String data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i += 2) {
            char c = data.charAt(i);
            int count = Character.getNumericValue(data.charAt(i + 1));
            sb.append(String.valueOf(c).repeat(count));
        }
        return sb.toString();
    }
}
