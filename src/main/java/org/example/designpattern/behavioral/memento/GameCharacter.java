package org.example.designpattern.behavioral.memento;

import java.time.LocalDateTime;

/**
 * 原发器 (Originator) —— 游戏角色
 * <p>
 * 角色拥有自己的内部状态（HP、MP、等级、位置等），
 * 并且能够：
 *   1. save()    —— 创建当前状态的备忘录（快照）
 *   2. restore() —— 从备忘录恢复到之前的状态
 * <p>
 * 只有 GameCharacter 自己知道如何创建和恢复快照，
 * 外部（GameSaveManager）只负责保管备忘录。
 */
public class GameCharacter {

    private String name;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int level;
    private int x;
    private int y;

    public GameCharacter(String name, int maxHp, int maxMp) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.mp = maxMp;
        this.maxMp = maxMp;
        this.level = 1;
        this.x = 0;
        this.y = 0;
    }

    // ─────────────────────────────────────────────
    //  游戏操作（改变内部状态）
    // ─────────────────────────────────────────────

    /** 战斗 —— 消耗 HP 和 MP */
    public void fight(String enemy, int hpCost, int mpCost) {
        this.hp = Math.max(0, this.hp - hpCost);
        this.mp = Math.max(0, this.mp - mpCost);
        System.out.printf("    ⚔️ %s 与 [%s] 战斗! HP -%d, MP -%d%n", name, enemy, hpCost, mpCost);
        printStatus();
    }

    /** 升级 */
    public void levelUp() {
        this.level++;
        this.maxHp += 50;
        this.maxMp += 30;
        this.hp = maxHp;  // 升级回满血
        this.mp = maxMp;
        System.out.printf("    🎉 %s 升级! 当前 Lv.%d, MaxHP: %d, MaxMP: %d%n",
                name, level, maxHp, maxMp);
        printStatus();
    }

    /** 移动到指定位置 */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.printf("    🚶 %s 移动到 (%d, %d)%n", name, x, y);
    }

    /** 打印当前状态 */
    public void printStatus() {
        System.out.printf("    📊 [%s] Lv.%d | HP: %d/%d | MP: %d/%d | 位置: (%d,%d)%n",
                name, level, hp, maxHp, mp, maxMp, x, y);
    }

    // ─────────────────────────────────────────────
    //  备忘录相关 —— 核心！
    // ─────────────────────────────────────────────

    /**
     * 创建备忘录（存档）
     * <p>
     * 只有角色自己知道需要保存哪些状态。
     * 返回一个不可变的快照对象。
     */
    public CharacterMemento save() {
        System.out.printf("    💾 %s 创建存档...%n", name);
        return new CharacterMemento(name, hp, mp, level, x, y, LocalDateTime.now());
    }

    /**
     * 从备忘录恢复（读档）
     * <p>
     * 只有角色自己知道如何从快照恢复状态。
     */
    public void restore(CharacterMemento memento) {
        this.name = memento.name();
        this.hp = memento.hp();
        this.mp = memento.mp();
        this.level = memento.level();
        this.x = memento.x();
        this.y = memento.y();
        // 根据等级恢复上限
        this.maxHp = 100 + (level - 1) * 50;
        this.maxMp = 50 + (level - 1) * 30;
        System.out.printf("    📂 %s 读档成功!%n", name);
        printStatus();
    }

    public String getName() {
        return name;
    }
}
