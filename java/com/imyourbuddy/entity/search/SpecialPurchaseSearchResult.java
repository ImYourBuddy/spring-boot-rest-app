package com.imyourbuddy.entity.search;

import java.util.Date;

public interface SpecialPurchaseSearchResult {
    Date getDate();
    String getBuyer();
    double getDiscount();
    String getBook();
    int getQuantity();
}
