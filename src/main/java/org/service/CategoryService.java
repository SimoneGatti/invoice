package org.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by simonegatti on 27/02/16.
 */

public class CategoryService {

    public static enum Category {
        BOOK, FOOD, MEDICAL_PRODUCT, CD, PERFUME
    }

    public static Set<Category> getExemptedCategories() {
        return new HashSet<Category>(Arrays.asList(Category.BOOK, Category.FOOD, Category.MEDICAL_PRODUCT));
    }
}