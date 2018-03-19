package org.demo.people.flight.business;

import org.demo.people.flight.business.rules.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class FraudDetector {

//    private List<FligthRule> rules;
    private Map<String, FligthRule> rules;
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

//        rules.put(CardBlackListRule.getClass().getSimpleName(), new CardBlackListRule());
//        rules.add(new CountryRedListRule());
//        rules.add(new FlightDayRule());
//        rules.add(new LastNameMatchRule());
//        rules.add(new CreditLastNameMatchRule());
//        rules.add(new PurchaseLimitRule());

        this.addRule(new CardBlackListRule());
        this.addRule(new CountryRedListRule());
        this.addRule(new FlightDayRule());
        this.addRule(new LastNameMatchRule());
        this.addRule(new CreditLastNameMatchRule());
        this.addRule(new PurchaseLimitRule());

//        rules.add(new CardBlackListRule());
//        rules.add(new CountryRedListRule());
//        rules.add(new FlightDayRule());
//        rules.add(new LastNameMatchRule());
//        rules.add(new CreditLastNameMatchRule());
//        rules.add(new PurchaseLimitRule());
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

//    public List<FligthRule> getRules() {
//        return rules;
//    }
//
//    public void setRules(List<FligthRule> rules) {
//        this.rules = rules;
//    }


    //TODO should generate a copy before
    public Map<String, FligthRule> getRules() {
        return rules;
    }

    public void setRules(Map<String, FligthRule> rules) {
        this.rules = rules;
    }

    public static void main(String[] args) {
        new FraudDetector();
    }

    //TODO should return a copy
    public FligthRule disableRule(String ruleName) {
        FligthRule ruleDisabled = rules.get(ruleName);
        ruleDisabled.setEnabled(false);
        return ruleDisabled;
    }
}
