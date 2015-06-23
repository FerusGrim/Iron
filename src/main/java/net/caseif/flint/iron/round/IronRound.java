package net.caseif.flint.iron.round;

import net.caseif.flint.challenger.Challenger;
import net.caseif.flint.common.CommonArena;
import net.caseif.flint.common.round.CommonRound;
import net.caseif.flint.exception.round.RoundJoinException;
import net.caseif.flint.iron.Iron;
import net.caseif.flint.iron.IronMinigame;
import net.caseif.flint.iron.challenger.IronChallenger;
import net.caseif.flint.iron.event.challenger.IronChallengerLeaveRoundEvent;
import net.caseif.flint.iron.event.round.IronRoundTimerChangeEvent;
import net.caseif.flint.iron.event.round.IronRoundTimerStartEvent;
import net.caseif.flint.iron.event.round.IronRoundTimerStopEvent;
import org.spongepowered.api.service.scheduler.Task;

import java.util.UUID;

public class IronRound extends CommonRound {

    private Task task = null;

    public IronRound(CommonArena arena) {
        super(arena);
    }

    @Override
    public Challenger addChallenger(UUID uuid) throws RoundJoinException {
        return new IronChallenger(uuid, this);
    }

    @Override
    public void removeChallenger(Challenger challenger) {
        IronChallengerLeaveRoundEvent event = new IronChallengerLeaveRoundEvent(challenger);
        Iron.callEvent(event);

        if (!event.isCancelled()) {
            super.removeChallenger(challenger);
        }
    }

    @Override
    public void startTimer() throws IllegalStateException {
        if (!this.isTimerTicking()) {
            IronRoundTimerStartEvent event = new IronRoundTimerStartEvent(this);
            Iron.callEvent(event);

            if (event.isCancelled()) {
                return;
            }

            this.task = Iron.getGame().get().getSyncScheduler().runRepeatingTask(
                    ((IronMinigame) this.getMinigame()).getPluginInstance(),
                    new RoundWorker(this),
                    20L
            ).get();
        }
    }

    @Override
    public void stopTimer() throws IllegalStateException {
        if (this.isTimerTicking()) {
            IronRoundTimerStopEvent event = new IronRoundTimerStopEvent(this);
            Iron.callEvent(event);

            if (!event.isCancelled()) {
                this.task.cancel();
            }
        }
    }

    @Override
    public boolean isTimerTicking() {
        return this.task != null;
    }

    @Override
    public void setTime(long time) {
        this.setTime(time, true);
    }

    public void setTime(long time, boolean callEvent) {
        if (callEvent) {
            IronRoundTimerChangeEvent event = new IronRoundTimerChangeEvent(this, this.getTime(), time);
            Iron.callEvent(event);
        }
        super.setTime(time);
    }
}
