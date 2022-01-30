/*
 * mixelblocks-coresystem | Copyright (C) 2022 | mixelblocks.de | LuciferMorningstarDev
 * Licensed under the MIT License
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * You should have received a copy of the MIT License
 *
 * Repository:
 *     Github:          https://github.com/MixelBlccks/mixelblocks-coresystem
 *
 * Contact:
 *     Discord Server:  https://mixelblocks.de/discord
 *     Website:         https://mixelblocks.de/
 *     DashBoard:       https://dash.mixelblocks.de/
 *     Mail:            contact@mixelblocks.de
 *     Minecraft:       mixelblocks.de:25565
 *
 */
package de.mixelblocks.coresystem.players;

import de.mixelblocks.coresystem.economy.EconomyPlayerData;
import de.mixelblocks.coresystem.players.meta.PlayerMeta;
import de.mixelblocks.coresystem.players.namehistory.NameHistory;
import de.mixelblocks.coresystem.players.options.PlayerOptions;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players:CoreOfflinePlayerImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CoreOfflinePlayerImpl implements CoreOfflinePlayer {

    private final CachedPlayer cachedPlayer;

    public CoreOfflinePlayerImpl(CachedPlayer cachedPlayer) {
        this.cachedPlayer = cachedPlayer;
    }

    @Override
    public UUID uuid() {
        return cachedPlayer.uuid();
    }

    @Override
    public String username() {
        return cachedPlayer.username();
    }

    @Override
    public String displayName() {
        return cachedPlayer.displayName();
    }

    @Override
    public boolean online() {
        return cachedPlayer.online();
    }

    @Override
    public NameHistory nameHistory() {
        return cachedPlayer.nameHistory();
    }

    @Override
    public PlayerOptions options() {
        return cachedPlayer.options();
    }

    @Override
    public PlayerMeta meta() {
        return cachedPlayer.meta();
    }

    @Override
    public EconomyPlayerData economyData() {
        return cachedPlayer.economyData();
    }

    @Override
    public CachedPlayer cached() {
        return cachedPlayer;
    }
}
