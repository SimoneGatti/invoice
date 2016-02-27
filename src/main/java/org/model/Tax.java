package org.model;

import java.math.BigDecimal;

/**
 * Created by simonegatti on 27/02/16.
 */
public abstract class Tax {
    private String description;
    private BigDecimal rate;

    public Tax (String description, BigDecimal rate) {
        this.description = description;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
