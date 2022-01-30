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

import de.mixelblocks.coresystem.CoreSystemProvider;
import de.mixelblocks.coresystem.economy.EconomyPlayerData;
import de.mixelblocks.coresystem.economy.EconomyPlayerDataImpl;
import de.mixelblocks.coresystem.players.meta.PlayerMeta;
import de.mixelblocks.coresystem.players.meta.PlayerMetaImpl;
import de.mixelblocks.coresystem.players.namehistory.NameHistory;
import de.mixelblocks.coresystem.players.namehistory.NameHistoryImpl;
import de.mixelblocks.coresystem.players.options.PlayerOptions;
import de.mixelblocks.coresystem.players.options.PlayerOptionsImpl;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players:CachedPlayerImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CachedPlayerImpl implements CachedPlayer {

    private final CoreSystemProvider core;

    private final UUID uuid;

    private final String name;

    private final String displayName;

    private boolean online = false;

    private NameHistory nameHistory;
    private PlayerOptions options;
    private PlayerMeta meta;
    private EconomyPlayerData economyData;

    public CachedPlayerImpl(CoreSystemProvider core, UUID uuid, String name, String displayName) {
        this.core = core;
        this.uuid = uuid;
        this.name = name;
        this.displayName = displayName;
        this.nameHistory = new NameHistoryImpl(this, core);
        this.options = new PlayerOptionsImpl(this, core);
        this.meta = new PlayerMetaImpl(this, core);
        this.economyData = new EconomyPlayerDataImpl(this, core.economy());
    }


    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public String username() {
        return name;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public boolean online() {
        return online;
    }

    public void online(boolean online) {
        this.online = online;
    }

    @Override
    public NameHistory nameHistory() {
        return nameHistory;
    }

    @Override
    public PlayerOptions options() {
        return options;
    }

    @Override
    public PlayerMeta meta() {
        return meta;
    }

    @Override
    public EconomyPlayerData economyData() {
        return economyData;
    }
}
