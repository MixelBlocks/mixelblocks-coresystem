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
package de.mixelblocks.coresystem;

import de.mixelblocks.coresystem.economy.EconomySystemProvider;
import de.mixelblocks.coresystem.locations.CachedLocationProvider;
import de.mixelblocks.coresystem.luckperms.PermissionProvider;
import de.mixelblocks.coresystem.players.CorePlayerProvider;
import de.mixelblocks.coresystem.redis.RedisProvider;
import de.mixelblocks.coresystem.redis.messaging.MessagingProvider;
import de.mixelblocks.coresystem.teleport.homes.HomeSystemProvider;
import de.mixelblocks.coresystem.teleport.warps.WarpSystemProvider;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem:BukkitCore
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface BukkitCore {

    CoreSystemProvider coreSystem();

    default CachedLocationProvider cachedLocationProvider() {
        return coreSystem().locations();
    }

    default EconomySystemProvider economySystemProvider() {
        return coreSystem().economy();
    }

    default CorePlayerProvider corePlayerProvider() {
        return coreSystem().players();
    }

    default PermissionProvider permissionProvider() {
        return coreSystem().perms();
    }

    default HomeSystemProvider homeSystemProvider() {
        return coreSystem().homes();
    }

    default WarpSystemProvider warpSystemProvider() {
        return coreSystem().warps();
    }

    default MessagingProvider messagingProvider() {
        return coreSystem().messaging();
    }

    default RedisProvider redisProvider() {
        return coreSystem().redis();
    }

}