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
package de.mixelblocks.coresystem.locationa;

import de.mixelblocks.coresystem.locations.CachedLocation;
import de.mixelblocks.coresystem.locations.Location;
import de.mixelblocks.coresystem.redis.RedisClient;
import org.jetbrains.annotations.NotNull;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.locationa:CachedLocationImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CachedLocationImpl implements CachedLocation {

    private Location location;
    private RedisClient redis;

    public CachedLocationImpl(@NotNull Location location, @NotNull RedisClient redis) {
        this.location = location;
        this.redis = redis;
    }

    @Override
    public void reload() {
        location = Location.fromString(redis.get("locations." + name()));
    }

    @Override
    public void save() {
        redis.set("locations." + name(), Location.toString(location));
    }

    @Override
    public void delete() {
        redis.setEx(-1, "locations." + name(), null);
    }

    @Override
    public void edit(Location newLocation) {
        this.location = newLocation;
    }

    @Override
    public String name() {
        return location.name();
    }

    @Override
    public String worldName() {
        return location.worldName();
    }

    @Override
    public int getX() {
        return location.getX();
    }

    @Override
    public int getY() {
        return location.getY();
    }

    @Override
    public int getZ() {
        return location.getZ();
    }

    @Override
    public double getXExact() {
        return location.getXExact();
    }

    @Override
    public double getYExact() {
        return location.getYExact();
    }

    @Override
    public double getZExact() {
        return location.getZExact();
    }

    @Override
    public float getYaw() {
        return location.getYaw();
    }

    @Override
    public float getPitch() {
        return location.getPitch();
    }
}
