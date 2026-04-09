package org.example.designpattern.creational.prototype;

/**
 * 原型模式 —— 演示
 */
public class PrototypeDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 原型注册表 —— 预创建模板，按需克隆");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        MonsterRegistry registry = new MonsterRegistry();

        // 克隆3个哥布林（不走耗时初始化）
        System.out.println("--- 生成3个哥布林 ---");
        Monster goblin1 = registry.createMonster("goblin");
        goblin1.setName("哥布林-A");
        goblin1.showInfo();

        Monster goblin2 = registry.createMonster("goblin");
        goblin2.setName("哥布林-B");
        goblin2.setHp(120);  // 微调属性
        goblin2.showInfo();

        Monster goblin3 = registry.createMonster("goblin");
        goblin3.setName("哥布林-精英");
        goblin3.setHp(200);
        goblin3.setAttack(30);
        goblin3.getEquipment().setWeapon("铁剑");
        goblin3.getEquipment().setAttackBonus(10);
        goblin3.showInfo();

        System.out.println();
        System.out.println("--- 生成1个火龙 ---");
        Monster dragon = registry.createMonster("dragon");
        dragon.setName("烈焰飞龙");
        dragon.showInfo();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 深拷贝验证 —— 修改克隆体不影响原型");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        Monster boss1 = registry.createMonster("boss");
        boss1.setName("暗影领主-一阶段");
        Monster boss2 = registry.createMonster("boss");
        boss2.setName("暗影领主-二阶段（狂暴）");
        boss2.setHp(80000);
        boss2.setAttack(800);
        boss2.getEquipment().setWeapon("混沌之刃");
        boss2.getEquipment().setAttackBonus(300);
        boss2.getSkills().add("毁灭一击");

        System.out.println("  boss1（一阶段）:");
        boss1.showInfo();
        System.out.println("  boss2（二阶段狂暴）:");
        boss2.showInfo();
        System.out.println();
        System.out.println("  ✅ boss2 的修改没有影响 boss1 —— 深拷贝成功！");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 性能对比 —— new vs clone");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  用 new 创建（每次都走耗时初始化）:");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            new Monster("哥布林-" + i, "普通怪", 100, 15, 5,
                    new Equipment("木棍", 3),
                    java.util.List.of("撕咬", "逃跑"));
        }
        System.out.printf("  new 3次完成%n%n");

        System.out.println("  用 clone 创建（跳过初始化，直接复制）:");
        for (int i = 0; i < 3; i++) {
            Monster m = registry.createMonster("goblin");
            m.setName("哥布林-clone-" + i);
        }
        System.out.printf("  clone 3次完成%n");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                原型模式的关键：复制 > 新建

                ┌─────────────┐    clone()    ┌─────────────┐
                │ 原型(模板)   │─────────────►│  新对象(副本) │
                │ 已初始化好   │   快速复制     │ 独立的副本    │
                └─────────────┘              └─────────────┘

                浅拷贝 vs 深拷贝：
                ┌──────────┬───────────────────────────────────┐
                │ 浅拷贝    │ 基本类型复制，引用类型共享          │
                │          │ 修改引用字段 → 影响原对象 ❌        │
                ├──────────┼───────────────────────────────────┤
                │ 深拷贝    │ 所有字段都创建新副本               │
                │          │ 修改任何字段 → 不影响原对象 ✅      │
                └──────────┴───────────────────────────────────┘

                Java 中的原型模式：
                  Object.clone()     → 浅拷贝（需实现 Cloneable）
                  拷贝构造函数        → 手动深拷贝（推荐）
                  序列化/反序列化     → 自动深拷贝（性能差）
                  Spring BeanUtils   → 浅拷贝工具
                """);
    }
}
