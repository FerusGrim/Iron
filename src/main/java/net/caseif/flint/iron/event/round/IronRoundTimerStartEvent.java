package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundTimerStartEvent;
import net.caseif.flint.iron.event.IronCancellable;
import net.caseif.flint.round.Round;

public class IronRoundTimerStartEvent extends IronRoundEvent implements RoundTimerStartEvent, IronCancellable {

    private boolean cancelled = false;

    public IronRoundTimerStartEvent(Round round) {
        super(round);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
