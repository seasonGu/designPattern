package org.example.designpattern.behavioral.command;

/**
 * 具体命令 —— 插入文本
 * <p>
 * 命令对象封装了：做什么（insert）、对谁做（document）、参数（position, text）
 * 以及如何撤销（delete 插入的内容）。
 */
public class InsertCommand implements Command {

    private final TextDocument document;
    private final int position;
    private final String text;

    public InsertCommand(TextDocument document, int position, String text) {
        this.document = document;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() {
        document.insert(position, text);
    }

    @Override
    public void undo() {
        // 撤销插入 = 删除插入的内容
        document.delete(position, text.length());
    }

    @Override
    public String description() {
        return String.format("插入 \"%s\" 在位置 %d", text, position);
    }
}
