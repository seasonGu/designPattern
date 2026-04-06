package org.example.designpattern.behavioral.command;

/**
 * 具体命令 —— 删除文本
 * <p>
 * 为了支持撤销，需要在删除前保存被删除的内容。
 */
public class DeleteCommand implements Command {

    private final TextDocument document;
    private final int position;
    private final int length;
    /** 保存被删除的内容，撤销时用 */
    private String deletedText;

    public DeleteCommand(TextDocument document, int position, int length) {
        this.document = document;
        this.position = position;
        this.length = length;
    }

    @Override
    public void execute() {
        // 先备份要删除的内容
        deletedText = document.getContent().substring(position, position + length);
        document.delete(position, length);
    }

    @Override
    public void undo() {
        // 撤销删除 = 在原位置插入被删除的内容
        document.insert(position, deletedText);
    }

    @Override
    public String description() {
        return String.format("删除位置 %d 起的 %d 个字符", position, length);
    }
}
