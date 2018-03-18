package org.demo.people.flight.business;

import org.demo.people.flight.business.rules.*;

import java.util.ArrayList;
import java.util.List;

public class FraudDetection {

    private List<FligthRule> rules;

    public FraudDetection() {
        rules = new ArrayList<>();
        loadDefaultRules();
    }

    private void loadDefaultRules() {
//        FligthRule r1 = new CardBlackListRule();
//        FligthRule r2 = new CountryRedListRule();
//        FligthRule r3 = new FlightDayRule();
//        FligthRule r4 = new LastNameMatchRule();
//        FligthRule r5 = new CreditLastNameMatchRule();
//        FligthRule r6 = new PurchaseLimitRule();

        rules.add(new CardBlackListRule());
        rules.add(new CountryRedListRule());
        rules.add(new FlightDayRule());
        rules.add(new LastNameMatchRule());
        rules.add(new CreditLastNameMatchRule());
        rules.add(new PurchaseLimitRule());
    }

    public int getScore(FlyTicket ticket) {
        int res = 0;

        for (FligthRule rule : rules) {
            if (rule.isEnabled() && rule.evaluate(ticket)) {
//                System.out.println(rule.evaluate(ticket));
                res += rule.getScore();
            }
        }

        return res;
    }
}
