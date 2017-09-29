package com.rometools.modules.base.types;

import org.junit.Assert;
import org.junit.Test;

public class AvailabilityEnumarationTest {
    @Test
    public void findByValue() throws Exception {
        Assert.assertNull(AvailabilityEnumeration.findByValue("test"));
        Assert.assertEquals(AvailabilityEnumeration.IN_STOCK, AvailabilityEnumeration.findByValue("in stock"));
        Assert.assertEquals(AvailabilityEnumeration.IN_STOCK, AvailabilityEnumeration.findByValue("LimitedAvailability"));
        Assert.assertEquals(AvailabilityEnumeration.OUT_OF_STOCK, AvailabilityEnumeration.findByValue("Discontinued"));
        Assert.assertEquals(AvailabilityEnumeration.OUT_OF_STOCK, AvailabilityEnumeration.findByValue("OutOfStock"));
        Assert.assertEquals(AvailabilityEnumeration.PRE_ORDER, AvailabilityEnumeration.findByValue("pre-sale"));
    }



}