package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundEndEvent;
import net.caseif.flint.round.Round;

public class IronRoundEndEvent extends IronRoundEvent implements RoundEndEvent {

    private final boolean natural;

    protected IronRoundEndEvent(Round round, boolean natural) {
        super(round);
        this.natural = natural;
    }

    @Override
    public boolean isNatural() {
        return this.natural;
    }
}
