package net.caseif.flint.iron.event.round;

import net.caseif.flint.event.round.RoundChangeLifecycleStageEvent;
import net.caseif.flint.round.LifecycleStage;
import net.caseif.flint.round.Round;

public class IronRoundChangeLifecycleStageEvent extends IronRoundEvent implements RoundChangeLifecycleStageEvent {

    private final LifecycleStage before;
    private final LifecycleStage after;

    protected IronRoundChangeLifecycleStageEvent(Round round, LifecycleStage before, LifecycleStage after) {
        super(round);
        this.before = before;
        this.after = after;
    }

    @Override
    public LifecycleStage getStageBefore() {
        return this.before;
    }

    @Override
    public LifecycleStage getStageAfter() {
        return this.after;
    }
}
