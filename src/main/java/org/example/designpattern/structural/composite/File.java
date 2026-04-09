package org.example.designpattern.structural.composite;

/**
 * 叶子节点 —— 文件
 * <p>
 * 文件是树的末端，不能包含子节点。
 * getSize() 直接返回自身大小。
 */
public class File implements FileSystemNode {

    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void print(String indent) {
        System.out.printf("%s📄 %s (%s)%n", indent, name, formatSize(size));
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + "B";
        if (bytes < 1024 * 1024) return String.format("%.1fKB", bytes / 1024.0);
        return String.format("%.1fMB", bytes / (1024.0 * 1024));
    }
}
