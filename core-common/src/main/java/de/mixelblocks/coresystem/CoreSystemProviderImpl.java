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
import de.mixelblocks.coresystem.mongodb.MongoDatabaseHandler;
import de.mixelblocks.coresystem.players.CorePlayerProvider;
import de.mixelblocks.coresystem.redis.RedisProvider;
import de.mixelblocks.coresystem.teleport.homes.HomeSystemProvider;
import de.mixelblocks.coresystem.teleport.warps.WarpSystemProvider;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem:CoreSystemProviderImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CoreSystemProviderImpl implements CoreSystemProvider {

    private final RedisProvider redis;
    private final EconomySystemProvider economy;
    private final PermissionProvider perms;
    private final CachedLocationProvider locations;
    private final HomeSystemProvider homes;
    private final WarpSystemProvider warps;
    private final CorePlayerProvider players;

    private final MongoDatabaseHandler defaultDatabase;

    public CoreSystemProviderImpl(
            MongoDatabaseHandler defaultDatabase, RedisProvider redis,
            EconomySystemProvider economy, CorePlayerProvider players,
            PermissionProvider perms, CachedLocationProvider locations,
            HomeSystemProvider homes, WarpSystemProvider warps
    ) {
        this.defaultDatabase = defaultDatabase;
        this.redis = redis;
        this.economy = economy;
        this.players = players;
        this.perms = perms;
        this.locations = locations;
        this.homes = homes;
        this.warps = warps;
    }

    @Override
    public RedisProvider redis() {
        return redis;
    }

    @Override
    public EconomySystemProvider economy() {
        return economy;
    }

    @Override
    public PermissionProvider perms() {
        return perms;
    }

    @Override
    public CachedLocationProvider locations() {
        return locations;
    }

    @Override
    public HomeSystemProvider homes() {
        return homes;
    }

    @Override
    public WarpSystemProvider warps() {
        return warps;
    }

    @Override
    public CorePlayerProvider players() {
        return players;
    }

    @Override
    public MongoDatabaseHandler db() {
        return defaultDatabase;
    }
}
