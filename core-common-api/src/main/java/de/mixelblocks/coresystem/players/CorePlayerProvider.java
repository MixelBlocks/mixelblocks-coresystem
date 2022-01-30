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

import java.util.Collection;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players:CorePlayerProvider
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface CorePlayerProvider {

    CachedPlayer cached(UUID uuid);
    CachedPlayer cached(String name);
    Collection<CachedPlayer> allCached();

    CoreOfflinePlayer offline(UUID uuid);
    CoreOfflinePlayer offline(String name);

    CorePlayer online(UUID uuid);
    CorePlayer online(String name);
    Collection<CorePlayer> allOnline();

    String getName(UUID uuid);
    UUID getUniqueId(String name);

    boolean isPlayerLoaded(UUID uuid);
    boolean isPlayerLoaded(String name);
    boolean existPlayerByUniqueId(UUID uuid);
    boolean existPlayerByName(String name);

    int playerCount(boolean online);

}