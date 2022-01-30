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
package de.mixelblocks.coresystem.redis.messaging;

import de.mixelblocks.coresystem.redis.RedisClient;
import de.mixelblocks.coresystem.serialization.SerializableSerializer;

import java.io.Serializable;
import java.util.Collection;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.redis.messaging:MessagingProvider
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface MessagingProvider {

    RedisClient redis();

    Collection<MessagingChannel> messagingChannels();

    MessagingChannel channel(String name);

    void channel(MessagingChannel channel);

    default Object deserialize(String serializable) {
        return SerializableSerializer.deserialize(serializable);
    }

    default String serialize(Serializable serializable) {
        return SerializableSerializer.serialize(serializable);
    }

    static Object deserialized(String serializable) {
        return SerializableSerializer.deserialize(serializable);
    }

    static String serialized(Serializable serializable) {
        return SerializableSerializer.serialize(serializable);
    }

}