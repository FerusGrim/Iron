package net.caseif.flint.iron;

import com.google.common.collect.ImmutableSet;
import net.caseif.flint.common.CommonArena;
import net.caseif.flint.common.CommonMinigame;
import net.caseif.flint.iron.round.IronRound;
import net.caseif.flint.round.LifecycleStage;
import net.caseif.flint.round.Round;
import net.caseif.flint.util.physical.Location3D;

public class IronArena extends CommonArena {

    public IronArena(CommonMinigame parent, String id, String name, Location3D initialSpawn) {
        super(parent, id, name, initialSpawn);
    }

    @Override
    public Round createRound(ImmutableSet<LifecycleStage> immutableSet) throws UnsupportedOperationException {
        if (this.parent.getRoundMap().containsKey(this)) {
            throw new UnsupportedOperationException("Round already exists in arena \"" + this.getName() + "\"");
        }
        this.parent.getRoundMap().put(this, new IronRound(this));
        assert this.getRound().isPresent();
        return this.getRound().get();
    }
}
