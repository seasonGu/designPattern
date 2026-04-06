package org.example.designpattern.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 调用者（Invoker）—— 文本编辑器
 * <p>
 * 负责执行命令、管理撤销栈和重做栈。
 * 编辑器不关心具体是什么命令，只管调用 execute() 和 undo()。
 */
public class TextEditor {

    private final TextDocument document = new TextDocument();
    /** 撤销栈：记录已执行的命令 */
    private final Deque<Command> undoStack = new ArrayDeque<>();
    /** 重做栈：记录被撤销的命令 */
    private final Deque<Command> redoStack = new ArrayDeque<>();

    /**
     * 执行命令
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        // 执行新命令后，清空重做栈（历史分支被丢弃）
        redoStack.clear();
        System.out.printf("  执行: %-30s │ 文档: \"%s\"%n", command.description(), document);
    }

    /**
     * 撤销（Ctrl+Z）
     */
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("  撤销: 没有可撤销的操作");
            return;
        }
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        System.out.printf("  撤销: %-30s │ 文档: \"%s\"%n", command.description(), document);
    }

    /**
     * 重做（Ctrl+Y）
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("  重做: 没有可重做的操作");
            return;
        }
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        System.out.printf("  重做: %-30s │ 文档: \"%s\"%n", command.description(), document);
    }

    public TextDocument getDocument() {
        return document;
    }

    /**
     * 打印操作历史
     */
    public void printHistory() {
        System.out.println("  撤销栈 (" + undoStack.size() + "): ");
        int i = 1;
        for (Command cmd : undoStack) {
            System.out.printf("    %d. %s%n", i++, cmd.description());
        }
        System.out.println("  重做栈 (" + redoStack.size() + "): ");
        i = 1;
        for (Command cmd : redoStack) {
            System.out.printf("    %d. %s%n", i++, cmd.description());
        }
    }
}
