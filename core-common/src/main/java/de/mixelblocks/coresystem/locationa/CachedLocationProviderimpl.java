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
import de.mixelblocks.coresystem.locations.CachedLocationProvider;
import de.mixelblocks.coresystem.redis.RedisClient;

import java.util.Collection;
import java.util.Map;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.locationa:CachedLocationProviderimpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CachedLocationProviderimpl implements CachedLocationProvider {

    public CachedLocationProviderimpl(RedisClient redis) {
    }

    @Override
    public CachedLocation location(String name) {
        return null;
    }

    @Override
    public Collection<CachedLocation> cachedLocations() {
        return null;
    }

    @Override
    public Map<String, CachedLocation> locationCache() {
        return null;
    }
}
