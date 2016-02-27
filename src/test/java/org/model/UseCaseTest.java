package org.model;

import org.junit.Test;
import org.service.CategoryService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class UseCaseTest {

    @Test
    public void receipt1ShouldPass() {

        HashMap items = new HashMap<Item, Integer>();
        items.put(new Item("book", false, new BigDecimal("12.49"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.BOOK))),1);
        items.put(new Item("music CD", false, new BigDecimal("14.99"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD))),1);
        items.put(new Item("chocolate bar", false, new BigDecimal("0.85"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD))),1);
        Receipt input = new Receipt(items);

        System.out.println(input.toString());
        System.out.println(input.getBill());

        assertEquals("total must be 1.50", input.getTaxAmount(), new BigDecimal("1.50"));
        assertEquals("tax amount must be 29.83", input.getTotal(), new BigDecimal("29.83"));
    }

    @Test
    public void receipt2ShouldPass() {

        HashMap items = new HashMap<Item, Integer>();
        items.put(new Item("imported box of chocolates", true, new BigDecimal("10.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD))),1);
        items.put(new Item("imported bottle of perfume", true, new BigDecimal("47.50"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.PERFUME))),1);
        Receipt input = new Receipt(items);

        System.out.println(input.toString());
        System.out.println(input.getBill());

        assertEquals("total must be 7.65", input.getTaxAmount(), new BigDecimal("7.65"));
        assertEquals("tax amount must be 65.15", input.getTotal(), new BigDecimal("65.15"));
    }

    @Test
    public void receipt3ShouldPass() {

        HashMap items = new HashMap<Item, Integer>();
        items.put(new Item("imported bottle of perfume", true, new BigDecimal("27.99"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.PERFUME))),1);
        items.put(new Item("bottle of perfume", false, new BigDecimal("18.99"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.PERFUME))),1);
        items.put(new Item("packet of headache pills", false, new BigDecimal("9.75"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.MEDICAL_PRODUCT))),1);
        items.put(new Item("box of imported chocolates", true, new BigDecimal("11.25"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD))),1);
        Receipt input = new Receipt(items);

        System.out.println(input.toString());
        System.out.println(input.getBill());

        assertEquals("total must be 6.70", input.getTaxAmount(), new BigDecimal("6.70"));
        assertEquals("tax amount must be 74.68", input.getTotal(), new BigDecimal("74.68"));
    }


}