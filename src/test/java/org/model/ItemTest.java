package org.model;

import org.junit.Test;
import org.service.CategoryService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by simonegatti on 27/02/16.
 */
public class ItemTest {

    @Test
    public void emptyCategories() {

        Item item = new Item("item", false, new BigDecimal("15.00"), new HashSet<CategoryService.Category>());

        assertEquals("tax amount must be 1.50", item.getTaxAmount().setScale(2), new BigDecimal("1.50"));
    }

    @Test
    public void getExemptedCategories() {

        Item item = new Item("item", false, new BigDecimal("15.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD)));

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));

        item.setCategories(new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD, CategoryService.Category.CD)));

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));
    }

    @Test
    public void importedGood() {

        Item item = new Item("item", true, new BigDecimal("10.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD)));

        assertEquals("tax amount must be 0.5", item.getTaxAmount().setScale(2), new BigDecimal("0.50"));

        item.setCategories(new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD)));

        assertEquals("tax amount must be 1.5", item.getTaxAmount().setScale(2), new BigDecimal("1.50"));
    }

    @Test
    public void priceChange() {

        Item item = new Item("item", false, new BigDecimal("15.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD)));

        assertEquals("tax amount must be 1.50", item.getTaxAmount().setScale(2), new BigDecimal("1.50"));

        item.setPrice(new BigDecimal("16.00"));

        assertEquals("tax amount must be 1.60", item.getTaxAmount().setScale(2), new BigDecimal("1.60"));

        item.setPrice(new BigDecimal("15.00"));

        assertEquals("tax amount must be 1.50", item.getTaxAmount().setScale(2), new BigDecimal("1.50"));
    }

    @Test
    public void importedChange() {

        Item item = new Item("item", false, new BigDecimal("10.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD)));

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));

        item.setImported(true);

        assertEquals("tax amount must be 0.5", item.getTaxAmount().setScale(2), new BigDecimal("0.50"));

        item.setImported(false);

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));
    }

    @Test
    public void categoryChange() {

        Item item = new Item("item", false, new BigDecimal("10.00"), new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD)));

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));

        item.setCategories(new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.CD)));

        assertEquals("tax amount must be 1", item.getTaxAmount().setScale(2), new BigDecimal("1.00"));

        item.setCategories(new HashSet<CategoryService.Category>(Arrays.asList(CategoryService.Category.FOOD)));

        assertEquals("tax amount must be 0", item.getTaxAmount().setScale(2), new BigDecimal("0.00"));
    }
}
