package org.demo.people.flight.business;

import org.demo.people.flight.business.CreditCard;
import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.FraudDetection;
import org.demo.people.flight.business.Person;
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
    public void testCheckFlyImportRule() {
        FligthRule r1 = new PurchaseLimitRule();
        FligthRule[] flights = {r1};

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCard(creditCard);
        ticket.setDestinationCity("Guinea");
        ticket.setFlyDate(LocalDate.now());
        ticket.setPassengers(passengers);
        ticket.setPurchaseAmount(60000);

        FraudDetection detect = new FraudDetection();
//        detect.setFlyTicket(ticket);

        int score = detect.getScore(ticket);



        Assert.assertEquals(230, score, 0.1);
    }
}
