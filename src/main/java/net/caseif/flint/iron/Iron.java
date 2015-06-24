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
