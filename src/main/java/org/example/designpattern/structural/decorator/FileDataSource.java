package org.example.designpattern.structural.decorator;

/**
 * 具体组件 —— 文件数据源
 * <p>
 * 最底层的实际操作者，真正负责存储和读取数据。
 * 装饰器会包裹这个组件，在它之上添加功能。
 */
public class FileDataSource implements DataSource {

    private final String filename;
    /** 模拟文件存储 */
    private String fileContent = "";

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(String data) {
        this.fileContent = data;
        System.out.printf("    [文件] 写入 %s（%d 字符）%n", filename, data.length());
    }

    @Override
    public String read() {
        System.out.printf("    [文件] 读取 %s（%d 字符）%n", filename, fileContent.length());
        return fileContent;
    }
}
