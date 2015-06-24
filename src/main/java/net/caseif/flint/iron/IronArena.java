/*
 * New BSD License (BSD-new)
 *
 * Copyright (c) 2015 Maxim Roncac�
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
