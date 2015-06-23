package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundTimerTickEvent;
import net.caseif.flint.iron.round.IronRound;

public class IronRoundTimerTickEvent extends IronRoundTimerChangeEvent implements RoundTimerTickEvent {

    public IronRoundTimerTickEvent(IronRound round, long oldTime, long newTime) {
        super(round, oldTime, newTime);
    }
}
