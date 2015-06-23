package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundEvent;
import net.caseif.flint.iron.event.IronEvent;
import net.caseif.flint.round.Round;

public class IronRoundEvent extends IronEvent implements RoundEvent {

    private final Round round;

    protected IronRoundEvent(Round round) {
        super(round.getMinigame());
        this.round = round;
    }

    @Override
    public Round getRound() {
        return this.round;
    }
}
