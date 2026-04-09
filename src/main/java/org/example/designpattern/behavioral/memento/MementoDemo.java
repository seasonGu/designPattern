package org.example.designpattern.behavioral.memento;

/**
 * 备忘录模式 —— 演示
 */
public class MementoDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用备忘录，如何实现存档/读档？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                不用备忘录的存档：
                // 手动拷贝每个字段
                int savedHp = character.getHp();
                int savedMp = character.getMp();
                int savedLevel = character.getLevel();
                int savedX = character.getX();
                int savedY = character.getY();
                // ... 字段越多越痛苦

                // 读档时手动恢复
                character.setHp(savedHp);
                character.setMp(savedMp);
                // ... 又是一堆 setter

                问题：
                  ❌ 外部直接访问对象内部状态 → 破坏封装性
                  ❌ 字段增减时，存档/读档代码都要改
                  ❌ 多个存档槽位管理更加混乱
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 创建角色，开始冒险");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        GameCharacter hero = new GameCharacter("勇者", 100, 50);
        GameSaveManager saveManager = new GameSaveManager();
        hero.printStatus();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 战斗并存档");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 打几个小怪
        hero.moveTo(10, 5);
        hero.fight("史莱姆", 10, 5);
        hero.fight("哥布林", 15, 10);
        hero.levelUp();
        System.out.println();

        // ★ 打 Boss 之前存档！
        System.out.println("  >>> 准备挑战 Boss，先存个档 <<<");
        saveManager.saveGame(hero.save());
        System.out.println();

        // 继续打怪
        hero.moveTo(20, 15);
        hero.fight("骷髅兵", 20, 15);
        System.out.println();

        // 再存一档
        System.out.println("  >>> 再存一个档 <<<");
        saveManager.saveGame(hero.save());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 挑战 Boss 失败，读档恢复!");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  >>> 挑战暗黑龙王! <<<");
        hero.fight("暗黑龙王", 80, 40);
        System.out.println("  💀 被 Boss 秒杀了... 血量太低!");
        System.out.println();

        // 读取最新存档
        System.out.println("  >>> 读取最新存档 <<<");
        CharacterMemento latestSave = saveManager.loadLatest();
        if (latestSave != null) {
            hero.restore(latestSave);
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 多存档槽位管理");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        saveManager.listSaves();
        System.out.println();

        // 回到更早的存档（刚打完哥布林升级后的状态）
        System.out.println("  >>> 读取存档 #1（打 Boss 之前的状态）<<<");
        CharacterMemento slot1 = saveManager.loadSlot(1);
        if (slot1 != null) {
            hero.restore(slot1);
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                备忘录模式的关键：对象自己负责快照，外部只负责保管

                ┌──────────────────┐  save()   ┌──────────────────┐
                │ GameCharacter    │ ────────► │ CharacterMemento │
                │ (原发器)          │           │ (备忘录/快照)     │
                │ hp=30, mp=10     │ ◄──────── │ hp=80, mp=40     │
                │ level=2          │ restore() │ level=2          │
                └──────────────────┘           └────────┬─────────┘
                                                        │ 保管
                                               ┌───────┴─────────┐
                                               │ GameSaveManager  │
                                               │ (管理者)          │
                                               │ saves: [#1, #2]  │
                                               │ 只存取，不偷看！   │
                                               └─────────────────┘

                三个角色的职责：
                  原发器：知道自己有哪些状态，负责创建和恢复快照
                  备忘录：不可变的快照对象，存储状态数据
                  管理者：保管备忘录，但不能查看或修改内容

                Java/Spring 中的备忘录模式：
                  Serializable —— 序列化就是一种广义的备忘录
                  Spring Webflow —— FlowExecution 的快照恢复
                  IDE 的 Ctrl+Z 撤销 —— 部分实现用备忘录
                  git stash —— 暂存工作区状态，随时恢复
                """);
    }
}
