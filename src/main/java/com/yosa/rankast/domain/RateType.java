package com.yosa.rankast.domain;

public enum RateType {
    Up(1),
    Down(-1);

    private long rate;

    RateType(long rate) {
        this.rate = rate;
    }

    public long getRate() {
        return rate;
    }
}
