package net.caseif.flint.iron.event.challenger;

import net.caseif.flint.challenger.Challenger;
import net.caseif.flint.event.challenger.ChallengerEvent;
import net.caseif.flint.iron.event.IronEvent;

public class IronChallengerEvent extends IronEvent implements ChallengerEvent {

    private Challenger challenger;

    public IronChallengerEvent(Challenger challenger) {
        super(challenger.getMinigame());
        this.challenger = challenger;
    }

    @Override
    public Challenger getChallenger() {
        return this.challenger;
    }
}
