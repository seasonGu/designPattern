package org.example.designpattern.structural.composite;

/**
 * 组合模式 —— 演示
 */
public class CompositeDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 构建文件系统树");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 构建一个项目的文件结构
        Directory root = new Directory("my-project");

        // src 目录
        Directory src = new Directory("src");
        Directory main = new Directory("main");
        Directory java = new Directory("java");
        java.add(new File("Application.java", 2048));
        java.add(new File("UserService.java", 4096));
        java.add(new File("OrderService.java", 5120));

        Directory resources = new Directory("resources");
        resources.add(new File("application.yml", 512));
        resources.add(new File("logback.xml", 1024));

        main.add(java);
        main.add(resources);
        src.add(main);

        // test 目录
        Directory test = new Directory("test");
        test.add(new File("ApplicationTest.java", 1536));
        test.add(new File("UserServiceTest.java", 3072));

        // 根目录
        root.add(src);
        root.add(test);
        root.add(new File("pom.xml", 2560));
        root.add(new File("README.md", 1280));
        root.add(new File(".gitignore", 256));

        // 打印整个文件树
        root.print("");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 统一操作 —— 文件和文件夹调用方式完全一样");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 对单个文件调用 getSize()
        FileSystemNode singleFile = new File("test.txt", 1024);
        System.out.printf("  单个文件大小: %s → %d 字节%n",
                singleFile.getName(), singleFile.getSize());

        // 对整个目录调用 getSize() —— 自动递归！调用方式一模一样！
        System.out.printf("  java 目录大小: %s → %d 字节（递归计算）%n",
                java.getName(), java.getSize());
        System.out.printf("  src 目录大小:  %s → %d 字节（递归计算）%n",
                src.getName(), src.getSize());
        System.out.printf("  根目录总大小:  %s → %d 字节（递归计算）%n",
                root.getName(), root.getSize());

        System.out.println();
        System.out.printf("  根目录总文件数: %d%n", root.countAll());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 动态增删节点");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  添加 docker 配置:");
        Directory docker = new Directory("docker");
        docker.add(new File("Dockerfile", 512));
        docker.add(new File("docker-compose.yml", 1024));
        root.add(docker);

        System.out.printf("  新的根目录总大小: %d 字节%n", root.getSize());
        System.out.println();
        root.print("");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                组合模式的关键：「部分」和「整体」具有一致性

                            FileSystemNode（统一接口）
                               /          \\
                           File           Directory
                          (叶子)           (容器)
                        getSize()        getSize() {
                        → 返回自身大小       for (child : children)
                                              total += child.getSize(); ← 递归！
                                          }

                客户端代码：
                  node.getSize()   // 不管是文件还是文件夹，调用方式完全一样
                  node.print("")   // 如果是文件夹，自动递归打印整棵子树

                Java/Spring 中的组合模式：
                  java.awt.Component → Container 包含 Component
                  Spring CompositePropertySource
                  MyBatis SqlNode（IfSqlNode, MixedSqlNode...）
                """);
    }
}
