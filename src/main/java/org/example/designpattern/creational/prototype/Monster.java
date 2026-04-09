package org.example.designpattern.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体原型 —— 怪物
 * <p>
 * 包含基本类型（int, String）和引用类型（Equipment, List），
 * 用来演示深拷贝的必要性。
 */
public class Monster implements GameUnit {

    private String name;
    private String type;
    private int hp;
    private int attack;
    private int defense;
    private Equipment equipment;       // 引用类型
    private List<String> skills;       // 引用类型

    public Monster(String name, String type, int hp, int attack, int defense,
                   Equipment equipment, List<String> skills) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.equipment = equipment;
        this.skills = skills;

        // 模拟复杂初始化过程
        simulateExpensiveInit();
    }

    /** 模拟耗时的初始化（加载模型、计算AI路径等）*/
    private void simulateExpensiveInit() {
        System.out.printf("    [初始化] %s: 加载3D模型 → 计算AI路径 → 加载音效（耗时500ms）%n", name);
    }

    /**
     * 私有构造函数 —— 专门给 clone 用，跳过耗时初始化
     */
    private Monster() {
        // 不调用 simulateExpensiveInit()，直接通过字段赋值
    }

    /**
     * 深拷贝克隆
     * <p>
     * 关键：引用类型字段必须创建新副本，而不是共享引用。
     */
    @Override
    public Monster clone() {
        Monster copy = new Monster();  // 跳过耗时初始化
        // 基本类型和 String：直接赋值（值拷贝）
        copy.name = this.name;
        copy.type = this.type;
        copy.hp = this.hp;
        copy.attack = this.attack;
        copy.defense = this.defense;
        // 引用类型：必须深拷贝
        copy.equipment = this.equipment.deepCopy();
        copy.skills = new ArrayList<>(this.skills);
        return copy;
    }

    @Override
    public void showInfo() {
        System.out.printf("    %s [%s] HP:%d ATK:%d DEF:%d 装备:%s 技能:%s%n",
                name, type, hp, attack, defense, equipment, skills);
    }

    // getter/setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }
    public Equipment getEquipment() { return equipment; }
    public List<String> getSkills() { return skills; }
}
