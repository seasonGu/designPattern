package org.example.designpattern.behavioral.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 备忘录 —— 角色状态快照
 * <p>
 * 用 record 实现，天然不可变。
 * 保存角色在某一时刻的完整状态。
 * <p>
 * 注意：Caretaker（GameSaveManager）只保管这个对象，
 * 不应该读取或修改它的内容（虽然 Java 中无法完全限制，
 * 但通过约定来保证封装性）。
 */
public record CharacterMemento(
        String name,
        int hp,
        int mp,
        int level,
        int x,
        int y,
        LocalDateTime saveTime
) {
    /** 获取存档摘要信息（供显示用） */
    public String getSummary() {
        return String.format("Lv.%d %s HP:%d MP:%d 位置:(%d,%d) [%s]",
                level, name, hp, mp, x, y,
                saveTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
