package org.demo.people.flight.business;

public class FlightDayRule extends FligthRule {

    public FlightDayRule() {
        setScore(30);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        return false;
    }
}
