package net.caseif.flint.iron;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.config.ConfigDir;

import java.io.File;

@Plugin(id = Iron.ID, name = Iron.ID, version = Iron.VERSION)
public class IronMain {

    @Inject private PluginContainer container;
    @Inject private Game game;
    @Inject private Logger logger;
    @Inject @ConfigDir(sharedRoot = false) private File configDir;

    @Subscribe public void onInit(InitializationEvent ignored) {
        Iron.setIronMod(this.container, this.game, this.logger, this.configDir);
    }
}
