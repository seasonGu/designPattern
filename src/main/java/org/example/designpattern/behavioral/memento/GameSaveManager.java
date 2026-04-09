package org.example.designpattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理者 (Caretaker) —— 存档管理器
 * <p>
 * 职责：
 *   1. 保管备忘录（存档列表）
 *   2. 提供存档和读档的入口
 * <p>
 * 重要：管理者不能查看或修改备忘录的内容！
 *       它只是个"保险箱"，负责存取，不偷看内容。
 */
public class GameSaveManager {

    /** 存档列表 */
    private final List<CharacterMemento> saves = new ArrayList<>();

    /** 存档 */
    public void saveGame(CharacterMemento memento) {
        saves.add(memento);
        System.out.printf("    📁 存档成功! [存档 #%d] %s%n",
                saves.size(), memento.getSummary());
    }

    /** 读取最近一次存档 */
    public CharacterMemento loadLatest() {
        if (saves.isEmpty()) {
            System.out.println("    ⚠️ 没有存档!");
            return null;
        }
        CharacterMemento memento = saves.get(saves.size() - 1);
        System.out.printf("    📁 读取最新存档 [#%d] %s%n",
                saves.size(), memento.getSummary());
        return memento;
    }

    /** 读取指定槽位的存档（从1开始） */
    public CharacterMemento loadSlot(int slot) {
        if (slot < 1 || slot > saves.size()) {
            System.out.printf("    ⚠️ 存档 #%d 不存在! 当前共 %d 个存档%n", slot, saves.size());
            return null;
        }
        CharacterMemento memento = saves.get(slot - 1);
        System.out.printf("    📁 读取存档 [#%d] %s%n", slot, memento.getSummary());
        return memento;
    }

    /** 列出所有存档 */
    public void listSaves() {
        if (saves.isEmpty()) {
            System.out.println("    📁 暂无存档");
            return;
        }
        System.out.println("    📁 存档列表:");
        for (int i = 0; i < saves.size(); i++) {
            System.out.printf("       #%d: %s%n", i + 1, saves.get(i).getSummary());
        }
    }

    public int getSaveCount() {
        return saves.size();
    }
}
