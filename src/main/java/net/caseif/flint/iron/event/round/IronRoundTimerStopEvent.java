package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundTimerStopEvent;
import net.caseif.flint.iron.event.IronCancellable;
import net.caseif.flint.round.Round;

public class IronRoundTimerStopEvent extends IronRoundEvent implements RoundTimerStopEvent, IronCancellable {

    private boolean cancelled = false;

    public IronRoundTimerStopEvent(Round round) {
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
