package net.caseif.flint.iron.event.challenger;

import net.caseif.flint.challenger.Challenger;
import net.caseif.flint.event.challenger.ChallengerJoinRoundEvent;
import net.caseif.flint.iron.event.IronCancellable;

public class IronChallengerJoinRoundEvent extends IronChallengerEvent implements ChallengerJoinRoundEvent, IronCancellable {

    private boolean cancelled = false;

    public IronChallengerJoinRoundEvent(Challenger challenger) {
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
