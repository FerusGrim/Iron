package net.caseif.flint.iron.challenger;

import com.google.common.base.Optional;
import net.caseif.flint.common.challenger.CommonChallenger;
import net.caseif.flint.exception.round.RoundJoinException;
import net.caseif.flint.iron.Iron;
import net.caseif.flint.iron.event.challenger.IronChallengerJoinRoundEvent;
import net.caseif.flint.iron.round.IronRound;
import org.spongepowered.api.entity.player.Player;

import java.util.UUID;

public class IronChallenger extends CommonChallenger {

    public IronChallenger(UUID uuid, IronRound round) throws RoundJoinException {
        Optional<Player> player = Iron.getGame().get().getServer().getPlayer(uuid);
        if (!player.isPresent()) {
            throw new RoundJoinException(uuid, round, RoundJoinException.Reason.OFFLINE, "Player is offline!");
        }

        this.uuid = uuid;
        this.name = player.get().getName();
        this.round = round;

        IronChallengerJoinRoundEvent event = new IronChallengerJoinRoundEvent(this);
        Iron.callEvent(event);

        if (event.isCancelled()) {
            throw new RoundJoinException(uuid, round, RoundJoinException.Reason.CANCELLED, "Event was cancelled!");
        }

        this.round.getChallengerMap().put(uuid, this);
    }
}
