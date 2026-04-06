package org.example.designpattern.behavioral.command;

/**
 * 命令接口
 * <p>
 * 所有具体命令都实现这个接口。
 * 关键：同时定义 execute() 和 undo()，支持撤销。
 */
public interface Command {

    /** 执行命令 */
    void execute();

    /** 撤销命令（恢复到执行前的状态） */
    void undo();

    /** 命令描述（用于日志） */
    String description();
}
