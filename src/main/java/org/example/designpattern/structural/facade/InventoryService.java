package org.example.designpattern.structural.facade;

import java.util.HashMap;
import java.util.Map;

/**
 * 子系统① —— 库存服务
 */
public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public InventoryService() {
        stock.put("iPhone", 10);
        stock.put("MacBook", 5);
        stock.put("AirPods", 20);
    }

    public boolean checkStock(String productName, int quantity) {
        int available = stock.getOrDefault(productName, 0);
        System.out.printf("    [库存] 检查库存: %s 需要%d件，当前库存%d件%n",
                productName, quantity, available);
        return available >= quantity;
    }

    public void deductStock(String productName, int quantity) {
        stock.merge(productName, -quantity, Integer::sum);
        System.out.printf("    [库存] 扣减库存: %s -%d件，剩余%d件%n",
                productName, quantity, stock.get(productName));
    }

    public void restoreStock(String productName, int quantity) {
        stock.merge(productName, quantity, Integer::sum);
        System.out.printf("    [库存] 恢复库存: %s +%d件%n", productName, quantity);
    }
}
