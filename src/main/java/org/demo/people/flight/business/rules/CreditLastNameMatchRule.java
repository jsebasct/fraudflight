package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;

import java.util.Arrays;

public class CreditLastNameMatchRule extends FligthRule {

    public CreditLastNameMatchRule() {
        setScore(20);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {

        String lastNameHolder = ticket.getCreditCard().getHolder().getLastName();
        boolean match = Arrays.stream(ticket.getPassengers()).anyMatch(pass -> pass.getLastName().equals(lastNameHolder));
        return !match;

    }
}
