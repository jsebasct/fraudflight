package org.demo.people.flight.business;

import org.demo.people.flight.business.rules.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FraudDetector {

    private List<FligthRule> rules;
    private int umbral;

    public FraudDetector() {
        rules = new ArrayList<>();
        loadDefaultRules();
    }

    private void loadDefaultRules() {

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
                System.out.println(rule.getClass().getSimpleName() + " - " + rule.evaluate(ticket));
                res += rule.getScore();
            }
        }

        return res;
    }

    public boolean isFraud(FlyTicket ticket) {
        return getScore(ticket) >= getUmbral();
    }

    public int getUmbral() {
        return umbral;
    }

    public void setUmbral(int umbral) {
        this.umbral = umbral;
    }
}
