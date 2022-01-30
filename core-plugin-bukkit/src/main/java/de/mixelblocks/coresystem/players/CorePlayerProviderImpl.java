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

import de.mixelblocks.coresystem.CoreSystemBukkitPlugin;
import de.mixelblocks.coresystem.accounts.MinecraftAccountValidator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players:CorePlayerProviderImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CorePlayerProviderImpl implements CorePlayerProvider {

    private final CoreSystemBukkitPlugin plugin;

    private final Map<UUID, CachedPlayer> cachedPlayers;
    private final Map<UUID, CorePlayer> onlinePlayers;

    public CorePlayerProviderImpl(CoreSystemBukkitPlugin plugin) {
        this.plugin = plugin;
        this.cachedPlayers = new HashMap<>();
        this.onlinePlayers = new HashMap<>();
    }

    @Override
    public CachedPlayer cached(UUID uuid) {
        if(cachedPlayers.containsKey(uuid)) return cachedPlayers.get(uuid);
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if(offlinePlayer == null) return null;
        CachedPlayer player = new CachedPlayerImpl(plugin.getCoreSystemProvider(), uuid, offlinePlayer.getName(),
                offlinePlayer.isOnline() ? offlinePlayer.getName() : offlinePlayer.getPlayer().getDisplayName());
        cachedPlayers.put(uuid, player);
        return player;
    }

    @Override
    public CachedPlayer cached(String name) {
        for(CachedPlayer player : cachedPlayers.values()) {
            if(player.username() == name) return player;
        }
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(MinecraftAccountValidator.resolveUUID(name));
        if(offlinePlayer == null) return null;
        CachedPlayer player = new CachedPlayerImpl(plugin.getCoreSystemProvider(), offlinePlayer.getUniqueId(), offlinePlayer.getName(),
                offlinePlayer.isOnline() ? offlinePlayer.getName() : offlinePlayer.getPlayer().getDisplayName());
        cachedPlayers.put(offlinePlayer.getUniqueId(), player);
        return player;
    }

    @Override
    public Collection<CachedPlayer> allCached() {
        return cachedPlayers.values();
    }

    @Override
    public CoreOfflinePlayer offline(UUID uuid) {
        return new CoreOfflinePlayerImpl(cached(uuid));
    }

    @Override
    public CoreOfflinePlayer offline(String name) {
        return new CoreOfflinePlayerImpl(cached(name));
    }

    @Override
    public CorePlayer online(UUID uuid) {
        return onlinePlayers.get(uuid);
    }

    @Override
    public CorePlayer online(String name) {
        for(CorePlayer player : onlinePlayers.values()) {
            if(player.username() == name) return player;
        }
        return null;
    }

    @Override
    public Collection<CorePlayer> allOnline() {
        return onlinePlayers.values();
    }

    @Override
    public String getName(UUID uuid) {
        return offline(uuid).username();
    }

    @Override
    public UUID getUniqueId(String name) {
        return offline(name).uuid();
    }

    @Override
    public boolean isPlayerLoaded(UUID uuid) {
        return cachedPlayers.containsKey(uuid);
    }

    @Override
    public boolean isPlayerLoaded(String name) {
        for(CachedPlayer player : cachedPlayers.values()) {
            if(player.username() == name) return true;
        }
        return false;
    }

    @Override
    public boolean existPlayerByUniqueId(UUID uuid) {
        return cached(uuid) != null;
    }

    @Override
    public boolean existPlayerByName(String name) {
        return cached(name) != null;
    }

    @Override
    public int playerCount(boolean online) {
        return online ? Bukkit.getOnlinePlayers().size() : cachedPlayers.size();
    }
}
