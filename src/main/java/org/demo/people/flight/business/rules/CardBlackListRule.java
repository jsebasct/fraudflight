package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;

import java.util.ArrayList;
import java.util.List;

public class CardBlackListRule extends FligthRule {

    //TODO since list allows duplicated elements its ok to use this DS to hold my cards ?
    private List<Long> blackList;

    public CardBlackListRule() {
        this.setScore(100);
        blackList = new ArrayList<>();
        //TODO esta bueno llamar a un constructor en el constructor ?
        initBlackList();
    }

    private void initBlackList() {
        blackList.add(1234_5678_9012_3456L);
        blackList.add(1789_5678_9012_3456L);
        blackList.add(1012_5678_9012_3456L);
    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        //TODO use lambdas
        for (Long stole : blackList) {
            if (stole.equals(ticket.getCreditCard().getCreditCardNumber())) {
                return true;
            }
        }
        return false;
    }
}
