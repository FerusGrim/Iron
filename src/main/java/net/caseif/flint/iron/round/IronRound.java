/*
 * New BSD License (BSD-new)
 *
 * Copyright (c) 2015 Maxim Roncacé
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     - Neither the name of the copyright holder nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
