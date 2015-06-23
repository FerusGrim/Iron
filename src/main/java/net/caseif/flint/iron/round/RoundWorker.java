package net.caseif.flint.iron.round;

import com.google.common.base.Optional;
import net.caseif.flint.iron.Iron;
import net.caseif.flint.iron.event.round.IronRoundTimerTickEvent;
import net.caseif.flint.round.LifecycleStage;

public class RoundWorker implements Runnable {

    private final IronRound round;

    public RoundWorker(IronRound round) {
        this.round = round;
    }

    public void run() {
        boolean stageSwitch = this.round.getTime() >= this.round.getLifecycleStage().getDuration();
        IronRoundTimerTickEvent event = new IronRoundTimerTickEvent(this.round, this.round.getTime(),
                stageSwitch ? 0 : this.round.getTime() + 1);
        Iron.callEvent(event);
        if (stageSwitch) {
            Optional<LifecycleStage> nextStage = this.round.getNextLifecycleStage();
            if (nextStage.isPresent()) {
                this.round.setTime(0);
                this.round.setLifecycleStage(nextStage.get());
            } else {
                this.round.end();
            }
        } else {
            this.round.setTime(this.round.getTime() + 1, false);
        }
    }
}
