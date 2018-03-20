package org.demo.people.flight.business;

import org.demo.people.flight.business.rules.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class FraudDetector {

    //TODO remove transient
    private transient Map<String, FligthRule> rules;
    private int umbral;

    public FraudDetector() {
        rules = new HashMap<>();
        loadDefaultRules();
    }

    public void addRule(FligthRule rule) {
        System.out.println( "Key: " +  rule.getClass().getSimpleName() );
        rules.put(rule.getClass().getSimpleName(), rule);
    }

    private void loadDefaultRules() {

        this.addRule(new CardBlackListRule());
        this.addRule(new CountryRedListRule());
        this.addRule(new FlightDayRule());
        this.addRule(new LastNameMatchRule());
        this.addRule(new CreditLastNameMatchRule());
        this.addRule(new PurchaseLimitRule());
    }

    public int getScore(FlyTicket ticket) {
        int res = 0;

        for (Map.Entry<String, FligthRule> ruleMap : rules.entrySet()) {
            FligthRule rule = ruleMap.getValue();
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





    //TODO PENDING should generate a copy before
    public Map<String, FligthRule> getRules() {
//        Map<String, FligthRule> mapCopy = rules.entrySet()
//                .stream()
//                .collect(Collectors.toMap(e -> e.getKey(), e -> new FligthRule(e.getValue())));
//        return mapCopy;
        return new HashMap<>(rules);
    }

    public void setRules(Map<String, FligthRule> rules) {
        this.rules = rules;
    }

    public boolean allRulesEnabled() {
        long count = rules.entrySet().stream()
                .filter(i->i.getValue().isEnabled())
                .count();
        return count == rules.size();
    }

    //TODO should return a copy
    public FligthRule disableRule(String ruleName) {
        FligthRule ruleDisabled = rules.get(ruleName);
        ruleDisabled.setEnabled(false);
        return ruleDisabled;
    }
}
