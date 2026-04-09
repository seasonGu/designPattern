package org.example.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器节点 —— 文件夹
 * <p>
 * 文件夹可以包含文件和子文件夹（children 类型是 FileSystemNode）。
 * getSize() 递归计算所有子节点的大小之和。
 * print() 递归打印整个子树。
 *
 * 这就是组合模式的核心：容器和叶子实现同一接口，
 * 容器的操作通过递归委托给子节点来完成。
 */
public class Directory implements FileSystemNode {

    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    /** 添加子节点（文件或文件夹都行，因为类型是 FileSystemNode） */
    public Directory add(FileSystemNode node) {
        children.add(node);
        return this;  // 支持链式调用
    }

    /** 移除子节点 */
    public void remove(FileSystemNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 递归计算大小 —— 组合模式的精髓
     * <p>
     * 对文件夹调 getSize()，它会递归调用所有子节点的 getSize()。
     * 子节点如果是文件 → 返回文件大小
     * 子节点如果是文件夹 → 继续递归
     * 客户端完全不用管这些，调用方式一模一样。
     */
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemNode child : children) {
            totalSize += child.getSize();  // 递归！
        }
        return totalSize;
    }

    /** 递归打印树形结构 */
    @Override
    public void print(String indent) {
        System.out.printf("%s📁 %s/ (%s, %d项)%n",
                indent, name, formatSize(getSize()), children.size());
        for (FileSystemNode child : children) {
            child.print(indent + "  ");  // 递归，增加缩进
        }
    }

    /** 获取子节点数量（递归，包含所有层级） */
    public int countAll() {
        int count = 0;
        for (FileSystemNode child : children) {
            count++;  // 当前子节点
            if (child instanceof Directory dir) {
                count += dir.countAll();  // 递归统计子文件夹
            }
        }
        return count;
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + "B";
        if (bytes < 1024 * 1024) return String.format("%.1fKB", bytes / 1024.0);
        return String.format("%.1fMB", bytes / (1024.0 * 1024));
    }
}
