package org.example.designpattern.creational.builder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 产品类 —— HTTP 请求（不可变对象）
 * <p>
 * 注意：
 * 1. 所有字段都是 final —— 创建后不可修改
 * 2. 没有 setter —— 只能通过 Builder 设置
 * 3. 构造函数是 private —— 只有 Builder 能创建
 */
public class HttpRequest {

    // ═══════════ 必填参数 ═══════════
    private final String url;

    // ═══════════ 可选参数（有默认值）═══════════
    private final String method;
    private final Map<String, String> headers;
    private final String body;
    private final int timeoutMs;
    private final int retryCount;
    private final boolean followRedirects;

    /**
     * 私有构造函数 —— 只有 Builder 能调用
     */
    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = Collections.unmodifiableMap(builder.headers);
        this.body = builder.body;
        this.timeoutMs = builder.timeoutMs;
        this.retryCount = builder.retryCount;
        this.followRedirects = builder.followRedirects;
    }

    // ═══════════ 只有 getter，没有 setter ═══════════

    public String getUrl() { return url; }
    public String getMethod() { return method; }
    public Map<String, String> getHeaders() { return headers; }
    public String getBody() { return body; }
    public int getTimeoutMs() { return timeoutMs; }
    public int getRetryCount() { return retryCount; }
    public boolean isFollowRedirects() { return followRedirects; }

    /**
     * 模拟发送请求
     */
    public void execute() {
        System.out.println("  发送 HTTP 请求:");
        System.out.printf("    %s %s%n", method, url);
        if (!headers.isEmpty()) {
            System.out.println("    请求头:");
            headers.forEach((k, v) -> System.out.printf("      %s: %s%n", k, v));
        }
        if (body != null) {
            System.out.printf("    请求体: %s%n", body);
        }
        System.out.printf("    超时: %dms | 重试: %d次 | 跟随重定向: %s%n",
                timeoutMs, retryCount, followRedirects ? "是" : "否");
        System.out.println("    → 请求发送成功 ✅");
    }

    @Override
    public String toString() {
        return String.format("HttpRequest{%s %s, headers=%d, timeout=%dms, retry=%d}",
                method, url, headers.size(), timeoutMs, retryCount);
    }

    // ══════════════════════════════════════════════════
    //  Builder —— 内部静态类（建造者模式的核心）
    // ══════════════════════════════════════════════════

    /**
     * 建造者
     * <p>
     * 设计要点：
     * 1. 必填参数通过 Builder 构造函数传入（编译期保证不为空）
     * 2. 可选参数通过链式方法设置（有默认值）
     * 3. 每个方法返回 this，支持链式调用
     * 4. build() 方法执行校验并创建不可变对象
     */
    public static class Builder {

        // 必填
        private final String url;

        // 可选（设置默认值）
        private String method = "GET";
        private final Map<String, String> headers = new LinkedHashMap<>();
        private String body = null;
        private int timeoutMs = 5000;
        private int retryCount = 0;
        private boolean followRedirects = true;

        /**
         * 必填参数通过构造函数传入
         */
        public Builder(String url) {
            this.url = url;
        }

        public Builder method(String method) {
            this.method = method;
            return this;  // 返回 this，支持链式调用
        }

        public Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeoutMs(int timeoutMs) {
            this.timeoutMs = timeoutMs;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder followRedirects(boolean followRedirects) {
            this.followRedirects = followRedirects;
            return this;
        }

        /**
         * 构建最终对象
         * <p>
         * 可以在这里做参数校验，确保对象合法。
         */
        public HttpRequest build() {
            // 校验
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL 不能为空");
            }
            if (body != null && "GET".equalsIgnoreCase(method)) {
                throw new IllegalArgumentException("GET 请求不能有请求体");
            }
            if (timeoutMs <= 0) {
                throw new IllegalArgumentException("超时时间必须大于0");
            }
            return new HttpRequest(this);
        }
    }
}
