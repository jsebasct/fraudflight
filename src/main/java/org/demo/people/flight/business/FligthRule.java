package org.demo.people.flight.business;

public abstract class FligthRule {

    private int score;
    private boolean enabled;

    public FligthRule() {
        this.enabled = true;
    }

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
