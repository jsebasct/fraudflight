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
        r1 = new CardBlackListRule();
        r2 = new CountryRedListRule();
        r3 = new FlightDayRule();
    }

    @Test
    public void testCountRules() {
        FligthRule[] flights = {r1, r2, r3};

        int counter = 0;
        for (FligthRule rule : flights) {
            counter = counter + (rule.isEnabled() ? 1 : 0);
        }

        Assert.assertEquals(3, counter, 0.1);
    }

    @Test
    public void testEnabledRules() {
        r2.setEnabled(false);
        FligthRule[] flights = {r1, r2, r3};

        int counter = 0;
        for (FligthRule rule : flights) {
            counter = counter + (rule.isEnabled() ? 1 : 0);
        }

        Assert.assertEquals(2, counter, 0.1);
    }

    @Test
    public void testCheckAllEnabledRules() {
        FligthRule[] flights = {r1, r2, r3};

        //TODO che esto deberia algun funcion copada de lambda ?
        int score = 0;
        for (FligthRule rule : flights) {
            if (rule.isEnabled() ) {
                score += rule.getScore();
            }
        }

        System.out.println("Score: " + score);
        Assert.assertEquals(170, score, 0.1);
    }

    @Test
    public void testCheckEnabledRules() {
        r2.setEnabled(false);
        FligthRule[] flights = {r1, r2, r3};

        //TODO che esto deberia algun funcion copada de lambda ?
        int score = 0;
        for (FligthRule rule : flights) {
            if (rule.isEnabled() ) {
                score += rule.getScore();
            }
        }

        System.out.println("Score: " + score);
        Assert.assertEquals(130, score, 0.1);
    }


    @Test
    public void testCheckCardBlackRules() {
        //r2.setEnabled(false);
        FligthRule[] flights = {r1, r2, r3};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCardNumber(1234_5678_9012_3456L);
        ticket.setDestinationCity("Guinea");

        //TODO che esto deberia algun funcion copada de lambda ?
        int score = 0;
        for (FligthRule rule : flights) {
            if (rule.isEnabled() && rule.evaluate(ticket) ) {
                score += rule.getScore();
            }
        }

        System.out.println("Score: " + score);
        Assert.assertEquals(140, score, 0.1);
    }
}
