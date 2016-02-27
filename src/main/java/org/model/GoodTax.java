package org.model;

import java.math.BigDecimal;

/**
 * Created by simonegatti on 27/02/16.
 */
public class GoodTax extends Tax {

    private static GoodTax instance = null;


    private GoodTax() {
        super("good tax", new BigDecimal("0.1"));
    }


    public static synchronized GoodTax getTax() {
        if (instance == null) {
            instance = new GoodTax();
        }
        return instance;
    }
}