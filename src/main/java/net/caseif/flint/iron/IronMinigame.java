package net.caseif.flint.iron;

import net.caseif.flint.Arena;
import net.caseif.flint.common.CommonMinigame;
import net.caseif.flint.util.physical.Location3D;
import org.spongepowered.api.plugin.PluginContainer;

public class IronMinigame extends CommonMinigame {

    private final PluginContainer container;

    public IronMinigame(PluginContainer plugin) {
        this.container = plugin;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    public Object getPluginInstance() {
        return this.container.getInstance();
    }

    @Override
    public String getPlugin() {
        return this.container.getName();
    }

    @Override
    public Arena createArena(String arenaId, String name, Location3D spawnPoint) throws IllegalArgumentException {
        if (this.arenas.containsKey(arenaId)) {
            throw new IllegalArgumentException("Arena with ID \"" + arenaId + "\" already exists!");
        }
        return new IronArena(this, arenaId, name, spawnPoint);
    }

    @Override
    public Arena createArena(String arenaId, Location3D spawnPoint) throws IllegalArgumentException {
        return this.createArena(arenaId, arenaId, spawnPoint);
    }
}
