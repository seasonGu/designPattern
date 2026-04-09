package org.example.designpattern.creational.prototype;

/**
 * 装备类 —— 用于演示深拷贝 vs 浅拷贝
 * <p>
 * 这是一个引用类型字段。
 * 浅拷贝时两个怪物共享同一个 Equipment 对象（修改一个会影响另一个）。
 * 深拷贝时每个怪物有自己独立的 Equipment 副本。
 */
public class Equipment {

    private String weapon;
    private int attackBonus;

    public Equipment(String weapon, int attackBonus) {
        this.weapon = weapon;
        this.attackBonus = attackBonus;
    }

    /** 深拷贝：创建全新的 Equipment */
    public Equipment deepCopy() {
        return new Equipment(this.weapon, this.attackBonus);
    }

    public String getWeapon() { return weapon; }
    public void setWeapon(String weapon) { this.weapon = weapon; }
    public int getAttackBonus() { return attackBonus; }
    public void setAttackBonus(int attackBonus) { this.attackBonus = attackBonus; }

    @Override
    public String toString() {
        return String.format("Equipment{%s, +%d攻击}", weapon, attackBonus);
    }
}
