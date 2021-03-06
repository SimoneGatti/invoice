package org.model;

import org.service.CategoryService;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by simonegatti on 27/02/16.
 */
public class Item {

    private String description;
    private Boolean imported;
    private BigDecimal price;
    private Set<CategoryService.Category> categories;
    private List<Tax> taxes;


    public Item(String description, Boolean imported, BigDecimal price) {
        this.description = description;
        this.imported = imported;
        this.price = price;
        this.categories = new HashSet<CategoryService.Category>();
        resetTaxes();
    }

    public Item(String description, Boolean imported, BigDecimal price, Set categories) {
        this.description = description;
        this.imported = imported;
        this.price = price;
        this.categories = categories;
        resetTaxes();
    }


    private void resetTaxes() {
        ArrayList<Tax> taxesTemp = new ArrayList<Tax>();
        Set<CategoryService.Category> exempedCategory = CategoryService.getExemptedCategories();
        if(imported) {
            taxesTemp.add(ImportedTax.getTax());
        }
        if(!exempedCategory.removeAll(categories)){
            taxesTemp.add(GoodTax.getTax());
        }
        taxes = taxesTemp;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
        resetTaxes();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<CategoryService.Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryService.Category> categories) {
        this.categories = categories;
        resetTaxes();
    }


    public BigDecimal getTaxAmount() {
        BigDecimal totalRate = new BigDecimal("0.0");
        for(Iterator<Tax> i = taxes.iterator(); i.hasNext();) {
            totalRate = totalRate.add(i.next().getRate());
        }
        return price.multiply(totalRate);
    }


    @Override
    public String toString() {
        return this.description + " at " + this.price.toString();
    }
}
