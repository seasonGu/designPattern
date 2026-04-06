package org.example.designpattern.behavioral.command;

/**
 * 命令模式 —— 演示
 */
public class CommandDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  1. 基本操作 —— 插入、删除、替换");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println();

        TextEditor editor = new TextEditor();
        TextDocument doc = editor.getDocument();

        editor.executeCommand(new InsertCommand(doc, 0, "Hello"));
        editor.executeCommand(new InsertCommand(doc, 5, " World"));
        editor.executeCommand(new InsertCommand(doc, 11, "!"));
        editor.executeCommand(new ReplaceCommand(doc, "World", "Java"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  2. 撤销（Undo）—— Ctrl+Z");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println();

        editor.undo();  // 撤销替换 → "Hello World!"
        editor.undo();  // 撤销插入! → "Hello World"
        editor.undo();  // 撤销插入World → "Hello"

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  3. 重做（Redo）—— Ctrl+Y");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println();

        editor.redo();  // 重做插入World → "Hello World"
        editor.redo();  // 重做插入! → "Hello World!"

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  4. 执行新命令后，重做栈被清空");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  当前重做栈:");
        editor.printHistory();

        System.out.println();
        System.out.println("  执行新操作（重做栈会被清空）:");
        editor.executeCommand(new ReplaceCommand(doc, "World", "Design Pattern"));
        editor.redo();  // 重做栈已空

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  5. 完整编辑会话 —— 模拟写代码");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println();

        TextEditor codeEditor = new TextEditor();
        TextDocument code = codeEditor.getDocument();

        codeEditor.executeCommand(new InsertCommand(code, 0, "public class Main {"));
        codeEditor.executeCommand(new InsertCommand(code, code.length(), "\n    // TODO\n"));
        codeEditor.executeCommand(new InsertCommand(code, code.length(), "}"));
        codeEditor.executeCommand(new ReplaceCommand(code, "// TODO", "System.out.println(\"Hi\");"));

        System.out.println();
        System.out.println("  操作历史:");
        codeEditor.printHistory();

        System.out.println();
        System.out.println("  连续撤销两步:");
        codeEditor.undo();
        codeEditor.undo();

        System.out.println();
        System.out.println("  再重做一步:");
        codeEditor.redo();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  6. 核心理解");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("""

                命令模式的关键：把「动作」变成「对象」

                ┌─────────────┐  executeCommand(cmd)  ┌─────────────┐
                │ TextEditor  │─────────────────────►│  Command     │
                │ (调用者)     │                       │  + execute() │
                │             │  cmd 存入 undoStack   │  + undo()    │
                │ undoStack   │◄──────────────────── │  + description│
                │ redoStack   │                       └──────┬───────┘
                └─────────────┘                              │
                                                      ┌──────┴──────┐
                                                      │TextDocument │
                                                      │ (接收者)     │
                                                      └─────────────┘

                为什么能撤销？
                  每个 Command 对象记住了「怎么做」和「怎么反做」
                  InsertCommand.undo() = delete
                  DeleteCommand.undo() = insert（先备份删掉的内容）
                  ReplaceCommand.undo() = 替换回原文本
                """);
    }
}
