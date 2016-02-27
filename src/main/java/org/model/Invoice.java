package org.model;

import java.math.BigDecimal;

/**
 * Created by simonegatti on 27/02/16.
 */
public class Invoice {
    private String print;
    private BigDecimal total;
    private BigDecimal taxAmount;

    public Invoice (String print, BigDecimal total, BigDecimal taxAmount) {
        this.print = print;
        this.total = total;
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return print;
    }
}
