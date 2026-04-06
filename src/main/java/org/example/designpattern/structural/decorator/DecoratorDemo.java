package org.example.designpattern.structural.decorator;

/**
 * 装饰器模式 —— 演示
 */
public class DecoratorDemo {

    public static void main(String[] args) {

        String testData = "Hello Design Pattern!";

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 无装饰 —— 直接写入文件");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  原始数据: " + testData);
        System.out.println();

        DataSource fileOnly = new FileDataSource("data.txt");
        fileOnly.write(testData);
        String result1 = fileOnly.read();
        System.out.println("  读取结果: " + result1);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 单层装饰 —— 加密后写入");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  原始数据: " + testData);
        System.out.println("  写入流程: 数据 → [加密] → [文件]");
        System.out.println();

        // 加密装饰器 包裹 文件数据源
        DataSource encrypted = new EncryptionDecorator(
                new FileDataSource("encrypted.txt")
        );
        encrypted.write(testData);
        System.out.println();
        System.out.println("  --- 读取（自动解密）---");
        String result2 = encrypted.read();
        System.out.println("  读取结果: " + result2);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 双层装饰 —— 先加密再 Base64 编码");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  原始数据: " + testData);
        System.out.println("  写入流程: 数据 → [Base64] → [加密] → [文件]");
        System.out.println("  读取流程: [文件] → [加密解密] → [Base64解码] → 数据");
        System.out.println();

        // 套娃：Base64 包裹 加密 包裹 文件
        DataSource doubleWrapped = new Base64Decorator(
                new EncryptionDecorator(
                        new FileDataSource("double.txt")
                )
        );
        doubleWrapped.write(testData);
        System.out.println();
        System.out.println("  --- 读取（自动逆向还原）---");
        String result3 = doubleWrapped.read();
        System.out.println("  读取结果: " + result3);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 自由组合 —— 这就是装饰器的威力");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                // 只加密
                new EncryptionDecorator(new FileDataSource("a.txt"))

                // 只压缩
                new CompressionDecorator(new FileDataSource("b.txt"))

                // 只 Base64
                new Base64Decorator(new FileDataSource("c.txt"))

                // 先加密再 Base64
                new Base64Decorator(new EncryptionDecorator(new FileDataSource("d.txt")))

                // 先压缩再加密再 Base64 —— 三层套娃！
                new Base64Decorator(new EncryptionDecorator(new CompressionDecorator(new FileDataSource("e.txt"))))
                """);

        System.out.println("如果用继承实现这些组合，需要多少个子类？");
        System.out.println("  3个功能的所有组合 = 7个子类！（A,B,C,AB,AC,BC,ABC）");
        System.out.println("  用装饰器只需要 3个装饰器类，自由组合即可 ✅");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 对比 Java I/O 流 —— 装饰器模式的经典应用");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                // Java I/O 就是装饰器模式！
                new BufferedReader(                  // 装饰器：缓冲
                    new InputStreamReader(           // 装饰器：字节→字符转换
                        new FileInputStream("x.txt") // 具体组件：文件读取
                    )
                )

                // 对应关系：
                // InputStream          = DataSource（组件接口）
                // FileInputStream      = FileDataSource（具体组件）
                // FilterInputStream    = DataSourceDecorator（装饰器基类）
                // BufferedInputStream  = 具体装饰器
                // DataInputStream      = 具体装饰器
                """);
    }
}
