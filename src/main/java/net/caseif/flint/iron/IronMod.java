package net.caseif.flint.iron;

import net.caseif.flint.Minigame;
import net.caseif.flint.common.CommonCore;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

public class IronMod extends CommonCore implements IronPlugin {

    private final PluginContainer container;
    private final Game game;
    private final Logger logger;
    private final File configDir;

    public IronMod(PluginContainer container, Game game, Logger logger, File configDir) {
        this.container = container;
        this.game = game;
        this.logger = logger;
        this.configDir = configDir;
    }

    @Override
    public PluginContainer getContainer() {
        return this.container;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public File getConfigDir() {
        return this.configDir;
    }

    @Override
    public Minigame registerPlugin(String pluginId) throws IllegalArgumentException {
        if (this.minigames.containsKey(pluginId)) {
            throw new IllegalArgumentException(pluginId + " attempted to register itself more than once.");
        }

        Minigame minigame = new IronMinigame(this.container);
        this.minigames.put(pluginId, minigame);
        return minigame;
    }
}
