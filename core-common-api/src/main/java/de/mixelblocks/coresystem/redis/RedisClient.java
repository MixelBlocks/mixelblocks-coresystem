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

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.redis:RedisClient
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class RedisClient {

    public static final String session = UUID.randomUUID().toString(), splitter = "\\:-:/", server = "default";

    private final Jedis jedisClient;
    private final RedisPubSubListener pubSubListener;

    public RedisClient(HostAndPort hostAndPort) {
        this.jedisClient = new Jedis(hostAndPort);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(HostAndPort hostAndPort, String authentication) {
        this.jedisClient = new Jedis(hostAndPort);
        this.jedisClient.auth(authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(HostAndPort hostAndPort, String user, String authentication) {
        this.jedisClient = new Jedis(hostAndPort);
        this.jedisClient.auth(user, authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String host, int port) {
        this.jedisClient = new Jedis(host, port);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String host, int port, String authentication) {
        this.jedisClient = new Jedis(host, port);
        this.jedisClient.auth(authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String host, int port, String user, String authentication) {
        this.jedisClient = new Jedis(host, port);
        this.jedisClient.auth(user, authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String connectionString) {
        this.jedisClient = new Jedis(connectionString);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String connectionString, String authentication) {
        this.jedisClient = new Jedis(connectionString);
        this.jedisClient.auth(authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }
    public RedisClient(String connectionString, String user, String authentication) {
        this.jedisClient = new Jedis(connectionString);
        this.jedisClient.auth(user, authentication);
        this.pubSubListener = new RedisPubSubListener();
        recheckConnection();
    }

    public static RedisClient fromConfig(RedisConfig config) {
        if(config.getUser() == null && config.getAuthentication() == null) {
            return new RedisClient(config.getConnection());
        } else if(config.getUser() == null) {
            return new RedisClient(config.getConnection(), config.getAuthentication());
        } else return new RedisClient(config.getConnection());
    }

    public void recheckConnection() {
        if(!this.jedisClient.isConnected()) this.jedisClient.connect();
    }

    public void sendMessage(String channel, String message) {
        this.jedisClient.publish(channel, session + splitter + message);
    }

    public RedisPubSubListener pubSubListener() {
        return pubSubListener;
    }

    /**
     * Set a value to a key ( No Expiry )
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        recheckConnection();
        return this.jedisClient.set(key, value);
    }

    /**
     * Set a value to a key ( Secondy Expiry )
     * @param seconds
     * @param key
     * @param value
     * @return
     */
    public String setEx(long seconds, String key, String value) {
        recheckConnection();
        return this.jedisClient.setex(key, seconds, value);
    }

    /**
     * Get a value by a key
     * @param key
     * @param defaultValue
     * @return value - the value which is present for a key ( If not existing or expired returns value )
     */
    public String get(String key, String defaultValue) {
        recheckConnection();
        String value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Get a value by a key
     * @param key
     * @return value - the value which is present for a key ( If not existing or expired returns null )
     */
    public String get(String key) {
        recheckConnection();
        return this.jedisClient.get(key);
    }

}
