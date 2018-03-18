package org.demo.people.flight.business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FlightDayRule extends FligthRule {

    public FlightDayRule() {
        setScore(30);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        long hoursUntil = ticket.getFlyDate().until(LocalDate.now(), ChronoUnit.DAYS);
        if (hoursUntil <= 1) {
            return true;
        }
        return false;
    }
}
