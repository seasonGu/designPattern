package org.example.designpattern.behavioral.command;

/**
 * 接收者（Receiver）—— 文本文档
 * <p>
 * 真正执行操作的对象。命令对象会调用它的方法来完成实际工作。
 */
public class TextDocument {

    private StringBuilder content = new StringBuilder();

    public void insert(int position, String text) {
        content.insert(position, text);
    }

    public void delete(int position, int length) {
        content.delete(position, position + length);
    }

    public String getContent() {
        return content.toString();
    }

    public int length() {
        return content.length();
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
