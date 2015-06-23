package net.caseif.flint.iron;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

public interface IronPlugin {

    PluginContainer getContainer();

    Game getGame();

    Logger getLogger();

    File getConfigDir();
}
