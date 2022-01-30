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
package de.mixelblocks.coresystem.locations;

import de.mixelblocks.coresystem.redis.RedisClient;
import de.mixelblocks.coresystem.serialization.SerializableSerializer;

import java.io.Serializable;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.locations:Location
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface Location extends Serializable {
    String name();
    String worldName();
    int getX();
    int getY();
    int getZ();
    double getXExact();
    double getYExact();
    double getZExact();
    float getYaw();
    float getPitch();

    class Impl implements Location {

        private String name;
        private String worldName;
        private double x;
        private double y;
        private double z;

        private float yaw;
        private float pitch;

        public Impl(String name, String worldName, double x, double y, double z) {
            this.name = name;
            this.worldName = worldName;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = 0f;
            this.pitch = 0f;
        }

        public Impl(String name, String worldName, double x, double y, double z, float yaw,float pitch) {
            this.name = name;
            this.worldName = worldName;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String worldName() {
            return worldName;
        }

        @Override
        public int getX() {
            return (int) x;
        }

        @Override
        public int getY() {
            return (int) y;
        }

        @Override
        public int getZ() {
            return (int) z;
        }

        @Override
        public double getXExact() {
            return x;
        }

        @Override
        public double getYExact() {
            return y;
        }

        @Override
        public double getZExact() {
            return z;
        }

        @Override
        public float getYaw() {
            return yaw;
        }

        @Override
        public float getPitch() {
            return pitch;
        }
    }

    static Location newLocation(String name, String world, double x, double y, double z, float yaw, float pitch) {
        return new Impl(name, world, x, y, z, yaw, pitch);
    }

    static Location newLocation(String name, String world, double x, double y, double z) {
        return new Impl(name, world, x, y, z, 0, 0);
    }

    static String toString(Location location) {
        try {
            return SerializableSerializer.serialize(location);
        } catch(Exception ex) {
            return null;
        }
    }

    static Location fromString(String location) {
        try {
            return (Location) SerializableSerializer.deserialize(location);
        } catch(Exception ex) {
            return null;
        }
    }

    static Location load(RedisClient redisClient, String name) {
        try {
            return fromString(redisClient.get("locations." + name));
        } catch(Exception ex) {
            return null;
        }
    }

}