package net.caseif.flint.iron;

import com.google.common.base.Optional;
import net.caseif.flint.Minigame;
import net.caseif.flint.iron.event.IronEvent;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

public final class Iron {

    public static final String ID = "Iron";
    public static final String VERSION = "1.0.0-SNAPSHOT";

    public static IronMod ironModUnsafe;

    public static boolean isPresent() {
        if (Iron.ironModUnsafe != null) {
            return true;
        }
        throw new IllegalStateException("The IronMod singleton hasn't been defined!");
    }

    public static void setIronMod(PluginContainer container, Game game, Logger logger, File configDir) {
        if (Iron.isPresent()) {
            throw new IllegalStateException("The IronMod singleton has already been defined!");
        }
        Iron.ironModUnsafe = new IronMod(container, game, logger, configDir);
    }

    public static Optional<IronMod> getIronMod() {
        return Iron.isPresent() ?
                Optional.<IronMod>absent() :
                Optional.of(Iron.ironModUnsafe);
    }

    public static Optional<Game> getGame() {
        return Iron.isPresent() ?
                Optional.<Game>absent() :
                Optional.of(Iron.ironModUnsafe.getGame());
    }

    public static Optional<Logger> getLogger() {
        return Iron.isPresent() ?
                Optional.<Logger>absent() :
                Optional.of(Iron.ironModUnsafe.getLogger());
    }

    public static Optional<File> getConfigDir() {
        return Iron.isPresent() ?
                Optional.<File>absent() :
                Optional.of(Iron.ironModUnsafe.getConfigDir());
    }

    public static Optional<File> getConfigDir(Minigame minigame) {
        return Iron.isPresent() ?
                Optional.<File>absent() :
                Optional.of(((IronPlugin) ((IronMinigame) minigame).getContainer().getInstance()).getConfigDir());
    }

    public static void callEvent(IronEvent event) {
        Iron.getGame().get().getEventManager().post(event);
    }
}
