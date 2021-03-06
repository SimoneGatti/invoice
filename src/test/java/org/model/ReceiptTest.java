package org.model;

import org.junit.Test;
import org.service.CategoryService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

/**
 * Created by simonegatti on 27/02/16.
 */
public class ReceiptTest {

    @Test
    public void emptyReceipt() {

        Receipt input = new Receipt();

        Invoice invoice = input.getInvoice();
        assertEquals("total must be 0.00", invoice.getTaxAmount(), new BigDecimal("0.00"));
        assertEquals("tax amount must be 0.00", invoice.getTotal(), new BigDecimal("0.00"));


    }

    @Test
    public void itemAdded() {

        Item musicCD = new Item("music CD", false, new BigDecimal("14.99"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD)));
        Hashtable items = new Hashtable<Item, Integer>();
        items.put(new Item("book", false, new BigDecimal("12.49"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.BOOK))),1);
        items.put(new Item("chocolate bar", false, new BigDecimal("0.85"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD))),1);
        Receipt input = new Receipt(items);

        Invoice invoice = input.getInvoice();
        assertEquals("total must be 0.00", invoice.getTaxAmount(), new BigDecimal("0.00"));
        assertEquals("tax amount must be 13.34", invoice.getTotal(), new BigDecimal("13.34"));

        input.addItem(musicCD);

        invoice = input.getInvoice();
        assertEquals("total must be 1.50", invoice.getTaxAmount(), new BigDecimal("1.50"));
        assertEquals("tax amount must be 29.83", invoice.getTotal(), new BigDecimal("29.83"));

        input.addItem(musicCD);

        invoice = input.getInvoice();
        assertEquals("total must be 3.00", invoice.getTaxAmount(), new BigDecimal("3.00"));
        assertEquals("tax amount must be 46.32", invoice.getTotal(), new BigDecimal("46.32"));
    }

    @Test
    public void itemRemoved() {

        Item musicCD = new Item("music CD", false, new BigDecimal("14.99"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD)));
        Hashtable items = new Hashtable<Item, Integer>();
        items.put(new Item("book", false, new BigDecimal("12.49"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.BOOK))),1);
        items.put(musicCD,2);
        items.put(new Item("chocolate bar", false, new BigDecimal("0.85"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD))),1);
        Receipt input = new Receipt(items);

        Invoice invoice = input.getInvoice();
        assertEquals("total must be 3.00", invoice.getTaxAmount(), new BigDecimal("3.00"));
        assertEquals("tax amount must be 46.32", invoice.getTotal(), new BigDecimal("46.32"));

        input.removeItem(musicCD);

        invoice = input.getInvoice();
        assertEquals("total must be 1.50", invoice.getTaxAmount(), new BigDecimal("1.50"));
        assertEquals("tax amount must be 29.83", invoice.getTotal(), new BigDecimal("29.83"));

        input.removeItem(musicCD);

        invoice = input.getInvoice();
        assertEquals("total must be 0.00", invoice.getTaxAmount(), new BigDecimal("0.00"));
        assertEquals("tax amount must be 13.34", invoice.getTotal(), new BigDecimal("13.34"));
    }
}
