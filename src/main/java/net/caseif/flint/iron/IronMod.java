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
