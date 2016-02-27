package org.model;

import java.math.BigDecimal;

/**
 * Created by simonegatti on 27/02/16.
 */
public class ImportedTax extends Tax {
    private static ImportedTax instance = null;

    private ImportedTax() {
        super("imported tax", new BigDecimal("0.05"));
    }

    public static synchronized ImportedTax getTax() {
        if (instance == null) {
            instance = new ImportedTax();
        }
        return instance;
    }
}
