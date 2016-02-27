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
        this.total = new BigDecimal("0.00");
        this.taxAmount = new BigDecimal("0.00");
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
        if(taxAmount.remainder(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_UP).equals(new BigDecimal("0.00")))
        {
            taxAmount = taxAmount.setScale(2);
        }
        else
        {
            taxAmount = taxAmount.add(new BigDecimal("0.05").subtract(taxAmount.remainder(new BigDecimal("0.05")))).setScale(2);
        }
        total = total.add(taxAmount).setScale(2);
    }


    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
        calculate();
    }

    public void addItem(Item item) {
        if(items.containsKey(item))
        {
            items.put(item, items.get(item) + 1);
        }
        else
        {
            items.put(item, 1);
        }
        calculate();
    }

    public void removeItem(Item item) {
        if(items.containsKey(item))
        {
            if(items.get(item) > 1) {
                items.put(item, items.get(item) - 1);
            }
            else
            {
                items.remove(item);
            }
        }
        calculate();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }


    public String getBill() {
        String result = this.toString();
        result = result + "Sales Taxes: " + taxAmount.toString() + "\n" + "Total: " + total.toString() + "\n";
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
