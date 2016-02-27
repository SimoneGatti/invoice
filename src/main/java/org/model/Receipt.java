package org.model;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by simonegatti on 27/02/16.
 */
public class Receipt {

    private Hashtable<Item, Integer> items;


    public Receipt() {
        this.items = new Hashtable<Item, Integer>();
    }

    public Receipt(Hashtable<Item, Integer> items) {
        this.items = items;
    }


    public Hashtable<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Hashtable<Item, Integer> items) {
        this.items = items;
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
    }


    public Invoice getInvoice() {
        String print = "";
        BigDecimal totalTemp = new BigDecimal("0.0");
        BigDecimal taxAmountTemp = new BigDecimal("0.0");
        for(Iterator<Map.Entry<Item, Integer>> i = items.entrySet().iterator(); i.hasNext();) {
            Map.Entry<Item, Integer> nextEntry = i.next();
            Item nextItem = nextEntry.getKey();
            BigDecimal number = new BigDecimal(nextEntry.getValue());
            print = print + nextEntry.getValue() + " " + nextItem.toString() + "\n";
            totalTemp = totalTemp.add(nextItem.getPrice().multiply(number));
            taxAmountTemp = taxAmountTemp.add(nextItem.getTaxAmount().multiply(number));
        }
        if(taxAmountTemp.remainder(new BigDecimal("0.05")).setScale(2, BigDecimal.ROUND_UP).equals(new BigDecimal("0.00")))
        {
            taxAmountTemp = taxAmountTemp.setScale(2);
        }
        else
        {
            taxAmountTemp = taxAmountTemp.add(new BigDecimal("0.05").subtract(taxAmountTemp.remainder(new BigDecimal("0.05")))).setScale(2);
        }
        totalTemp = totalTemp.add(taxAmountTemp).setScale(2);
        print = print + "Sales Taxes: " + taxAmountTemp.toString() + "\n" + "Total: " + totalTemp.toString() + "\n";
        return new Invoice(print, totalTemp, taxAmountTemp);
    }

    public String getBill() {
        String result = this.toString();
        Invoice invoice = getInvoice();

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
