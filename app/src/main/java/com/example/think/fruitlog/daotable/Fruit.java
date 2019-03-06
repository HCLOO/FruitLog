package com.example.think.fruitlog.daotable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Fruit {
    @Id
    private Long id;
    private String fruitName;
    private int fruitImageId;
    private String fruitDetail;
    @Generated(hash = 522044184)
    public Fruit(Long id, String fruitName, int fruitImageId, String fruitDetail) {
        this.id = id;
        this.fruitName = fruitName;
        this.fruitImageId = fruitImageId;
        this.fruitDetail = fruitDetail;
    }
    @Generated(hash = 2030614587)
    public Fruit() {
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
    public String getFruitDetail() {
        return this.fruitDetail;
    }
    public void setFruitDetail(String fruitDetail) {
        this.fruitDetail = fruitDetail;
    }
}
