package org.example.designpattern.creational.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原型注册表 —— 怪物模板管理器
 * <p>
 * 预先创建好各种怪物模板（原型），需要时直接克隆。
 * 避免每次都走耗时的初始化流程。
 */
public class MonsterRegistry {

    private final Map<String, Monster> templates = new HashMap<>();

    public MonsterRegistry() {
        System.out.println("  ═══ 初始化怪物模板（只需一次）═══");
        // 创建模板时才走耗时初始化
        templates.put("goblin", new Monster(
                "哥布林", "普通怪", 100, 15, 5,
                new Equipment("木棍", 3),
                List.of("撕咬", "逃跑")
        ));
        templates.put("dragon", new Monster(
                "火龙", "精英怪", 5000, 200, 100,
                new Equipment("龙息", 50),
                List.of("火焰吐息", "龙爪", "飞行", "龙尾横扫")
        ));
        templates.put("boss", new Monster(
                "暗影领主", "Boss", 50000, 500, 300,
                new Equipment("暗影之刃", 150),
                List.of("暗影打击", "灵魂吸取", "召唤亡灵", "暗影风暴", "不死之身")
        ));
        System.out.println("  ═══ 模板初始化完成 ═══\n");
    }

    /**
     * 通过克隆创建怪物 —— 不走耗时初始化，直接复制
     */
    public Monster createMonster(String type) {
        Monster template = templates.get(type);
        if (template == null) {
            throw new IllegalArgumentException("未知怪物类型: " + type);
        }
        System.out.printf("  [注册表] 克隆 \"%s\" 模板（跳过初始化）%n", type);
        return template.clone();
    }
}
