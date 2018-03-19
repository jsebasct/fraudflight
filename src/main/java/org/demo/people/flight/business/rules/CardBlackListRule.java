package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;

import java.util.HashSet;
import java.util.Set;

public class CardBlackListRule extends FligthRule {

    private Set<Long> blackList;

    public CardBlackListRule() {
        this.setScore(100);
        blackList = new HashSet<>();

        initBlackList();
    }

    private void initBlackList() {
        blackList.add(1234_5678_9012_3456L);
        blackList.add(1789_5678_9012_3456L);
        blackList.add(1012_5678_9012_3456L);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        return blackList.contains(ticket.getCreditCard().getCreditCardNumber());
    }
}
