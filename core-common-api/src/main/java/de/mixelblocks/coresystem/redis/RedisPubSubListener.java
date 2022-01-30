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
package de.mixelblocks.coresystem.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.redis:RedisPubSubListener
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class RedisPubSubListener extends JedisPubSub {

    private final List<Consumer<String>> onPongHandlers;

    private final List<BiConsumer<String, String>> onMessageHandlers;
    private final List<BiConsumer<String, Integer>> onSubscribeHandlers;
    private final List<BiConsumer<String, Integer>> onUnsubscribeHandlers;

    private final List<RedisTriConsumer<String, String, String, Boolean>> onPMessageHandlers;
    private final List<BiConsumer<String, Integer>> onPSubscribeHandlers;
    private final List<BiConsumer<String, Integer>> onPUnsubscribeHandlers;


    public RedisPubSubListener() {
        this.onPongHandlers = new ArrayList<>();

        this.onMessageHandlers = new ArrayList<>();
        this.onSubscribeHandlers = new ArrayList<>();
        this.onUnsubscribeHandlers = new ArrayList<>();

        this.onPMessageHandlers = new ArrayList<>();
        this.onPSubscribeHandlers = new ArrayList<>();
        this.onPUnsubscribeHandlers = new ArrayList<>();
    }

    @Override
    public void onPong(String pattern) {
        for(Consumer consumer : onPongHandlers) {
            try {
                consumer.accept(pattern);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onMessage(String channel, String message) {
        for(BiConsumer consumer : onMessageHandlers) {
            try {
                consumer.accept(channel, message);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        for(BiConsumer consumer : onSubscribeHandlers) {
            try {
                consumer.accept(channel, subscribedChannels);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        for(BiConsumer consumer : onUnsubscribeHandlers) {
            try {
                consumer.accept(channel, subscribedChannels);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        for(RedisTriConsumer<String, String, String, Boolean> consumer : onPMessageHandlers) {
            try {
                consumer.accept(pattern, channel, message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        for(BiConsumer consumer : onPSubscribeHandlers) {
            try {
                consumer.accept(pattern, subscribedChannels);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        for(BiConsumer consumer : onPUnsubscribeHandlers) {
            try {
                consumer.accept(pattern, subscribedChannels);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Consumer<String>> pongHandlers() {
        return onPongHandlers;
    }

    public List<BiConsumer<String, String>> messageHandlers() {
        return onMessageHandlers;
    }

    public List<BiConsumer<String, Integer>> subscribeHandlers() {
        return onSubscribeHandlers;
    }

    public List<BiConsumer<String, Integer>> unsubscribeHandlers() {
        return onUnsubscribeHandlers;
    }

    public List<RedisTriConsumer<String, String, String, Boolean>> pMessageHandlers() {
        return onPMessageHandlers;
    }

    public List<BiConsumer<String, Integer>> pSubscribeHandlers() {
        return onPSubscribeHandlers;
    }

    public List<BiConsumer<String, Integer>> pUnsubscribeHandlers() {
        return onPUnsubscribeHandlers;
    }

}
