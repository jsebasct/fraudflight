package org.demo.people.flight.business;

import org.junit.Assert;
import org.junit.Test;

public class FligthRuleTest {

    @Test
    public void testCountRules() {
        FligthRule r1 = new BlackListRule();
        FligthRule r2 = new RedListRule();
        FligthRule r3 = new FlightDayRule();

        FligthRule[] flights = {r1, r2, r3};

        int counter = 0;
        for (FligthRule rule : flights) {
            counter = counter + (rule.isEnabled() ? 1 : 0);
        }
        System.out.println("Enabled: " + counter);
        Assert.assertEquals(3, counter, 0.1);
    }

}
