package net.caseif.flint.iron.event.challenger;

import net.caseif.flint.challenger.Challenger;
import net.caseif.flint.event.challenger.ChallengerLeaveRoundEvent;
import net.caseif.flint.iron.event.IronCancellable;

public class IronChallengerLeaveRoundEvent extends IronChallengerEvent implements ChallengerLeaveRoundEvent, IronCancellable {

    private boolean cancelled;

    public IronChallengerLeaveRoundEvent(Challenger challenger) {
        super(challenger);
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
