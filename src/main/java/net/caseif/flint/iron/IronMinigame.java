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
