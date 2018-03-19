package org.demo.people.flight.business.rules;

import org.demo.people.flight.business.FlyTicket;

public abstract class FligthRule {

    private int score;
    private boolean enabled;

    public FligthRule() {
        this.enabled = true;
    }

//    public FligthRule(FligthRule rule) {
//        score = rule.getScore();
//        enabled = rule.isEnabled();
//    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract boolean evaluate(FlyTicket ticket);
}
