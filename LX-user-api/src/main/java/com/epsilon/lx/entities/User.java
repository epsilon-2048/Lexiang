package com.epsilon.lx.entities;

public class User {
    private Long id;

    private Long baseUserId;

    private Integer overage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Long baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Integer getOverage() {
        return overage;
    }

    public void setOverage(Integer overage) {
        this.overage = overage;
    }
}