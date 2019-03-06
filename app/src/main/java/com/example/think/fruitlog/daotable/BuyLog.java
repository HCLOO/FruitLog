package com.example.think.fruitlog.daotable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BuyLog {
    @Id
    private Long id;
    private String fruitName;
    private int fruitImageId;
    private String fruitBuyTime;
    @Generated(hash = 1659393032)
    public BuyLog(Long id, String fruitName, int fruitImageId,
            String fruitBuyTime) {
        this.id = id;
        this.fruitName = fruitName;
        this.fruitImageId = fruitImageId;
        this.fruitBuyTime = fruitBuyTime;
    }
    @Generated(hash = 1523784531)
    public BuyLog() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFruitName() {
        return this.fruitName;
    }
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
    public int getFruitImageId() {
        return this.fruitImageId;
    }
    public void setFruitImageId(int fruitImageId) {
        this.fruitImageId = fruitImageId;
    }
    public String getFruitBuyTime() {
        return this.fruitBuyTime;
    }
    public void setFruitBuyTime(String fruitBuyTime) {
        this.fruitBuyTime = fruitBuyTime;
    }
}
