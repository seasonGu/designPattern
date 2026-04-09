package org.example.designpattern.structural.composite;

/**
 * 组件接口 —— 文件系统节点
 * <p>
 * 叶子（File）和容器（Directory）都实现这个接口。
 * 客户端统一通过这个接口操作，不需要区分是文件还是文件夹。
 */
public interface FileSystemNode {

    /** 获取名称 */
    String getName();

    /** 获取大小（字节） */
    long getSize();

    /**
     * 打印树形结构
     *
     * @param indent 当前缩进
     */
    void print(String indent);
}
