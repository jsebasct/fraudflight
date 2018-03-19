package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.Person;

public class LastNameMatchRule extends FligthRule {

    public LastNameMatchRule() {
        setScore(25);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        boolean res = ticket.matchPassengersLastName();
        return !res;
    }
}
