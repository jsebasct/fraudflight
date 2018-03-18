package org.demo.people.flight.business;

import java.util.Arrays;
import java.util.stream.Stream;

public class CreditLastNameMatchRule extends FligthRule {
    public CreditLastNameMatchRule() {
        setScore(20);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {

        String lastNameHolder = ticket.getCreditCard().getHolder().getLastName();
        boolean match = Arrays.stream(ticket.getPassengers()).anyMatch(pass -> pass.getLastName().equals(lastNameHolder));
        return !match;
//        boolean coincedence = false;
//
//        for (Passenger passenger : ticket.getPassengers()) {
//            if (passenger.getLastName().equals(lastNameHolder)) {
//                coincedence = true;
//                break;
//            }
//        }
//        return !coincedence;



    }
}
