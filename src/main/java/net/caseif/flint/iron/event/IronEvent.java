package net.caseif.flint.iron.event;

import net.caseif.flint.Minigame;
import net.caseif.flint.event.FlintEvent;
import org.spongepowered.api.event.AbstractEvent;

public class IronEvent extends AbstractEvent implements FlintEvent {

    private final Minigame minigame;

    protected IronEvent(Minigame minigame) {
        this.minigame = minigame;
    }

    @Override
    public Minigame getMinigame() {
        return this.minigame;
    }

    @Override
    public String getPlugin() {
        return this.minigame.getPlugin();
    }
}
