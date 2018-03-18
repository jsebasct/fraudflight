package org.demo.people.flight.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FligthRuleTest {

    private FligthRule r1;
    private FligthRule r2;
    private FligthRule r3;

    private Passenger[] passengers;

    @Before
    public void before() {
        r1 = new CardBlackListRule();
        r2 = new CountryRedListRule();
        r3 = new FlightDayRule();

        Passenger p1 = new Passenger("Jhon", "Smith");
        Passenger p2 = new Passenger("Jane", "SmithSSSSS");

        passengers = new Passenger[2];
        passengers[0] = p1;
        passengers[1] = p2;
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
        ticket.setFlyDate(LocalDate.of(2015, 3, 16));

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

    @Test
    public void testCheckTrip24Rule() {
        r1.setEnabled(false);
        r2.setEnabled(false);
        FligthRule[] flights = {r1, r2, r3};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCardNumber(1234_5678_9012_3456L);
        ticket.setDestinationCity("Guinea");
        //ticket.setFlyDate(LocalDate.of(2018, 3, 16));
        ticket.setFlyDate(LocalDate.now());

        int score = 0;
        for (FligthRule rule : flights) {
            if (rule.isEnabled() && rule.evaluate(ticket)) {
                score += rule.getScore();
            }
        }

        System.out.println("Score24: " + score);
        Assert.assertEquals(30, score, 0.1);
    }

    @Test
    public void testCheckLastNameRule() {
        FligthRule r1 = new LastNameMatchRule();
        FligthRule[] flights = {r1};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCardNumber(1234_5678_9012_3456L);
        ticket.setDestinationCity("Guinea");
        ticket.setFlyDate(LocalDate.of(2018, 3, 16));
        ticket.setPassengers(passengers);

        int score = 0;
        for (FligthRule rule : flights) {
            if (rule.isEnabled() && rule.evaluate(ticket)) {
                score += rule.getScore();
            }
        }

        System.out.println("ScoreName: " + score);
        Assert.assertEquals(25, score, 0.1);
    }
}
