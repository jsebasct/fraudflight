package org.demo.people.flight.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FligthRuleTest {

    FligthRule r1;
    FligthRule r2;
    FligthRule r3;

    @Before
    public void before() {
        r1 = new BlackListRule();
        r2 = new RedListRule();
        r3 = new FlightDayRule();
    }

    @Test
    public void testCountRules() {
        FligthRule[] flights = {r1, r2, r3};

        int counter = 0;
        for (FligthRule rule : flights) {
            counter = counter + (rule.isEnabled() ? 1 : 0);
        }
        System.out.println("Enabled: " + counter);
        Assert.assertEquals(3, counter, 0.1);
    }

    @Test
    public void testEnabledRules() {
        System.out.println("Test 2");

        r2.setEnabled(false);
        FligthRule[] flights = {r1, r2, r3};

        int counter = 0;
        for (FligthRule rule : flights) {
            counter = counter + (rule.isEnabled() ? 1 : 0);
        }
        System.out.println("Enabled: " + counter);
        Assert.assertEquals(2, counter, 0.1);
    }

}
