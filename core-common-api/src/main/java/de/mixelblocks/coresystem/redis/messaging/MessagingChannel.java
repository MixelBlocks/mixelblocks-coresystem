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
import de.mixelblocks.coresystem.redis.messaging.commands.MessagingCommand;
import de.mixelblocks.coresystem.redis.messaging.commands.MessagingCommandMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.redis.messaging:MessagingChannel
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface MessagingChannel {

    String getName();
    MessagingCommandMap commandMap();

    void sendMessage(String message);
    void sendCommand(String server, String command);
    void sendGlobalCommand(String command);

    MessagingProvider service();

    void onMessage(Consumer<String> message);

    /**
     * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
     * @since 27.01.2022
     */
    class DefaultChannel implements MessagingChannel {

        private final String name;
        private final MessagingCommandMap commandMap;
        private final List<Consumer> messageHandlers;
        private final MessagingProvider service;

        public DefaultChannel(String name, MessagingProvider service)  {
            this.name = name;
            this.service = service;
            this.commandMap = new MessagingCommandMap();
            this.messageHandlers = new ArrayList<>();
            this.service().redis().pubSubListener().messageHandlers().add((channel, message) -> {
                if(channel != name) return;
                if(!message.contains(RedisClient.splitter)) {
                    messageHandlers.forEach(handler -> handler.accept(message));
                    return;
                }
                String session = message.split(RedisClient.splitter)[0];
                try {
                    UUID uuid = UUID.fromString(session);
                } catch (Exception e) {
                    messageHandlers.forEach(handler -> handler.accept(message));
                    return;
                }

                String onlyContent = message.strip().split(RedisClient.splitter)[1];
                String allArgs[] = onlyContent.strip().split(Sep.CMD.get());
                if(allArgs.length > 0) {
                    switch(allArgs[0].toLowerCase()) {
                        case "server": {
                            if(message.startsWith(RedisClient.session)) return;
                            try {
                                String server = allArgs[1];
                                if(server.equalsIgnoreCase(RedisClient.server)) break;
                                String command = allArgs[2];
                                MessagingCommand cmd = commandMap.get(command);
                                cmd.execute(this, command, allArgs[3].split("\\s+"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "global": {
                            try {
                                String command = allArgs[1];
                                MessagingCommand cmd = commandMap.get(command);
                                cmd.execute(this, command, allArgs[1].split("\\s+"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        default: {
                            break;
                        }
                    }
                }
            });
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public MessagingCommandMap commandMap() {
            return commandMap;
        }

        @Override
        public void sendMessage(String message) {
            service().redis().sendMessage(name, message);
        }

        @Override
        public void sendCommand(String server, String command) {
            sendMessage("GLOBAL" + Sep.CMD.get() + server + Sep.CMD.get() + command);
        }

        @Override
        public void sendGlobalCommand(String command) {
            sendMessage("GLOBAL" + Sep.CMD.get() + command);
        }

        @Override
        public MessagingProvider service() {
            return service;
        }

        @Override
        public void onMessage(Consumer<String> message) {
            messageHandlers.add(message);
        }

    }

    enum Sep {
        CMD("//::\\\\");
        public String s;
        Sep(String s) { this.s = s; }
        public String get() {
            return this.s;
        }
    }

    static MessagingChannel newChannel(String name, MessagingProvider service) {
        return new DefaultChannel(name, service);
    }

}
