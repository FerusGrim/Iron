package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundTimerChangeEvent;
import net.caseif.flint.round.Round;

public class IronRoundTimerChangeEvent extends IronRoundEvent implements RoundTimerChangeEvent {

    private final long oldTime, newTime;

    private boolean cancelled = false;

    public IronRoundTimerChangeEvent(Round round, long oldTime, long newTime) {
        super(round);
        this.oldTime = oldTime;
        this.newTime = newTime;
    }

    @Override
    public long getOldTime() {
        return this.oldTime;
    }

    @Override
    public long getNewTime() {
        return this.newTime;
    }
}
