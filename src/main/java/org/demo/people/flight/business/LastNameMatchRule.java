package org.demo.people.flight.business;

public class LastNameMatchRule extends FligthRule {

    public LastNameMatchRule() {
        setScore(25);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        //TODO refactorizar passengers evalueate last name should be done in ticket ?

        String lastNameTemplate = "";
        Passenger[] passengers = ticket.getPassengers();
        if (passengers.length >= 1) {
            lastNameTemplate = passengers[0].getLastName();
        }
        if (passengers.length > 1) {
            for (Passenger p : passengers) {
                if (!p.getLastName().equals(lastNameTemplate)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
