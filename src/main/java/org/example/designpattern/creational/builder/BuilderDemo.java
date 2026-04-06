package org.example.designpattern.creational.builder;

/**
 * 建造者模式 —— 演示
 */
public class BuilderDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  0.【反面教材】构造函数爆炸问题");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                // ❌ 构造函数参数太多，根本看不懂每个参数是什么意思
                new HttpRequest("https://api.example.com",
                    "POST", "application/json", null, "{\\"key\\":\\"value\\"}",
                    5000, 3, true, null, null);
                //        ↑ 这些参数分别代表什么？天知道...

                // ❌ 用 setter，对象创建后还能被修改，不安全
                HttpRequest req = new HttpRequest();
                req.setUrl("...");
                req.setMethod("POST");
                // 忘了设 timeout 怎么办？对象处于半成品状态
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 最简请求 —— 只设必填参数，其他用默认值");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        HttpRequest simpleGet = new HttpRequest.Builder("https://api.example.com/users")
                .build();
        simpleGet.execute();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 完整 POST 请求 —— 链式调用设置多个参数");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        HttpRequest postRequest = new HttpRequest.Builder("https://api.example.com/orders")
                .method("POST")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token_abc123")
                .header("X-Request-Id", "req-2024-001")
                .body("{\"item\":\"iPhone\",\"quantity\":1,\"price\":9999}")
                .timeoutMs(10000)
                .retryCount(3)
                .build();
        postRequest.execute();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 自定义配置 —— 只设置需要的参数");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        HttpRequest customGet = new HttpRequest.Builder("https://cdn.example.com/image.png")
                .timeoutMs(30000)          // 大文件，超时设长一点
                .retryCount(5)             // 多重试几次
                .followRedirects(false)    // 不跟随重定向
                .build();
        customGet.execute();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 参数校验 —— build() 时检查参数合法性");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        try {
            new HttpRequest.Builder("https://api.example.com/data")
                    .method("GET")
                    .body("{\"key\":\"value\"}")  // GET 请求不应该有 body
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("  校验拦截: " + e.getMessage() + " ✅");
        }

        System.out.println();
        try {
            new HttpRequest.Builder("")  // 空 URL
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("  校验拦截: " + e.getMessage() + " ✅");
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 不可变性 —— 创建后无法修改");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                HttpRequest req = new HttpRequest.Builder("...")
                        .method("POST")
                        .build();

                req.setMethod("GET");   // ❌ 编译错误！没有 setter
                req.getHeaders().put("hack", "value"); // ❌ 运行时异常！headers 不可修改

                // 想要不同配置？创建一个新的 Builder 就好
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  6. 对比：Builder 模式在 Java 生态中无处不在");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                // Lombok —— 一个注解搞定
                @Builder
                public class User {
                    private String name;
                    private int age;
                }
                User user = User.builder().name("张三").age(28).build();

                // JDK 自带
                StringBuilder sb = new StringBuilder().append("hello").append(" ").append("world");
                HttpClient client = HttpClient.newBuilder().connectTimeout(...).build();

                // Spring
                MockMvcRequestBuilders.get("/api").header("Auth", "token").content("...")
                """);
    }
}
