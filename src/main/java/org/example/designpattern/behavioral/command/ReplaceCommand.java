package org.example.designpattern.behavioral.command;

/**
 * 具体命令 —— 替换文本
 * <p>
 * 组合了删除和插入两个操作，但作为一个原子命令来撤销。
 */
public class ReplaceCommand implements Command {

    private final TextDocument document;
    private final String oldText;
    private final String newText;
    /** 记录替换发生的位置，撤销时使用 */
    private int replacePosition = -1;

    public ReplaceCommand(TextDocument document, String oldText, String newText) {
        this.document = document;
        this.oldText = oldText;
        this.newText = newText;
    }

    @Override
    public void execute() {
        String content = document.getContent();
        replacePosition = content.indexOf(oldText);
        if (replacePosition >= 0) {
            document.delete(replacePosition, oldText.length());
            document.insert(replacePosition, newText);
        }
    }

    @Override
    public void undo() {
        if (replacePosition >= 0) {
            document.delete(replacePosition, newText.length());
            document.insert(replacePosition, oldText);
        }
    }

    @Override
    public String description() {
        return String.format("替换 \"%s\" 为 \"%s\"", oldText, newText);
    }
}
