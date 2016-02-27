package org.model;

import org.service.CategoryService;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by simonegatti on 27/02/16.
 */
public class Receipt {

    private Map<Item, Integer> items;
    private BigDecimal total;
    private BigDecimal taxAmount;

    public Receipt() {
        this.items = new HashMap<Item, Integer>();
        this.total = new BigDecimal("0.0");
        this.taxAmount = new BigDecimal("0.0");
    }

    public Receipt(Map<Item, Integer> items) {
        this.items = items;
        calculate();
    }

    private void calculate() {
        total = new BigDecimal("0.0");
        taxAmount = new BigDecimal("0.0");
        for(Iterator<Map.Entry<Item, Integer>> i = items.entrySet().iterator(); i.hasNext();) {
            Map.Entry<Item, Integer> nextEntry = i.next();
            Item nextItem = nextEntry.getKey();
            BigDecimal number = new BigDecimal(nextEntry.getValue());
            total = total.add(nextItem.getPrice().multiply(number));
            taxAmount = taxAmount.add(nextItem.getTaxAmount().multiply(number));
        }
        taxAmount = taxAmount.add(new BigDecimal("0.05").subtract(taxAmount.remainder(new BigDecimal("0.05")))).setScale(2);
        total = total.add(taxAmount).setScale(2);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public String getBill() {
        String result = "";
        for(Iterator<Map.Entry<Item, Integer>> i = items.entrySet().iterator(); i.hasNext();) {
            Map.Entry<Item, Integer> nextEntry = i.next();
            Item nextItem = nextEntry.getKey();
            result = result + nextEntry.getValue() + " " + nextItem.toString() + "\n";
        }
        result = result + "Sales Taxes: " + taxAmount.toString() + "\n" + "Total: " + total.toString();
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for(Iterator<Map.Entry<Item, Integer>> i = items.entrySet().iterator(); i.hasNext();) {
            Map.Entry<Item, Integer> nextEntry = i.next();
            Item nextItem = nextEntry.getKey();
            result = result + nextEntry.getValue() + " " + nextItem.toString() + "\n";
        }
        return result;
    }
}
