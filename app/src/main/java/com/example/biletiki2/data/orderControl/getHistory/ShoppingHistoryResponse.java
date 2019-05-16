package com.example.biletiki2.data.orderControl.getHistory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingHistoryResponse {
    private List<ShoppingHistory> shoppingHistory;

    public ShoppingHistoryResponse(List<ShoppingHistory> shoppingHistory) {
        this.shoppingHistory = new ArrayList<>();
        this.shoppingHistory = shoppingHistory;
    }

    public List<ShoppingHistory> getShoppingHistory() {
        return shoppingHistory;
    }

    public void setShoppingHistory(List<ShoppingHistory> shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }
}
