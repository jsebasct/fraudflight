package org.demo.people.flight.business;

public class PurchaseLimitRule extends FligthRule {

    private static final int LIMIT = 50000;

    public PurchaseLimitRule() {
        setScore(15);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        return ticket.getPurchaseAmount() > LIMIT;
    }
}
