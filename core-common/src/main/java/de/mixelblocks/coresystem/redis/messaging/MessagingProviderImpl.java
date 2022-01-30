package de.mixelblocks.coresystem.redis.messaging;

import de.mixelblocks.coresystem.redis.RedisClient;
import de.mixelblocks.coresystem.redis.messaging.commands.defaults.PingCommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.redis.messaging:MessagingProviderImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class MessagingProviderImpl implements MessagingProvider {

    private final RedisClient redis;
    private Map<String, MessagingChannel> channels;

    public MessagingProviderImpl(RedisClient redis) {
        this.redis = redis;
        this.channels = new HashMap<>();
        // register default channel
        this.channel(MessagingChannel.newChannel("coresystem", this));
        this.channel("coresystem").commandMap().register(new PingCommand());
    }

    @Override
    public RedisClient redis() {
        return redis;
    }

    @Override
    public Collection<MessagingChannel> messagingChannels() {
        return channels.values();
    }

    @Override
    public MessagingChannel channel(String name) {
        return channels.get(name);
    }

    @Override
    public void channel(MessagingChannel channel) {
        if(!channels.containsKey(channel.getName())) channels.put(channel.getName(), channel);
    }
}
