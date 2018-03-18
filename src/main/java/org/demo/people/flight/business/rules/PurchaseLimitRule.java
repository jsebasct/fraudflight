package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.rules.FligthRule;

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
