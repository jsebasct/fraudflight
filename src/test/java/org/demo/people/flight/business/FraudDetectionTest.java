package org.demo.people.flight.business;

import org.demo.people.flight.business.rules.FligthRule;
import org.demo.people.flight.business.rules.PurchaseLimitRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class FraudDetectionTest {

    private Person[] passengers;
    private CreditCard creditCard;

    @Before
    public void init() {
        Person holder = new Person("Juan", "Perez");
        creditCard = new CreditCard(1234_5678_9012_3456L, holder);

        Person p1 = new Person("Jhon", "Smith");
        Person p2 = new Person("Jane", "SmithSSSSS");

        passengers = new Person[2];
        passengers[0] = p1;
        passengers[1] = p2;
    }

    @Test
    public void testCheckFlyScore() {
        FligthRule r1 = new PurchaseLimitRule();
        FligthRule[] flights = {r1};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCard(creditCard);
        ticket.setDestinationCity("Guinea");
        ticket.setFlyDate(LocalDate.now());
        ticket.setPassengers(passengers);
        ticket.setPurchaseAmount(60000);

        FraudDetection detect = new FraudDetection();

        int score = detect.getScore(ticket);
        Assert.assertEquals(230, score, 0.1);
    }

    @Test
    public void testCheckTrueFraud() {
        FligthRule r1 = new PurchaseLimitRule();
        FligthRule[] flights = {r1};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCard(creditCard);
        ticket.setDestinationCity("Guinea");
        ticket.setFlyDate(LocalDate.now());
        ticket.setPassengers(passengers);
        ticket.setPurchaseAmount(60000);

        FraudDetection detect = new FraudDetection();
        detect.setUmbral(60);
        boolean fraud = detect.isFraud(ticket);
        Assert.assertTrue(fraud);
    }

    @Test
    public void testCheckFalseFraud() {
        FligthRule r1 = new PurchaseLimitRule();
        FligthRule[] flights = {r1};


        Person holder = new Person("Juan", "Valdez");
        creditCard = new CreditCard(9234_5678_9012_3456L, holder);

        Person p1 = new Person("Jhon", "Smith");
        Person p2 = new Person("Jane", "Valdez");

        passengers = new Person[2];
        passengers[0] = p1;
        passengers[1] = p2;

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCard(creditCard);
        ticket.setDestinationCity("Bolivia");
        ticket.setFlyDate(LocalDate.now());
        ticket.setPassengers(passengers);
        ticket.setPurchaseAmount(40000);

        FraudDetection detect = new FraudDetection();
        detect.setUmbral(60);

        int score = detect.getScore(ticket);
        System.out.println(score);
        Assert.assertEquals(55, score, 0.1);

        boolean fraud = detect.isFraud(ticket);
        Assert.assertFalse(fraud);
    }
}
